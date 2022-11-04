package com.liuyang.balleatball;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Utils {
    public static int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    public static int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
    public static final  BufferedImage welImage = Utils.getImage("welcome.png");
    public static final  BufferedImage iconImage = Utils.getImage("icon.png");
    public static final  BufferedImage gameoverImage = Utils.getImage("gameover.png");
    public static final  BufferedImage winImage = Utils.getImage("win.png");

    public static final  AudioClip bgMusic = Utils.getMusic("bgm.wav");
    public static final  AudioClip crashMusic = Utils.getMusic("crash.wav");
    public static final  AudioClip gameoverMusic = Utils.getMusic("gameover.wav");
    public static final  AudioClip winMusic = Utils.getMusic("win.wav");

    public static Color getScreenColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

    public static BufferedImage getImage(String imageName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Utils.class.getResource("../image/" + imageName));
        } catch (IOException e) {

        }
        return image;
    }

    public static ImageIcon getIcon(String imageName) {
        return new ImageIcon(Utils.class.getResource("../image/" + imageName));
    }

    public static AudioClip getMusic(String musicName){
        AudioClip music = Applet.newAudioClip(Utils.class.getResource("../music/" + musicName));
        return music;
    }

}
