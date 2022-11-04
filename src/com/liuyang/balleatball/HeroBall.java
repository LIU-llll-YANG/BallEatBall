package com.liuyang.balleatball;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HeroBall extends Ball implements MouseMotionListener {
    private int score ;
    private int life ;

    public HeroBall() {
        //初始化
        this.setX(300);
        this.setY(400);
        this.setD(20);
        this.setColor(Utils.getScreenColor());
        this.score = 0;
        this.life = 5;
    }

    public void paintHeroBall(Graphics g){
        g.setColor(getColor());
        g.fillOval(getX(),getY(),getD(),getD());
        g.setFont(new Font("微软雅黑",Font.PLAIN,15));
        g.drawString(this.getD()+"",this.getX(),this.getY());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        this.setX(mouseX-getD()/2);
        this.setY(mouseY-getD()/2);
    }
}
