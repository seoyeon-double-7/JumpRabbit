package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

public class Util {

	// 이미지의 사이즈 가져오기
	public static int[] getSize(String src) throws Exception {
		File imgf = new File(src);
		BufferedImage img = ImageIO.read(imgf);
		int width = img.getWidth();
		int height = img.getHeight();
		int[] tempSize = {width, height};
		return tempSize;
	}
	
	// 이미지의 픽셀값 가져오기
	public static int[][] getPic(String src) throws Exception{
		File imgf = new File(src);
		BufferedImage img = ImageIO.read(imgf);
		int width = img.getWidth();
		int height = img.getHeight();
		int[] pixels=new int[width*height];
		PixelGrabber grab = new PixelGrabber(img, 0, 0, width, height, pixels, 0,width);
		grab.grabPixels();
		
		int[][] picture=new int[width][height];
		for(int i=0;i<pixels.length;i++)
		      picture[i%width][i/width]=pixels[i] + 16777216;
		return picture;
	}
	
	// 현재시간 타임스탬프로 가져오기
	public static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}
	
	// 글자에 테두리 넣기
	public static void drawFancyString(Graphics2D g, String str, int x, int y, float size, Color internalColor) {
		  if(str.length()==0)return;
		  AffineTransform orig = g.getTransform();
		  Font f = new Font("Harlow Solid Italic", Font.BOLD, 50);
		  TextLayout tl = new TextLayout(str, f, g.getFontRenderContext());
		  AffineTransform transform = g.getTransform();
		  FontMetrics fm = g.getFontMetrics(f);
		  Shape outline = tl.getOutline(null);
		  Rectangle bound = outline.getBounds();
		  transform.translate(x, y+fm.getAscent());
		  
		  g.setTransform(transform);
		  g.setColor(internalColor);
		  g.fill(outline);
		  g.setStroke(new BasicStroke(size/25));
		  g.setColor(Color.BLACK);
		  g.draw(outline);
		  
		  g.setTransform(orig);
		}
	

	
}
