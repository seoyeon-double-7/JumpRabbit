package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import JumpRabbit.JumpRabbit;
import dialog.GuestDialog;
import dialog.LogoutDialog;
import main.Main;


public class IntroPanel extends JPanel implements ActionListener, MouseListener {

    // 버튼 선언
    static JButton btnStart; //게임 시작
    static JButton btnHow; //게임 방법
    static JButton btnRank; //랭킹

    // 라벨 선언
    JLabel labelLogin; //로그인

    // 로그인 상태 판정
	public static Boolean isLogin = false;
	
	// 배경 이미지 준비
    ImageIcon mainScreen = new ImageIcon("img/screen_main.png");


    public IntroPanel() {
    	setLayout(null);
    	
    	// 요소들 정의하며 값 넣기
    	btnStart = new JButton(new ImageIcon("img/icon_start.png"));
    	btnHow = new JButton(new ImageIcon("img/icon_how.png"));
    	btnRank = new JButton(new ImageIcon("img/icon_rank.png"));
		labelLogin = new JLabel("LOGIN");
    			
	    // 요소들	버튼 위치 지정
	    btnStart.setBounds(183, 614, 198, 61);
	    btnHow.setBounds(554, 614, 124, 61);
	    btnRank.setBounds(867, 614, 162, 61);
		labelLogin.setBounds(1080, 10, 150,50);

	    // 버튼 윤곽선, 배경색 없애기
	    btnStart.setBorderPainted(false); btnStart.setContentAreaFilled(false);
	    btnHow.setBorderPainted(false); btnHow.setContentAreaFilled(false);
	    btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);
	    
		// 라벨 글씨 설정-폰트
	    labelLogin.setFont(Main.font.deriveFont(30f));
		
		// 라벨 글씨 설정-글씨색
		labelLogin.setForeground(Main.defaultColor);

	    // 마우스 클릭 리스너 추가
	    btnStart.addActionListener(this);
	    btnHow.addActionListener(this);
	    btnRank.addActionListener(this);

	    // 마우스 진입 판독 리스너 추가
	    btnStart.addMouseListener(this);
	    btnHow.addMouseListener(this);
	    btnRank.addMouseListener(this);
		labelLogin.addMouseListener(this);

	    // 패널에 요소 추가
        add(btnStart);
        add(btnHow);
        add(btnRank);
		add(labelLogin);
	}

    
	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		//paintComponent에 다른 코드를 넣는 이유는 GClearPanel에 상세하게 설명해두었습니다.
		
		if(isLogin) { //로그인 상태라면
			labelLogin.setText("LOGOUT"); //로그아웃할 수 있도록 함
			labelLogin.setBounds(1070, 10, 550,50);
		}
		else { //로그아웃 상태라면
			labelLogin.setText("LOGIN"); //로그인할 수 있도록 함
			labelLogin.setBounds(1080, 10, 150,50);
		}

		// 이미지 그려주기
		g.drawImage(mainScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
	}
	

	//* 마우스 클릭 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnStart){ //START 버튼 클릭시
			if(!isLogin) //로그인이 안 되어있다면
				new GuestDialog(); //게스트플레이할건지 로그인할건지 물어보는 다이얼로그창 띄우기
			else //로그인이 되어있다면
				JumpRabbit.changeCard("select"); //바로 게임 캐릭터 선택
		}
		else if(ob == btnHow) //HOW 버튼 클릭시
			JumpRabbit.changeCard("how"); //게임 방법 화면으로 이동	
		else if(ob == btnRank) //RANK 버튼 클릭시
			JumpRabbit.changeCard("rank"); //랭킹 화면으로 이동

	}


	//* 마우스 진입 감독 & 라벨 클릭 리스너
	//라벨 클릭하면
	@Override
	public void mouseClicked(MouseEvent e) {
		//labelLogin을 클릭하는데
		if(e.getSource().toString().contains("LOGIN")) //로그아웃 상태라면
			JumpRabbit.changeCard("login"); //로그인 화면으로 이동
		else if(e.getSource().toString().contains("LOGOUT")){ //로그인 상태라면
			new LogoutDialog(); //로그아웃을 묻는 다이얼로그창 띄우기
		}
	}

	
	//마우스 진입하면
	@Override
	public void mouseEntered(MouseEvent e) {
		//라벨 위에 마우스가 놓이면
		if(e.getSource().toString().contains("LOG")) //LOGIN, LOGOUT 둘다
			labelLogin.setForeground(Color.decode("#FFB7E0")); //더 연한 색으로 바뀜
		
		//버튼
		else if(e.getSource().toString().contains("icon_start")) //START 버튼
			btnStart.setIcon(new ImageIcon("img/icon_start_entered.png")); //이미지 바뀜

		else if(e.getSource().toString().contains("icon_how")) //HOW 버튼
			btnHow.setIcon(new ImageIcon("img/icon_how_entered.png")); //이미지 바뀜

		else if(e.getSource().toString().contains("icon_rank")) //RANK 버튼
			btnRank.setIcon(new ImageIcon("img/icon_rank_entered.png"));  //이미지 바뀜
		
	}
	
	
	//마우스 진입했다가 나오면
	@Override
	public void mouseExited(MouseEvent e) {
		//라벨
		if(e.getSource().toString().contains("LOG")) //LOGIN, LOGOUT 둘다
			labelLogin.setForeground(Main.defaultColor);

		//버튼
		else if(e.getSource().toString().contains("icon_start")){
			btnStart.setIcon(new ImageIcon("img/icon_start.png"));

		}else if(e.getSource().toString().contains("icon_how")){
			btnHow.setIcon(new ImageIcon("img/icon_how.png"));

		}else if(e.getSource().toString().contains("icon_rank")){
			btnRank.setIcon(new ImageIcon("img/icon_rank.png"));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
