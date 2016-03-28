/*package cn.fancy.test;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageTest1 {
  private static final String FOLDER = "d:\\test";
  public static void main(String[] args) {
    File fA = new File(FOLDER + "\\1.jpg");//����ͼƬ
    File fB = new File(FOLDER + "\\22.jpg");//logoͼƬ
    File fC = new File(FOLDER + "\\2.jpg");//logoͼƬ
    try {
        BufferedImage imA = ImageIO.read(fA);
        BufferedImage imB = ImageIO.read(fB);
        BufferedImage imC = ImageIO.read(fC);
        
        int[] arrayImA = new int[imA.getWidth()*imA.getHeight()];
        arrayImA = imA.getRGB(0, 0, imA.getWidth(), imA.getHeight(), arrayImA, 0, imA.getWidth());
        int[] arrayImB = new int[imB.getWidth()*imB.getHeight()];
        arrayImB = imB.getRGB(0, 0, imB.getWidth(), imB.getHeight(), arrayImB, 0, imB.getWidth());    
  
        
        BufferedImage newImg = new BufferedImage(imA.getWidth(), imA.getHeight()+imB.getHeight(), BufferedImage.TYPE_INT_RGB);
//      newImg.setRGB(0, 0, imA.getWidth(), imA.getHeight(), arrayImA, 0, imA.getWidth());
//      newImg.setRGB(imA.getWidth(), imA.getHeight(), imB.getWidth(), imB.getHeight(), arrayImB, 0, imB.getWidth());
        
        //alpha
        Graphics2D g = newImg.createGraphics();
        g.drawImage(imA, 0, 0, imA.getWidth(), imA.getHeight(),null);
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.5f));
        
        g.drawImage(imB, 20, 30, imB.getWidth(), imB.getHeight(), null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
    
        g.drawImage(imC, imC.getWidth(),700, 900, imC.getHeight(), null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        
        File out = new File(FOLDER + "\\new3.jpg");
        ImageIO.write(newImg, "jpg", out);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

}
*/