package dialog;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmDialog {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    public ConfirmDialog(String message) {

    	//* 안내글
        JLabel displayText = new JLabel("! "+message); //생성자로 텍스트값 넣어두기
        displayText.setFont(Main.font.deriveFont(20f)); //폰트 크기, 글씨체 설정
        displayText.setForeground(Color.white); //글씨색 설정
        displayText.setHorizontalAlignment(JLabel.CENTER); //가로 가운데 정렬
        displayText.setVerticalTextPosition(SwingConstants.CENTER); //세로 가운데 정렬
        displayText.setBounds(0,0,380,110); //위치 설정

        //* 확인 버튼
        JButton btn = new JButton("확인"); //생성자로 텍스트값 넣어두기
        btn.setFont(Main.font.deriveFont(20f)); //폰트 크기, 글씨체 설정
        btn.setForeground(Main.defaultColor); //글씨색 설정
        btn.setBackground(Color.white); //버튼 배경색 설정
        btn.setBounds(150,95,80,40); //위치 설정
        btn.setFocusPainted(false); //글씨 주위 윤곽선 사라지게

        //* 버튼 클릭시 프레임 꺼짐
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        //* 패널 설정
        panel.setLayout(null); //레이아웃 없음
        panel.setBackground(Color.decode("#FFB7E0")); //배경화면 색
        panel.add(displayText);
        panel.add(btn);
        frame.add(panel);
        
        //Frame 아이콘 이미지 준비
    	Toolkit tk = Toolkit.getDefaultToolkit();
    	Image frameIcon = tk.getImage("img/heart.png");
    	
        //* Frame 기본 설정
    	frame.setIconImage(frameIcon); //아이콘 설정
        frame.setTitle("NOTICE"); //상단바 이름 설정
        frame.setSize(400, 220); //윈도우창 가로세로 폭 지정
        frame.setResizable(false); //창 크기 고정
        frame.setLocationRelativeTo(null); //PC 화면의 가운데에 프레임 생성
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //상단바의 X 클릭시 해당 프레임만 종료
        frame.setVisible(true); ////프레임이 눈에 보이도록
    }
}
