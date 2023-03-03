package panels;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import JumpRabbit.JumpRabbit;
import dialog.ConfirmDialog;
import main.Main;

public class LoginPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	//ID, PW 텍스트
	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");
	
	//회원가입 버튼
	JButton btnJoin = new JButton(new ImageIcon("img/icon_join.png"));
	
	//뒤돌아가기 버튼
	static JButton btnBack = new JButton(new ImageIcon("img/icon_back.png"));

	//커스텀 텍스트 필드
	public static CustomTextField textID = new CustomTextField();
	public static CustomTextField textPW = new CustomTextField();

	//배경 이미지 준비
    ImageIcon howScreen = new ImageIcon("img/screen_login.png");
    
    
    //생성자
    public LoginPanel() {
    	setLayout(null);

    	// 요소들 위치 조정
		btnBack.setBounds(1070, 700, 102, 64);
	    labelID.setBounds(322, 341, 182, 56);
		labelPW.setBounds(323, 455, 182, 56);
		btnJoin.setBounds(720,480,200,100);
		textID.setBounds(430, 323, 404, 68);
		textPW.setBounds(430, 433, 404, 68);

		// 뒤로 돌아가기 버튼
		btnBack.setBorderPainted(false); //외곽선 안 보이게
		btnBack.setContentAreaFilled(false); //버튼색 투명으로
		
		// 회원가입 버튼
		btnJoin.setBorderPainted(false);
		btnJoin.setContentAreaFilled(false);
		
		// 폰트, 글씨 크기 설정
		labelID.setFont(Main.font.deriveFont(60f));
		labelPW.setFont(Main.font.deriveFont(60f));

		// 글씨색 설정
		labelID.setForeground(Main.defaultColor);
		labelPW.setForeground(Main.defaultColor);

		// 텍스트필드 힌트 설정
		textID.setHint("아이디를 입력하세요.");
		textPW.setHint("비밀번호를 입력하세요.");

		// 텍스트필드 배경 이미지 준비
		textID.setBackgroundImage("img/textfield.png");
		textPW.setBackgroundImage("img/textfield.png");

		// 패널에 요소 추가
		add(btnBack);
	    add(labelID);
	    add(labelPW);
		add(btnJoin);
		add(textID);
		add(textPW);

		// 마우스 클릭 리스너 추가
		btnBack.addActionListener(this);
		btnJoin.addMouseListener(this);
		btnBack.addMouseListener(this);
		
		// 키보드 입력 리스너 추가
		textID.addKeyListener(this);
		textPW.addKeyListener(this);
    }

    
	// 배경 이미지 그리기
	public void paintComponent(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	
	// 텍스트필드 입력란 비우는 메서드
	public static void setBlank(){
		textID.setText("");
		textPW.setText("");
		textID.setHint("아이디를 입력하세요.");
		textPW.setHint("비밀번호를 입력하세요.");
	}
	

	//* 버튼 클릭 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnBack){
			JumpRabbit.changeCard("intro");
			setBlank();
		}
	}

	
	//* 엔터키 리스너
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// 엔터 입력시
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//* 사용자가 입력한 id와 pw 값 가져오기
			String inputID = textID.getText();
			String inputPW = textPW.getText();

			try{
				//* DB 연동	
				String url = "jdbc:mysql://localhost:3306/jumprabbit";
				String userName = "jumprabbit";
				String password = "jumprabbit";
				Connection conn = DriverManager.getConnection(url, userName, password);

				// sql문
				String sql = "select * from user_information where id='"+inputID+"';";
				Statement st = conn.createStatement();
				ResultSet rs = null;
				
				//* 아이디, 비밀번호 판정
				if(!textID.getText().equals("아이디를 입력하세요.") && !textID.getText().equals("비밀번호를 입력하세요.")){ //힌트가 아니라 입력된 값이라면
					rs = st.executeQuery(sql); //sql문 실행
					rs.next(); //행 한 번 넘기고
					JoinNamePanel.Nickname = rs.getString("name"); //DB의 닉네임값 가져오기
				}else
					new ConfirmDialog("빈칸을 입력해주세요"); //텍스트필드에서 가져온 값이 힌트의 내용이라면 실행

				
				if(textID.getText().equals("아이디를 입력하세요.") || textPW.getText().equals("비밀번호를 입력하세요.")) //아이디 잘 있는지 확인해준뒤
					new ConfirmDialog("빈칸을 입력해주세요");
				else if(inputPW.equals(rs.getString("pw"))){ //비밀번호도 똑같다면
					System.out.println("로그인 성공");
					IntroPanel.isLogin = true; //로그인한 상태로 값 변경

					//화면 이동
					JumpRabbit.changeCard("intro");
				}
				else if(!inputPW.equals(rs.getString("pw"))){
					new ConfirmDialog("비밀번호가 틀렸습니다."); //아니라면
					textPW.setText("");
				}

				// LIFO로 close해주기
				st.close();
				if(rs!=null) rs.close();
				conn.close();

			}catch (Exception exception){ //mysql 오류가 발생하면
				if(exception.toString().contains("Illegal operation on empty result set.")){ //id를 조건으로 select했는데 empty 에러가 뜨면
					new ConfirmDialog("해당하는 아이디가 없습니다"); //아이디 정보 없음 표시 후
					setBlank(); //입력란 공란 비우기
				}
				else exception.printStackTrace();
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	
	//* 마우스 리스너
	@Override
	public void mouseClicked(MouseEvent e) {
		//회원가입 버튼 클릭하면
		if(e.getSource().toString().contains("icon_join"))
			JumpRabbit.changeCard("join"); //화면전환
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//버튼 위에 마우스가 오면
		if(e.getSource().toString().contains("icon_join")) //회원가입 버튼일때
			btnJoin.setIcon(new ImageIcon("img/icon_join_entered.png")); //원래 버튼색보다 더 진한 이미지로 잠시 변함
		else if(e.getSource().toString().contains("icon_back")) //되돌아가는 버튼일때
			btnBack.setIcon(new ImageIcon("img/icon_back_entered.png")); //마찬가지
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//버튼 위에 있던 마우스가 나가면
		if(e.getSource().toString().contains("icon_join")) //회원가입 버튼일때
			btnJoin.setIcon(new ImageIcon("img/icon_join.png")); //다시 원래 이미지로 돌아옴
		else if(e.getSource().toString().contains("icon_back_entered"))//되돌아가는 버튼일때
			btnBack.setIcon(new ImageIcon("img/icon_back.png")); //마찬가지
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
