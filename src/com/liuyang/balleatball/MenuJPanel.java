package com.liuyang.balleatball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuJPanel extends JPanel {
    GameJPanel gameJPanel;
    MenuJPanel menuJPanel = this;

    JButton startButton = new JButton("开始游戏");
    JButton pauseButton = new JButton("暂停游戏");
    JButton restartButton = new JButton("重新开始");
    JButton saveButton = new JButton("存档");
    JButton confirmButton = new JButton("确认");

    //增加难度按钮
    //简单  一般  困难  --> 单选
    JRadioButton easy = new JRadioButton(" 简  单 ");
    JRadioButton nomal = new JRadioButton(" 一  般 ");
    JRadioButton hard = new JRadioButton(" 困  难 ");

    //增加一个用户名输入框
    JLabel userNameJLable = new JLabel("玩 家 姓 名");
    JTextField userName = new JTextField("刘洋");

    public MenuJPanel(GameJPanel gamePanel) {
        this.gameJPanel = gamePanel;
        this.setLayout(null);
        this.setOpaque(false);//透明
        //this.setBackground(Color.gray);
        this.setBounds(10, 10, 1125, 100);
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //
        setZuJian();
        addZuJian();
        addListener();
        selectLevel();//决定等级
    }

    public void setZuJian() {
        int addX = 105;
        startButton.setBounds(20, 20, 100, 65);
        startButton.setBorder(null);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        startButton.setForeground(Color.gray);
        startButton.setEnabled(false);

        pauseButton.setBounds(20 + (addX * 1), 20, 100, 65);
        pauseButton.setBorder(null);
        pauseButton.setFocusPainted(false);
        pauseButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        pauseButton.setForeground(Color.gray);
        pauseButton.setEnabled(false);

        restartButton.setBounds(20 + (addX * 2), 20, 100, 65);
        restartButton.setBorder(null);
        restartButton.setFocusPainted(false);
        restartButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        restartButton.setForeground(Color.gray);
        restartButton.setEnabled(false);

        saveButton.setBounds(20 + (addX * 3), 20, 100, 65);
        saveButton.setBorder(null);
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        saveButton.setForeground(Color.gray);
        saveButton.setEnabled(false);

        confirmButton.setBounds(30 + (addX * 5), 20, 100, 65);
        confirmButton.setBorder(null);
        confirmButton.setFocusPainted(false);
        confirmButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        confirmButton.setForeground(Color.gray);

        //用户名 和输入框 设置
        userNameJLable.setFont(new Font("微软雅黑", Font.BOLD, 20));
        userNameJLable.setBounds(20 + (addX * 4) + 10, 20, 100, 30);
        userNameJLable.setForeground(Color.YELLOW);

        userName.setBounds(20 + (addX * 4) + 10, 50, 100, 30);
        userName.setBorder(null);
        userName.setFont(new Font("微软雅黑", Font.BOLD, 20));
        userName.setHorizontalAlignment(JButton.CENTER);//设置输入框文本居中
        //userName.setOpaque(false);

        //单选按钮设置
        int w = 100;
        easy.setBounds(620 + w, 10, 100, 30);
        easy.setOpaque(false);
        easy.setForeground(Color.white);
        easy.setFont(new Font("微软雅黑", Font.BOLD, 20));
        easy.setFocusPainted(false);
        easy.setSelected(true);

        nomal.setBounds(620 + w, 35, 100, 30);
        nomal.setOpaque(false);
        nomal.setForeground(Color.white);
        nomal.setFont(new Font("微软雅黑", Font.BOLD, 20));
        nomal.setFocusPainted(false);

        hard.setBounds(620 + w, 60, 100, 30);
        hard.setOpaque(false);
        hard.setForeground(Color.white);
        hard.setFont(new Font("微软雅黑", Font.BOLD, 20));
        hard.setFocusPainted(false);

        //将 简单和一般按钮  绑定为一组
        ButtonGroup group = new ButtonGroup();
        group.add(easy);
        group.add(nomal);
        group.add(hard);


    }

    public void addZuJian() {
        //添加按钮
        this.add(startButton);
        this.add(pauseButton);
        this.add(restartButton);
        this.add(saveButton);
        this.add(confirmButton);

        //添加 用户名
        this.add(userNameJLable);
        this.add(userName);


        //添加难度单选框
        this.add(easy);
        this.add(nomal);
        this.add(hard);
    }

    //定义一个方法用于为按钮增加监听事件
    public void addListener() {
        //开始按钮
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //改变游戏的状态
                gameJPanel.state = GameJPanel.GAME_RUNNING;//运行状态
                //设置按钮不可以使用了
                startButton.setEnabled(false);
                //设置输入框不可以输入
                userName.setEnabled(false);
                pauseButton.setEnabled(true);
                restartButton.setEnabled(true);
                selectLevel();
                gameJPanel.addEnemyBall();
                Utils.bgMusic.loop();
            }
        });
        //暂停按钮
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameJPanel.state == GameJPanel.GAME_RUNNING) {
                    //更改状态
                    gameJPanel.state = GameJPanel.GAME_PAUSE;
                    //修改文字
                    pauseButton.setText("恢复游戏");
                } else {
                    gameJPanel.state = GameJPanel.GAME_RUNNING;
                    pauseButton.setText("暂停游戏");
                }
            }
        });
        //重新开始
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更改游戏的状态
                gameJPanel.state = GameJPanel.GAME_START;
                //将所有的数据重置
                //敌人球的集合直接清空
                gameJPanel.enemyBalls.clear();
                //英雄球重置
                gameJPanel.heroBall = new HeroBall();
                gameJPanel.addMouseMotionListener(gameJPanel.heroBall);//重新的添加监听
                //开始按钮可以使用
                startButton.setEnabled(true);
                selectLevel();
                //设置 重新开始 和 暂停按钮不可以使用
                restartButton.setEnabled(false);
                pauseButton.setEnabled(false);
                saveButton.setEnabled(false);
            }
        });
        //存档 --> 待写
        //确认按钮添加 监听事件
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1 获取输入框中的内容
                String user = userName.getText();
                //2 判断这个用户名是否符合规则
                String reg = "^(?:[\\u4e00-\\u9fa5·]{2,5})$";
                if (user.matches(reg)) {
                    // 设置 开始按钮可以使用
                    startButton.setEnabled(true);
                    // 设置此输入框 和 确认按钮不能用
                    userName.setEnabled(false);
                    confirmButton.setEnabled(false);

                } else {
                    JOptionPane.showMessageDialog(menuJPanel, "请输入2--5位中文姓名");
                }
            }
        });


    }

    public void selectLevel() {
        //查看哪个按钮被选中了  true
        if (easy.isSelected()) {
            gameJPanel.level = 1;
        }
        if (nomal.isSelected()) {
            gameJPanel.level = 3;
        }
        if (hard.isSelected()) {
            gameJPanel.level = 5;
        }

    }

    //重写paint方法

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制矩形
        g.setColor(Color.gray);
        g.fillRect(870, 5, 300, 200);
        //绘制分数
        g.setColor(Color.yellow);
        g.setFont(new Font("Impact", Font.BOLD, 18));
        g.drawString("S O C R E  :   " + gameJPanel.heroBall.getScore(), 900, 30);
        g.drawString("L   I   F   E  :   " + gameJPanel.heroBall.getLife(), 900, 60);
        g.drawString("BEST SCORE :    ", 900, 90);


    }
}
