package cn.fancy.test;

import java.io.*;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class ImageTest {
  private static final String FOLDER = "d:\\test";
  public static void main(String[] args) {
    File fA = new File(FOLDER + "\\1.jpg");//背景图片
    try {
      File []fs=new File[]{new File(FOLDER + "\\11.jpg"),new File(FOLDER + "\\22.jpg")};
        BufferedImage imA = ImageIO.read(fA);
        for (int i = 0; i < fs.length; i++) {
         File f= fs[i];
         BufferedImage imB = ImageIO.read(f);
         
         int[] arrayImA = new int[imA.getWidth()*imA.getHeight()];
         arrayImA = imA.getRGB(0, 0, imA.getWidth(), imA.getHeight(), arrayImA, 0, imA.getWidth());
         int[] arrayImB = new int[imB.getWidth()*imB.getHeight()];
         arrayImB = imB.getRGB(0, 0, imB.getWidth(), imB.getHeight(), arrayImB, 0, imB.getWidth());
         
         BufferedImage newImg = new BufferedImage(imA.getWidth(), imA.getHeight(), BufferedImage.TYPE_INT_RGB);
//       newImg.setRGB(0, 0, imA.getWidth(), imA.getHeight(), arrayImA, 0, imA.getWidth());
//       newImg.setRGB(imA.getWidth(), imA.getHeight(), imB.getWidth(), imB.getHeight(), arrayImB, 0, imB.getWidth());
         
         //alpha
         Graphics2D g = newImg.createGraphics();
         g.drawImage(imA, 0, 0, imA.getWidth(), imA.getHeight(),null);
//         g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1.0f));
         g.drawImage(imB,20, 30, imB.getWidth(), imB.getHeight(), null);
         g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
         
         File out = new File(FOLDER + "\\new3.jpg");
         ImageIO.write(newImg, "jpg", out);
        }
       
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

}
