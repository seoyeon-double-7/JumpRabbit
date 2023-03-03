package dialog;

import main.Main;
import panels.IntroPanel;
import panels.JoinPanel;
import panels.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutDialog {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton btnYes = new JButton("예");
    JButton btnNo = new JButton("아니요");

    public LogoutDialog() {
    	
    	//* 질문 글
        JLabel displayText = new JLabel("! 로그아웃하시겠습니까?");
        displayText.setFont(Main.font.deriveFont(20f)); //폰트 크기, 글씨체 설정
        displayText.setForeground(Color.white); //글씨색 설정
        displayText.setHorizontalAlignment(JLabel.CENTER); //가로 가운데 정렬
        displayText.setVerticalTextPosition(SwingConstants.CENTER); //세로 가운데 정렬
        displayText.setBounds(0,0,380,110); //위치 설정

        //* '예' 버튼
        btnYes.setFont(Main.font.deriveFont(20f)); //폰트 크기, 글씨체 설정
        btnYes.setForeground(Main.defaultColor); //글씨색 설정
        btnYes.setBackground(Color.white); //버튼 배경색 설정
        btnYes.setBounds(100,95,80,40); //위치 설정
        btnYes.setFocusPainted(false); //글씨 주위 윤곽선 사라지게

        //* '아니요' 버튼
        btnNo.setFont(Main.font.deriveFont(20f)); //폰트 크기, 글씨체 설정
        btnNo.setForeground(Main.defaultColor); //글씨색 설정
        btnNo.setBackground(Color.white); //버튼 배경색 설정
        btnNo.setBounds(190,95,100,40); //위치 설정
        btnNo.setFocusPainted(false); //글씨 주위 윤곽선 사라지게

        //버튼 마우스 클릭 이벤트 추가
        btnYes.addActionListener(btnListener);
        btnNo.addActionListener(btnListener);

        //* 패널 설정
        panel.setLayout(null);
        panel.setBackground(Color.decode("#FFB7E0"));
        panel.add(displayText);
        panel.add(btnYes);
        panel.add(btnNo);
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
            frame.setVisible(false); //창 사라짐

            //btnYes 클릭시
            if(ob == btnYes){
                IntroPanel.isLogin = false; //로그인 안 한 상태로 변경
                LoginPanel.setBlank(); //로그인 입력란 비우기
                JoinPanel.setBlank(); //회원가입 입력란 비우기
            }//btnNo 클릭시
            else if(ob == btnNo){} //아무것도 안 함
        }
    };
}
