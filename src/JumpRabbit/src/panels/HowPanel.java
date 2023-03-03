package panels;

import JumpRabbit.JumpRabbit;
import dialog.GuestDialog;
import main.listenAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HowPanel extends JPanel implements ActionListener, MouseListener {

    // 버튼 선언, 정의
    static JButton btnHowStart = new JButton(new ImageIcon("img/icon_how_start.png")); //시작 버튼
    static JButton btnBack = new JButton(new ImageIcon("img/icon_back.png")); //되돌아가기 버튼
    
    // 배경 이미지 준비
    ImageIcon howScreen = new ImageIcon("img/screen_how.png");
    
    
    // 생성자
    public HowPanel() {
    	setLayout(null);

    	// 시작버튼
	    btnHowStart.setBounds(790, 560, 182, 56);
	    btnHowStart.setBorderPainted(false); 
	    btnHowStart.setContentAreaFilled(false);
		
        // 뒤로 돌아가기 버튼
	    btnBack.setBounds(1070, 700, 102, 64);
	    btnBack.setBorderPainted(false); btnBack.setContentAreaFilled(false);
	    
	    // 요소들 패널에 추가
	    add(btnHowStart);
	    add(btnBack);

	    // 마우스 클릭 이벤트
		btnHowStart.addActionListener(this);
        btnBack.addActionListener(this);

        // 마우스 진입 판독 이벤트
		btnHowStart.addMouseListener(this);
        btnBack.addMouseListener(this);
	    
    }

    
	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	
	//* 마우스 클릭 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnHowStart){ //시작 버튼이면
			if(!IntroPanel.isLogin) //로그아웃 상태면
				new GuestDialog(); //게스트 플레이할건지 로그인할건지 물어보는 다이얼로그창 띄우기
			else //로그인 상태면
				JumpRabbit.changeCard("select"); //게임 캐릭터 고르는 화면으로 이동
		}
		else if(ob == btnBack) //되돌아가기 버튼이면
			JumpRabbit.changeCard("intro"); //인트로 화면으로
	}

	
	//* 마우스 진입&나가는 이벤트
	//다른 클래스에 있는 같은 메서드의 주석을 참고해주세요
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().toString().contains("icon_how_start")){
			btnHowStart.setIcon(new ImageIcon("img/icon_how_start_entered.png"));
		}
        if(e.getSource().toString().contains("icon_back"))
            btnBack.setIcon(new ImageIcon("img/icon_back_entered.png"));
    
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().toString().contains("icon_how_start_entered"))
			btnHowStart.setIcon(new ImageIcon("img/icon_how_start.png"));

        if(e.getSource().toString().contains("icon_back_entered"))
            btnBack.setIcon(new ImageIcon("img/icon_back.png"));
    
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
