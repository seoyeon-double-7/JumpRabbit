package object;

import java.awt.Image;

import javax.swing.ImageIcon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rabbitimg {
   private ImageIcon cookieIc; //  ⺻   
   private ImageIcon jumpIc; //        
   private ImageIcon doubleJumpIc; //            
   private ImageIcon fallIc; //    ϸ  (            )
   private ImageIcon slideIc; //      ̵     
   private ImageIcon hitIc; //  ε         
   private int num;
}