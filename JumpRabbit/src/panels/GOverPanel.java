package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import JumpRabbit.JumpRabbit;
import dialog.ConfirmDialog;
import main.Main;

public class GOverPanel extends JPanel implements ActionListener, MouseListener {
	
	//배경 이미지 준비
    ImageIcon overScreen = new ImageIcon("img/screen_gameover.png");

    //버튼 선언, 정의
    JButton btnRe = new JButton(new ImageIcon("img/icon_game_return.png"));
    JButton btnRank = new JButton(new ImageIcon("img/icon_game_rank.png"));

    //점수 보여주는 라벨
    JLabel labelScore = new JLabel();
    
    //점수
    public static int score = -1;

    // 생성자
    public GOverPanel(){
        setLayout(null);

        // 버튼 위치 조정
        btnRe.setBounds(549, 488, 226, 57);
        btnRank.setBounds(814, 488, 149, 57);

        // 라벨 꾸미기
        labelScore.setBounds(200, 353, 800, 110);
        labelScore.setHorizontalAlignment(JLabel.CENTER);
        labelScore.setFont(Main.font.deriveFont(110f));
        labelScore.setForeground(Main.defaultColor);

        //	버튼 윤곽선, 배경색 없애기
        btnRe.setBorderPainted(false); btnRe.setContentAreaFilled(false);
        btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

        // 패널에 요소들 추가
        add(btnRe);
        add(btnRank);
        add(labelScore);

        // 클릭 이벤트
        btnRe.addActionListener(this);
        btnRank.addActionListener(this);

        // 마우스 이벤트 (entered, exited)
        btnRe.addMouseListener(this);
        btnRank.addMouseListener(this);

    }

    //* 배경 이미지 그려주기
    public void paintComponent(Graphics g) {
    	//다른 코드가 있는 이유는 GClearPanel의 주석을 참고하여 주세요
    	
    	//* 점수값 바꿔주기
        if(score == -1) // 초기값인 -1이라면(점수를 못받아온 상태)
            labelScore.setText("..."); //로딩을 뜻하는 ... 표시
         else // 점수를 받았다면
            labelScore.setText(Integer.toString(score)); //점수 표시
        
        g.drawImage(overScreen.getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

    
    //* 마우스 클릭 이벤트
    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob == btnRe)
            JumpRabbit.changeCard("select");
        else if(ob == btnRank)
            JumpRabbit.changeCard("rank");
    }

    
    //* DB에 점수 insert하는 메서드
    public void insertScore(boolean isExist){
        try{
        	// DB 연동
            String url = "jdbc:mysql://localhost:3306/jumprabbit";
            String userName = "jumprabbit";
            String password = "jumprabbit";
            String sql = null;

            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement pstmt = null;

            if(!isExist){  //첫 플레이일때(닉네임 없음) insert
            	// 일단 이 메서드를 실행하면 무조건 이 if문으로 들어오는데
            	// 첫 플레이가 아닌 플레이어는 primary key 오류가 나고, catch문으로 내려가게 된다
            	
                sql = "INSERT INTO user_score VALUES (?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, JoinNamePanel.Nickname);
                pstmt.setInt(2, score);
                
            }else{ //첫 플레이 아닐때(닉네임 있음) update
            	// catch문을 거쳐서야 이 블록 안으로 진입 가능하다
            	
                sql = "update user_score set score=? where name=?;";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, score);
                pstmt.setString(2, JoinNamePanel.Nickname);

            }
            pstmt.executeUpdate(); //쿼리 실행

            pstmt.close();
            conn.close();
            
        }catch (Exception e){
        	if(e.toString().contains("PRIMARY")) //위에서 언급한 primary key 오류가 나면
                insertScore(true); //첫번째 플레이가 아니라는 뜻을 가진 매개변수로 다시 메서드 호출
            else{
                new ConfirmDialog("알 수 없는 오류가 발생했습니다.");
                e.printStackTrace();
            }
        }
    }
    
    // 마우스 진입 판독하는 이벤트 리스너 
    @Override
    public void mouseEntered(MouseEvent e) {
    	// 마우스 진입
        if(e.getSource().toString().contains("icon_game_rank")) //랭킹 버튼에 진입하면
            btnRank.setIcon(new ImageIcon("img/icon_game_rank_entered.png")); //더 진한 색의 이미지로 변환
        
        else if(e.getSource().toString().contains("icon_game_return")) //되돌아가기 버튼에 진입하면
            btnRe.setIcon(new ImageIcon("img/icon_game_return_entered.png")); //더 진한 색의 이미지로 변환
    }

    @Override
    public void mouseExited(MouseEvent e) {
    	// 마우스 진입했다가 나감
        if(e.getSource().toString().contains("icon_game_rank_entered")) //랭킹 버튼에 진입하면
            btnRank.setIcon(new ImageIcon("img/icon_game_rank.png")); //원래 이미지로 변환
        
        else if(e.getSource().toString().contains("icon_game_return_entered")) //되돌아가기 버튼에 진입하면
            btnRe.setIcon(new ImageIcon("img/icon_game_return.png")); //원래 이미지로 변환
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
