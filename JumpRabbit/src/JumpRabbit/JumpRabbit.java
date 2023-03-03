package JumpRabbit;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dialog.ConfirmDialog;
import main.Main;
import main.listenAdapter;
import object.Rabbitimg;
import panels.GClearPanel;
import panels.GOverPanel;
import panels.GamePanel;
import panels.HowPanel;
import panels.IntroPanel;
import panels.JoinNamePanel;
import panels.JoinPanel;
import panels.LoginPanel;
import panels.RankPanel;
import panels.SelectPanel;

public class JumpRabbit extends listenAdapter {
	// 프레임
	public static JFrame frame;

	// 패널
	private IntroPanel pIntro = new IntroPanel();
	private HowPanel pHow = new HowPanel();
	private static RankPanel pRank = new RankPanel();
	private GOverPanel pOver = new GOverPanel();
	private GClearPanel pClear = new GClearPanel();
	private LoginPanel pLogin = new LoginPanel();
	private JoinPanel pJoin = new JoinPanel();
	private JoinNamePanel pJoinName = new JoinNamePanel();
	private SelectPanel pSelect = new SelectPanel(this);
	private GamePanel pGame = new GamePanel(frame, card);

	// 카드레이아웃
	private static CardLayout card;

	// select 패널의 토끼 이미지
	private Rabbitimg ci;

	// Music 객체
	static Music introMusic, gameMusic, gameclearMusic, gameoverMusic;

	// 폰트
	public static Font font;
	
	// 툴킷
	Toolkit tk;
	
	// 커서 이미지
	Image cursorImage;
	
	// Frame 아이콘
	Image frameIcon;

	// 생성자
	public JumpRabbit() {
		initialize();
	}

	//* Frame 설정
	private void initialize() {	
		//Frame 아이콘 이미지 준비
		tk = Toolkit.getDefaultToolkit();
		frameIcon = tk.getImage("img/heart.png");
		
        //* Frame 기본 설정
		frame = new JFrame(); //프레임 생성
        frame.setTitle("Jump Rabbit"); //상단바 이름 설정
        frame.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //윈도우창 가로세로 폭 지정
        frame.setResizable(false); //창 크기 고정
        frame.setLocationRelativeTo(null); //PC 화면의 가운데에 프레임 생성
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //상단바의 X 클릭시 프로그램 종료
        frame.setVisible(true); //프레임이 눈에 보이도록
        frame.setLayout(null); //레이아웃 없음(추후에 카드 레이아웃)
        frame.setIconImage(frameIcon); //아이콘 설정
		
        //카드레이아웃 생성
		card = new CardLayout(0, 0);
		frame.getContentPane().setLayout(card);

		//* 커스텀 마우스 커서
		cursorImage = tk.getImage("img/HH.png");
		Point point = new Point(10,10);
		Cursor cursor = tk.createCustomCursor(cursorImage, point, "");
		frame.getContentPane().setCursor(cursor);

		//* 패널 add("별칭", 패널 객체 이름)
		frame.getContentPane().add("intro", pIntro);
		frame.getContentPane().add("how", pHow);
		frame.getContentPane().add("rank", pRank);
		frame.getContentPane().add("over", pOver);
		frame.getContentPane().add("clear", pClear);
		frame.getContentPane().add("login",pLogin);
		frame.getContentPane().add("join",pJoin);
		frame.getContentPane().add("nickname",pJoinName);
		frame.getContentPane().add("select", pSelect);
		frame.getContentPane().add("game", pGame);

		//첫화면 설정
		card.show(frame.getContentPane(), "intro");
		
		//* 인트로 bgm 실행
		introMusic = new Music("bgm_basic", true);
		introMusic.start();
	}

	//* 외부 클래스에서 패널을 전환하기 위해 호출하는 메서드
	public static void changeCard(String currentPanel) {
		switch (currentPanel) {
		case "intro": //인트로 화면
			card.show(frame.getContentPane(), "intro");
			
			//* 인트로 bgm 새로 실행
			if(introMusic != null){ //이미 실행되고 있었다면
				introMusic.close(); //끊었다가
				introMusic = new Music("bgm_basic", true); //객체 다시 할당해주고
				introMusic.start(); //bgm 시작
			}
			
			break;
			
			
		case "how": //게임방법 화면
			card.show(frame.getContentPane(), "how");
			
			break;
			

		case "rank": //랭킹 화면
			card.show(frame.getContentPane(), "rank");
			
			//* 랭킹 계산
			pRank.printRanking();
			
			//* 흘러나오던 bgm 중단
			if(gameclearMusic != null) gameclearMusic.close();
			else if(gameoverMusic != null) gameoverMusic.close();
			
			break;
			

		case "over": //게임 오버 화면
			card.show(frame.getContentPane(), "over");
			
			//* 게임 bgm 중단
			if(gameMusic!=null) gameMusic.close();
			
			//* 게임오버 bgm 실행
			gameoverMusic = new Music("sound_gameover", true);
			gameoverMusic.start();
			
			break;

			
		case "clear": //게임 클리어 화면
			card.show(frame.getContentPane(), "clear");

			//* 게임 bgm 중단
			if(gameMusic!=null) gameMusic.close();
			
			//* 게임클리어 bgm 실행
			gameclearMusic = new Music("sound_gameclear", true);
			gameclearMusic.start();
			
			break;

			
		case "login": //로그인 화면
			card.show(frame.getContentPane(), "login");
			break;

			
		case "join": //회원가입 화면
			card.show(frame.getContentPane(), "join");
			break;

			
		case "nickname": //닉네임 화면
			card.show(frame.getContentPane(), "nickname");
			break;

			
		case "game": //게임 화면
			card.show(frame.getContentPane(), "game");	
	
			//* 인트로 bgm 중단
			introMusic.close();
			
			//* 게임 bgm 실행
			gameMusic = new Music("bgm_game", true);
			gameMusic.start();
			
			break;

			
		case "select": //캐릭터 고르는 화면
			card.show(frame.getContentPane(), "select");
			
			//* 게임 다시 할때마다 점수 초기화 
			GClearPanel.score = -1;
			GOverPanel.score = -1;
			
			
			break;
		}
	}
	
	
	//* GamePanel에서 필요한 메서드
	public GamePanel getGamePanel() {
		return pGame;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.pGame = gamePanel;
	}
	
	
	//* 마우스 클릭 이벤트
	@Override
	public void mousePressed(MouseEvent e) {

		//* start button 누르면 게임 시작
		if (e.getComponent().getName().equals("StartBtn")) {
	         frame.getContentPane().remove(pGame); // 방금 했던 게임 패널을 프레임에서 삭제
	         pGame = new GamePanel(frame, card); // 게임패널을 새 패널로 교체
	         pGame.setLayout(null);
	         frame.getContentPane().add(pGame, "game"); // 프레임에 새 게임패널 추가(카드레이아웃 하단)
	         
			if (pSelect.getCi() == null) {
				new ConfirmDialog("캐릭터를 골라주세요");// 캐릭터를 안골랐을경우 팝업
			} else {
				changeCard("game");// 캐릭터를 골랐다면 게임패널을 카드레이아웃 최상단으로 변경
				pGame.gameSet(pSelect.getCi()); // 쿠키이미지를 넘겨주고 게임패널 세팅
				pGame.gameStart(); // 게임시작
				pGame.requestFocus(); // 리스너를 game패널에 강제로 줌			
			}
		}
	}
}