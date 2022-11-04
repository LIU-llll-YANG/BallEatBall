package com.liuyang.balleatball;

import java.awt.*;

public class EnemyBall extends Ball{
    //属性
    //属性
    private int dir;
    private int speed;

    //行为

    //球运动四个方向 这个四个方向 定义为常量 左上 --> 右上 --> 右下  --> 左下
    public static final int LEFT_UP = 1;
    public static final int RIGHT_UP = 2;
    public static final int RIGHT_DOWN = 3;
    public static final int LEFT_DOWN = 4;

    //构造方法完成属性初始化
    public EnemyBall(int level){
        //设置属性
        int x = (int)(Math.random()* 1115);
        int y = (int)(Math.random()* -200);;
        int d = (int)(Math.random()*50)+10;
        Color color = Utils.getScreenColor();
        this.setX(x);
        this.setY(y);
        this.setD(d);
        this.setColor(color);
        this.dir = (int)(Math.random()*4)+1;
        this.speed = level;
    }



    //绘制球的方法
    public void paintBall(Graphics g){
        g.setColor(getColor());
        g.fillOval(getX(),getY(),getD(),getD());
        g.setFont(new Font("微软雅黑",Font.PLAIN,15));
        g.drawString(this.getD()+"",this.getX(),this.getY());
    }


    //球运动的方法
    int date = 0;
    public void ballMove(){
        if (date % 50 == 0){
            dir = (int)(Math.random()*4)+1;
        }
        date++;
        //1 定义四个方向的坐标变化
        //左上
        int x = this.getX();
        int y = this.getY();
        if (dir == LEFT_UP) {
            this.setX(x-=speed);
            this.setY(y-=speed);
        }
        //右上
        if (dir == RIGHT_UP) {
            this.setX(x+=speed);
            this.setY(y-=speed);
        }
        //右下
        if (dir == RIGHT_DOWN) {
            this.setX(x+=speed);
            this.setY(y+=speed);
        }
        //左下
        if (dir == LEFT_DOWN) {
            this.setX(x-=speed);
            this.setY(y+=speed);
        }

        //上
        if (y <= 0) {
            //顺时针
            if (dir == RIGHT_UP) {
                dir = RIGHT_DOWN;
            }
            //逆时针
            if (dir == LEFT_UP) {
                dir = LEFT_DOWN;
            }
        }

        //右
        if (x >= 1115 - getD()) {
            //顺时针
            if (dir == RIGHT_DOWN) {
                dir = LEFT_DOWN;
            }
            //逆时针
            if (dir == RIGHT_UP) {
                dir = LEFT_UP;
            }
        }

        //触底
        if (y >= 581 - getD()) {
            //顺时针
            if (dir == LEFT_DOWN) {
                dir = LEFT_UP;
            }
            //逆时针
            if (dir == RIGHT_DOWN) {
                dir = RIGHT_UP;
            }

        }

        //左
        if (x <= 0) {
            //顺时针
            if (dir == LEFT_UP) {
                dir = RIGHT_UP;
            }
            //逆时针
            if (dir == LEFT_DOWN) {
                dir = RIGHT_DOWN;
            }
        }

    }


    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
