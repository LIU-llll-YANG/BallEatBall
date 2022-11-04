package com.liuyang.balleatball;

import javax.swing.*;

public class GameStart {
    //1145 * 744
    public static void main(String[] args) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        }
        catch(Exception e)
        {
            //TODO exception
        }
        new GameJFrame();
    }
}
