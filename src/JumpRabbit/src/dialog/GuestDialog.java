package dialog;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import JumpRabbit.JumpRabbit;
import main.Main;
import panels.JoinNamePanel;

public class GuestDialog {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    JButton btnYes = new JButton("확인");
    JButton btnLogin = new JButton("로그인");

    static int cnt = 1000; //게스트 번호

    public GuestDialog() {

    	//* 질문 글
        JLabel displayText = new JLabel("! 게스트로 플레이하시겠습니까?");//생성자로 텍스트값 넣어두기
        displayText.setFont(Main.font.deriveFont(22f)); //폰트 크기, 글씨체 설정
        displayText.setForeground(Color.white);; //글씨색 설정
        displayText.setHorizontalAlignment(JLabel.CENTER); //가로 가운데 정렬
        displayText.setVerticalTextPosition(SwingConstants.CENTER); //세로 가운데 정렬
        displayText.setBounds(0,0,380,110); //위치 설정

        //* '예' 버튼
        btnYes.setFont(Main.font.deriveFont(20f)); //폰트 크기, 글씨체 설정
        btnYes.setForeground(Main.defaultColor); //글씨색 설정
        btnYes.setBackground(Color.white); //버튼 배경색 설정
        btnYes.setBounds(100,95,80,40); //위치 설정
        btnYes.setFocusPainted(false); //글씨 주위 윤곽선 사라지게

        //* '로그인' 버튼
        btnLogin.setFont(Main.font.deriveFont(20f)); //폰트 크기, 글씨체 설정
        btnLogin.setForeground(Main.defaultColor); //글씨색 설정
        btnLogin.setBackground(Color.white); //버튼 배경색 설정
        btnLogin.setBounds(190,95,100,40); //위치 설정
        btnLogin.setFocusPainted(false); //글씨 주위 윤곽선 사라지게
        
        //마우스 클릭시 이벤트 리스너 추가
        btnYes.addActionListener(btnListener);
        btnLogin.addActionListener(btnListener);

        //* 패널 기본 설정
        panel.setLayout(null);
        panel.setBackground(Color.decode("#FFB7E0"));
        panel.add(displayText);
        panel.add(btnYes);
        panel.add(btnLogin);
        frame.add(panel);

        //Frame 아이콘 이미지 준비
    	Toolkit tk = Toolkit.getDefaultToolkit();
    	Image frameIcon = tk.getImage("img/heart.png");
    	
        //* Frame 기본 설정
    	frame.setIconImage(frameIcon);
        frame.setTitle("Try to Login");
        frame.setSize(400, 220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    //* 버튼 클릭 리스너
    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            frame.setVisible(false);

            //btnYes 클릭시
            if(ob == btnYes){
                JumpRabbit.changeCard("select"); //게임 캐릭터 설정 화면으로 전환
                JoinNamePanel.Nickname = "guest" + ++cnt; //게스트용 닉네임 설정
            }//btnLogin 클릭시
            else if(ob == btnLogin)
                JumpRabbit.changeCard("login"); //로그인 화면으로 전환

        }
    };
}
