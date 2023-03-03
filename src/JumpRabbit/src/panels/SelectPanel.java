package panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import JumpRabbit.JumpRabbit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import object.Rabbitimg;

public class SelectPanel extends JPanel implements MouseListener{
   
   String rabbit1 = "img/RabbitImg/rabbit1/rightRabbit.gif";
   String rabbit2 = "img/RabbitImg/rabbit2/rightRabbit.gif";
   String rabbit3 = "img/RabbitImg/rabbit3/rightRabbit.gif";
   String rabbit4 = "img/RabbitImg/rabbit4/rightRabbit.gif";
   
   String rabbit11 = "img/RabbitImg/rabbit1/rightRabbit_selected.gif";
   String rabbit22 = "img/RabbitImg/rabbit2/rightRabbit_selected.gif";
   String rabbit33 = "img/RabbitImg/rabbit3/rightRabbit_selected.gif";
   String rabbit44 = "img/RabbitImg/rabbit4/rightRabbit_selected.gif";

   
   // 선택할 캐릭터 이미지 아이콘
   private ImageIcon ch01 = new ImageIcon(rabbit1);
   private ImageIcon ch02 = new ImageIcon(rabbit2);
   private ImageIcon ch03 = new ImageIcon(rabbit3);
   private ImageIcon ch04 = new ImageIcon(rabbit4);

   // 선택된 캐릭터 이미지 아이콘
   private ImageIcon ch011 = new ImageIcon(rabbit11);
   private ImageIcon ch022 = new ImageIcon(rabbit22);
   private ImageIcon ch033 = new ImageIcon(rabbit33);
   private ImageIcon ch044 = new ImageIcon(rabbit44);

   // 시작 버튼 이미지아이콘
   private ImageIcon start = new ImageIcon("img/icon_start.png");
   private ImageIcon started = new ImageIcon("img/select/icon_start_entered.png");
   
   // 이미지를 선택할 버튼
   private JButton ch1;
   private JButton ch2;
   private JButton ch3;
   private JButton ch4;
   
   private int x = 250;
   private int y = 225;
   
   // 시작 버튼
   private JButton StartBtn;
   
   // 게임에서 사용할 쿠키 이미지들을 담을 오브젝트
   private Rabbitimg ci;

   // 쿠키 이미지를 메인에서 gamePanel로 보내기 위한 게터
   public Rabbitimg getCi() {
      return ci;
   }

   public SelectPanel(Object o) {
      setLayout(null);
      
      // 시작 버튼
      StartBtn = new JButton(start);
      StartBtn.setName("StartBtn");
      StartBtn.addMouseListener((MouseListener) o);
      StartBtn.addMouseListener(this);
      
      StartBtn.setBounds(470, 550, 291, 81);
      add(StartBtn);
      StartBtn.setBorderPainted(false);
      StartBtn.setContentAreaFilled(false);
      StartBtn.setFocusPainted(false);
      
      // 캐릭터 ch1
      ch1 = new JButton(ch01);
      ch1.setName("ch1");
      ch1.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            ch1.setIcon(ch011);
            ch2.setIcon(ch02);
            ch3.setIcon(ch03);
            ch4.setIcon(ch04);
            ci = new Rabbitimg(new ImageIcon(rabbit1),
                  new ImageIcon(rabbit1),
                  new ImageIcon(rabbit1),
                  new ImageIcon(rabbit1),
                  new ImageIcon("img/RabbitImg/rabbit1/damageRabbit.gif"),
                  new ImageIcon("img/RabbitImg/rabbit1/damageRabbit.gif"), 1);
         }
      });
      ch1.setBounds(x, y, 150, 200);
      add(ch1);
      ch1.setBorderPainted(false);
      ch1.setContentAreaFilled(false);
      ch1.setFocusPainted(false);

      // 캐릭터 ch2
      ch2 = new JButton(ch02);
      ch2.setName("ch2");
      ch2.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            ch1.setIcon(ch01);
            ch2.setIcon(ch022);
            ch3.setIcon(ch03);
            ch4.setIcon(ch04);
            ci = new Rabbitimg(new ImageIcon(rabbit2),
                  new ImageIcon(rabbit2),
                  new ImageIcon(rabbit2),
                  new ImageIcon(rabbit2),
                  new ImageIcon("img/RabbitImg/rabbit1/damageRabbit.gif"),
                  new ImageIcon("img/RabbitImg/rabbit1/damageRabbit.gif"), 2);
         }
      });
      ch2.setBounds(x+200, y, 150, 200);
      add(ch2);
      ch2.setBorderPainted(false);
      ch2.setContentAreaFilled(false);
      ch2.setFocusPainted(false);

      // 캐릭터 ch3
      ch3 = new JButton(ch03);
      ch3.setName("ch3");
      ch3.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            ch1.setIcon(ch01);
            ch2.setIcon(ch02);
            ch3.setIcon(ch033);
            ch4.setIcon(ch04);
            ci = new Rabbitimg(new ImageIcon(rabbit3),
                  new ImageIcon(rabbit3),
                  new ImageIcon(rabbit3),
                  new ImageIcon(rabbit3),
                  new ImageIcon("img/RabbitImg/rabbit1/damageRabbit.gif"),
                  new ImageIcon("img/RabbitImg/rabbit1/damageRabbit.gif"), 3);
         }
      });
      ch3.setBounds(x+400, y, 150, 200);
      add(ch3);
      ch3.setBorderPainted(false);
      ch3.setContentAreaFilled(false);
      ch3.setFocusPainted(false);

      // 캐릭터 ch4
      ch4 = new JButton(ch04);
      ch4.setName("ch4");
      ch4.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            ch1.setIcon(ch01);
            ch2.setIcon(ch02);
            ch3.setIcon(ch03);
            ch4.setIcon(ch044);
            ci = new Rabbitimg(new ImageIcon(rabbit4),
                  new ImageIcon(rabbit4),
                  new ImageIcon(rabbit4),
                  new ImageIcon(rabbit4),
                  new ImageIcon("img/RabbitImg/rabbit1/damageRabbit.gif"),
                  new ImageIcon("img/RabbitImg/rabbit1/damageRabbit.gif"), 4);;
         }
      });
      ch4.setBounds(x+600, y, 150, 200);
      add(ch4);
      ch4.setBorderPainted(false);
      ch4.setContentAreaFilled(false);
      ch4.setFocusPainted(false);
      
      JLabel selectBg = new JLabel("");
      selectBg.setForeground(Color.ORANGE);
      selectBg.setHorizontalAlignment(SwingConstants.CENTER);
      selectBg.setIcon(new ImageIcon("img/Objectimg/map1img/selectBack2.png"));
      selectBg.setBounds(0, 0, 1200, 800);
      add(selectBg);
   }

   //마우스 리스너
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	System.out.println("들어옴");
	if(e.getSource().toString().contains("icon_start"))
		StartBtn.setIcon(new ImageIcon("img/icon_start_entered.png"));
	
}

@Override
public void mouseExited(MouseEvent e) {
	if(e.getSource().toString().contains("icon_start_entered"))
		StartBtn.setIcon(new ImageIcon("img/icon_start.png"));
	// TODO Auto-generated method stub
	
}
}