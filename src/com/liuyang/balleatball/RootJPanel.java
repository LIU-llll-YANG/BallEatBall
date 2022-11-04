package com.liuyang.balleatball;

import javax.swing.*;

public class RootJPanel extends JPanel {
    //增加一个标签组件
    JLabel  bgImageJLable = new JLabel();
    GameJPanel gameJPanel = new GameJPanel();
    MenuJPanel menuJPanel = new MenuJPanel(gameJPanel);

    public RootJPanel() {
        this.setLayout(null);//设置绝对布局
        this.setBeautiful();//调用美化
        this.addZuJian();//调用添加组件
        gameJPanel.move(menuJPanel);
    }


    //定义一个方法完成组件的属性设置和美化操作
    public void setBeautiful(){
        //标签组件
        bgImageJLable.setIcon(Utils.getIcon("bg.jpg"));
        bgImageJLable.setBounds(0,0,1200,800);
    }

    public void addZuJian(){
        this.add(menuJPanel);
        this.add(gameJPanel);
        this.add(bgImageJLable);

    }
}
