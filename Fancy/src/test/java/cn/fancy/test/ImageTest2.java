package cn.fancy.test;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageTest2 {
  private static final String FOLDER = "d:\\test";
  public static void main(String[] args) {
    File fA = new File(FOLDER + "\\1.jpg");//±³¾°Í¼Æ¬
    File fB = new File(FOLDER + "\\22.jpg");//±³¾°Í¼Æ¬
    File fC = new File(FOLDER + "\\2.jpg");//logoÍ¼Æ¬
    try {
//      File []fs=new File[]{new File(FOLDER + "\\11.jpg"),new File(FOLDER + "\\22.jpg")};
        BufferedImage imA = ImageIO.read(fA);
      
         BufferedImage imB = ImageIO.read(fB);
         BufferedImage imC = ImageIO.read(fC);
//         int[] arrayImA = new int[imA.getWidth()*imA.getHeight()];
//         arrayImA = imA.getRGB(0, 0, imA.getWidth(), imA.getHeight(), arrayImA, 0, imA.getWidth());
//         int[] arrayImB = new int[imB.getWidth()*imB.getHeight()];
//         arrayImB = imB.getRGB(0, 0, imB.getWidth(), imB.getHeight(), arrayImB, 0, imB.getWidth());
         
         BufferedImage newImg = new BufferedImage(imA.getWidth(), imA.getHeight(), BufferedImage.TYPE_INT_RGB);
//       newImg.setRGB(0, 0, imA.getWidth(), imA.getHeight(), arrayImA, 0, imA.getWidth());
//       newImg.setRGB(imA.getWidth(), imA.getHeight(), imB.getWidth(), imB.getHeight(), arrayImB, 0, imB.getWidth());
         
         //alpha
         Graphics2D g = newImg.createGraphics();
         g.drawImage(imA, 0, 0, imA.getWidth(), imA.getHeight(),null);
//         g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1.0f));
         g.drawImage(imB,20, 30, imB.getWidth(), imB.getHeight(), null);
         
         g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
         g.drawImage(imC, ((imA.getWidth()-imC.getWidth())/2),imA.getHeight()-imC.getHeight(), imC.getWidth(), imC.getHeight(), null);
         g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
          
         File out = new File(FOLDER + "\\new3.jpg");
         ImageIO.write(newImg, "jpg", out);
         System.out.println(ImageTest2.class.getResource("/"));
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

}
