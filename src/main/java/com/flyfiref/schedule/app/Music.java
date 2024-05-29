package com.flyfiref.schedule.app;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;

public class Music extends Thread{
    private BufferedInputStream msc;
    public Music(String type){
        msc= new BufferedInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(type));
    }
    public void run(){
        try {
            Player pLayer=new Player(msc);
            pLayer.play();
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }
}

