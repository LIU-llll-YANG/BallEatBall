package com.liuyang.balleatball;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameJPanel extends JPanel {
    //设置游戏的状态
    public static final int GAME_START = 1;
    public static final int GAME_RUNNING = 2;
    public static final int GAME_PAUSE = 3;
    public static final int GAME_RESTART = 4;
    public static final int GAME_OVER = 5;
    public static final int GAME_WIN = 6;
    int state = GAME_START;

    //创建敌人球集合
    int enemyCount = 10;
    ArrayList<EnemyBall> enemyBalls = new ArrayList<>();

    int level = 1;
    //英雄球对象
    HeroBall heroBall = new HeroBall();

    public GameJPanel() {
        this.setOpaque(false);//透明
        //this.setBackground(Color.gray);
        this.setBounds(10, 120, 1125, 590);
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        //添加对英雄球的监听
        this.addMouseMotionListener(heroBall);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制开始界面
        if (state == GAME_START) {
            g.drawImage(Utils.welImage, (1145 - Utils.welImage.getWidth()) / 2, (744 - Utils.welImage.getHeight()) / 2, null);
        }

        if (state == GAME_RUNNING || state == GAME_PAUSE) {
            //绘制敌人球
            for (int i = 0; i < enemyBalls.size(); i++) {
                EnemyBall enemyBall = enemyBalls.get(i);
                enemyBall.paintBall(g);
            }
            ////绘制英雄球
            heroBall.paintHeroBall(g);
        }
        //当游戏结束的时候 需要绘制
        if (state == GAME_OVER) {
            g.drawImage(Utils.gameoverImage, (1145 - Utils.gameoverImage.getWidth()) / 2, (744 - Utils.gameoverImage.getHeight()) / 2, null);
        }

        if (state == GAME_WIN) {
            g.drawImage(Utils.winImage, (1145 - Utils.winImage.getWidth()) / 2, (744 - Utils.winImage.getHeight()) / 2, null);
        }


    }

    public void eatBall() {
        //判断是否碰到
        //思想是遍历每个敌人球 判断是否和英雄球发生碰撞
        for (int i = 0; i < enemyBalls.size(); i++) {
            EnemyBall enemyBall = enemyBalls.get(i);
            boolean isCrashed = Ball.BallIsCrashed(enemyBall, heroBall);
            if (isCrashed) {
                Utils.crashMusic.play();
                //碰上了
                if (heroBall.getD() >= enemyBall.getD()) {// 英雄球的直径大于等于敌人球 --> 吃掉敌人球
                    //吃掉敌人球
                    enemyBalls.remove(enemyBall);//移除此球
                    //英雄球的直径变大 1
                    heroBall.setD(heroBall.getD() + 1);
                    //分数 + 1
                    heroBall.setScore(heroBall.getScore() + 1);
                } else {
                    //规则 生命值 -1  同时  直径 - 1
                    int life = heroBall.getLife();
                    if (life > 0) {
                        heroBall.setLife(--life);
                        int d = heroBall.getD();
                        if (d > 15) {
                            heroBall.setD(--d);
                        }
                        //敌人球消失
                        enemyBalls.remove(enemyBall);
                    } else {
                        //游戏结束
                        state = GameJPanel.GAME_OVER;
                        Utils.bgMusic.stop();
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {

                        }
                        Utils.gameoverMusic.play();

                    }
                }
            }
        }
    }


    public boolean isWin() {
        boolean flag = true;
        for (int i = 0; i < enemyBalls.size(); i++) {
            EnemyBall enemyBall = enemyBalls.get(i);
            if (enemyBall.getD() > heroBall.getD()) {
                flag = false;
            }
        }
        return flag;
    }

    //定义一个方法用于判断是否所有的敌人球直径都大于英雄球
    public boolean allEnemyBallIsBigThanHeroBall() {
        boolean flag = true;
        for (int i = 0; i < enemyBalls.size(); i++) {
            EnemyBall enemyBall = enemyBalls.get(i);
            //如果有一个比英雄球小 --> false
            if (enemyBall.getD() < heroBall.getD()) {
                flag = false;
            }
        }
        return flag;
    }

    public void addEnemyBall() {
        //完成为集合中添加敌人球对象
        for (int i = 0; i < enemyCount; i++) {
            enemyBalls.add(new EnemyBall(level));
        }
    }

    //定义一个方法实现组件的移动
    public void move(MenuJPanel menuJPanel) {

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    //1
                    //当运行的时候进行的活动
                    if (state == GAME_RUNNING) {
                        //敌人球移动
                        for (int i = 0; i < enemyBalls.size(); i++) {
                            EnemyBall enemyBall = enemyBalls.get(i);
                            enemyBall.ballMove();
                        }
                        //吃球
                        eatBall();
                        //补充敌人球
                        if (enemyBalls.size() < 10) {
                            enemyBalls.add(new EnemyBall(level));
                        }
                        //当所有的敌人球都大于英雄球
                        if (allEnemyBallIsBigThanHeroBall()) {
                            enemyBalls.add(new EnemyBall(level));
                        }
                        //判断是否赢了
                        if (isWin()) {
                            state = GAME_WIN;
                            Utils.bgMusic.stop();
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {

                            }
                            Utils.winMusic.play();

                        }
                    }

                    //当游戏结束 或者胜利的时候的时候更改一些按钮的状态
                    if (state == GAME_OVER || state == GAME_WIN) {
                        menuJPanel.saveButton.setEnabled(true);
                        menuJPanel.pauseButton.setEnabled(false);
                    }


                    //2
                    repaint();
                    menuJPanel.repaint();
                    //3
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }
}
