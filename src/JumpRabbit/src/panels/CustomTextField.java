package panels;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Main;

public class CustomTextField extends JTextField {
    private static final long serialVersionUID = 1L;
    
    // 텍스트필드 이미지
    private Image img;
    
    // 커스텀한 텍스트 필드
    private CustomTextField hintTextField;

    
    // 생성자
    public CustomTextField() {
        hintTextField = this;
        setBorder(new EmptyBorder(20, 20, 10, 3));
    }

    
    //* 텍스트필드 배경 이미지 그려주기
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, this);
    }

    
    //* 외곽선 안 보이게 하기
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0,0,0,0));
    }

    
    //* 배경 이미지 받아오기
    public void setBackgroundImage(String imPath){
        img = new ImageIcon(imPath).getImage();
        setOpaque(false); //기본 배경색 없애기
    }
    

    //* 텍스트필드에 힌트 넣기
    public void setHint(String hint){
        hintTextField.setText(hint);
        hintTextField.setFont(Main.font.deriveFont(20f));
        hintTextField.setForeground(Color.GRAY);
        hintTextField.addFocusListener(new FocusAdapter() { 

        	//* 텍스트필드 클릭시
            @Override
            public void focusGained(FocusEvent e) {
                if (hintTextField.getText().equals(hint)) 
                    hintTextField.setText(""); //힌트 없애기
                
                //폰트 설정
                hintTextField.setFont(Main.font.deriveFont(20f));
                hintTextField.setForeground(Color.blue);
            }

            //* 텍스트필드 클릭 해제
            @Override
            public void focusLost(FocusEvent e) {
                if ( hintTextField.getText().equals(hint) || getText().length() == 0) { //힌트가 들어있으면
                    hintTextField.setText(hint);
                    hintTextField.setFont(Main.font.deriveFont(20f));
                    hintTextField.setForeground(Color.GRAY);
                } else { //힌트가 들어있지 않고 1 이상의 글자가 입력되어 있으면
                    hintTextField.setText(getText());
                    hintTextField.setFont(Main.font.deriveFont(20f));
                    hintTextField.setForeground(Color.BLACK);
                }
            }
        });
    }
}
