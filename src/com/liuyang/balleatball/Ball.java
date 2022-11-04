package com.liuyang.balleatball;

import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private int d;
    private Color color;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    //定义一个方法判断 两个球是否发生了碰撞
    public static boolean BallIsCrashed(Ball b1,Ball b2){
        // 两个圆心之间的距离 和 半径之和 进行对比
        //1 求两个圆心的坐标
        int centerX1 = b1.x + b1.d/2;
        int centerY1 = b1.y + b1.d/2;

        int centerX2 = b2.x + b2.d/2;
        int centerY2 = b2.y + b2.d/2;

        //2 求圆心之间的距离  (x1 - x2)^ 2 + (y1-y2)^2 开根号
        double result = Math.pow((centerX1 - centerX2),2) + Math.pow((centerY1 - centerY2),2);
        double centerLength = Math.sqrt(result);

        //3 判断是否碰上了
        int sumR = (b1.d + b2.d)/2;

        if (centerLength > sumR){
            return false;
        }else {
            return true;
        }
    }


}
