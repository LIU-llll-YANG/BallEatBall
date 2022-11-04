package com.liuyang.balleatball;

import javax.swing.*;

public class GameJFrame extends JFrame {
    RootJPanel rootJPanel = new RootJPanel();
    public GameJFrame() {
        //this.getContentPane().setLayout(null);
        this.setTitle("大球吃小球");
        this.setSize(1200,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(Utils.iconImage);
        this.setLocationRelativeTo(null);
        //this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.add(rootJPanel);
        this.setVisible(true);

    }
}
