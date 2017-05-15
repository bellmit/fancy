package cn.fancy.ui;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Copyright(c)2015年,SIMPOTechnology.AllRightsReserved.SIMPOTechnology.CONFIDENTIAL
 */

/**
 *
 * @authorcaosheng
 * @version2016年8月26日
 */
public class AWTTest implements Serializable {



  private static final long serialVersionUID = 2054572776644100701L;

  public static void main(String[] args)
  {


    final Frame frame = new Frame();
    frame.setSize(800, 800);
    frame.setLocation(100, 100);
    frame.addWindowListener(

        new WindowAdapter() {

          @Override
          public void windowClosing(WindowEvent e)
          {
            System.exit(0);
          }
        });

    final TextArea ta = new TextArea();
    frame.add(ta);

    // 创建菜单栏
    MenuBar mb = new MenuBar();

    // 创建菜单
    Menu file = new Menu("File");
    Menu edit = new Menu("Edit");

    // 创建菜单项
    MenuItem mi1 = new MenuItem("Open");
    // 添加打开文件功能响应
    mi1.addActionListener(

        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e)
          {
            FileDialog fd = new FileDialog(frame, "打开文件", FileDialog.LOAD);
            fd.setVisible(true);

            String fileName = fd.getDirectory() + fd.getFile();
            if (fileName != null) {
              try {
                FileInputStream fis = new FileInputStream(fileName);
                byte[] buf = new byte[10 * 1024];

                try {
                  int len = fis.read(buf);
                  ta.append(new String(buf, 0, len));
                  fis.close();
                } catch (IOException e1) {
                  e1.printStackTrace();
                }

              } catch (FileNotFoundException e1) {
                e1.printStackTrace();
              }

            }
          }
        });
    MenuItem mi2 =

        new MenuItem("Save");
    MenuItem mi3 = new MenuItem("OtherSave");
    MenuItem mi4 = new MenuItem("Close");
    // 添加关闭响应
    mi4.addActionListener(

        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent arg0)
          {
            System.exit(0);
          }
        });
          MenuItem mi5 = new MenuItem("Cope");
          MenuItem mi6 = new MenuItem("Paste");
      
          file.add(mi1);
          file.add(mi2);
          file.add(mi3);
          file.add(mi4);
          edit.add(mi5);
          edit.add(mi6);
          mb.add(file);
          mb.add(edit);
          frame.setMenuBar(mb);
          frame.setVisible(true);
  }
}


class SwingFrame {

  public static void main(String[] args)
  {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton button = new JButton("ok");
    frame.getContentPane().add(button, BorderLayout.WEST);
    frame.setSize(800, 800);
    frame.setLocation(100, 100);
    frame.setVisible(true);
  }
}
