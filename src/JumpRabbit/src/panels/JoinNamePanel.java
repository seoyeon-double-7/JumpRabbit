package panels;

import javax.swing.*;

import JumpRabbit.JumpRabbit;
import main.Main;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JoinNamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	//입력란 옆의 텍스트
	JLabel labelNotice = new JLabel("NAME");
	
	//닉네임을 입력받을 커스텀 텍스트 필드
	public static CustomTextField textNickname = new CustomTextField();
	
	//문자열 타입의 닉네임
	public static String Nickname = "";
	
	//배경 이미지 준비
	ImageIcon howScreen = new ImageIcon("img/screen_nickname.png");
	
	//되돌아가기 버튼
	static JButton btnBack = new JButton(new ImageIcon("img/icon_back.png"));

	//회원가입 중 아이디, 비밀번호를 받았던 이전 패널에서 가져온 아이디. update시 조건문에 필요
	static public String inputID;

	
	//생성자
	public JoinNamePanel() {
		setLayout(null);
		//TODO: 회원가입 중 돌아가기 기능, 닉네임 없는 계정은 자동 삭제
		
		// 라벨 위치 조정
		labelNotice.setBounds(335, 377, 182, 56);
		textNickname.setBounds(473, 368, 404, 68);

		// 라벨 글꼴 설정
		labelNotice.setFont(Main.font.deriveFont(60f));
		labelNotice.setForeground(Main.defaultColor);

		// 커스텀 텍스트 필드 설정
		textNickname.setHint("닉네임을 입력하세요"); // 텍스트필드에 Hint 입력
		textNickname.setBackgroundImage("img/textfield.png"); // 텍스트필드 배경 이미지 설정
		
		// 뒤로 돌아가기 버튼
		btnBack.setBounds(1070, 700, 102, 64);
		btnBack.setBorderPainted(false); btnBack.setContentAreaFilled(false);

		// 요소들 패널에 추가
		add(labelNotice);
		add(textNickname);
		add(btnBack);
		
		// 리스너 추가
		btnBack.addActionListener(this); //클릭 이벤트
		btnBack.addMouseListener(this); //진입&나가는 이벤트
		textNickname.addKeyListener(this); //엔터 이벤트
		addKeyListener(this); //엔터 이벤트
		requestFocus();
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
		if(ob == btnBack){
			JumpRabbit.changeCard("login");
		}
	}

	
	//* 키보드 입력 이벤트
	@Override
	public void keyPressed(KeyEvent e) {
		//엔터 입력시 화면 이동
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try{
				//* DB 연동
				String url = "jdbc:mysql://localhost:3306/jumprabbit";
				String userName = "jumprabbit";
				String password = "jumprabbit";
				String sql = "update user_information set name = ? where id=?;";

				Connection conn = DriverManager.getConnection(url, userName, password);
				PreparedStatement pt = conn.prepareStatement(sql);

				Nickname = textNickname.getText();
				pt.setString(1, textNickname.getText());
				pt.setString(2, inputID);

				int r = pt.executeUpdate();

				pt.close();
				conn.close();
			}catch (Exception exception){
				exception.printStackTrace();
			}

			System.out.println("회원가입 성공");
			IntroPanel.isLogin = true;
			JumpRabbit.changeCard("intro");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}


	//마우스 진입 시 이벤트
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().toString().contains("icon_back"))
			btnBack.setIcon(new ImageIcon("img/icon_back_entered.png"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
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
