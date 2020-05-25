package ru.gb.racing.core;

import com.sun.javafx.sg.prism.web.NGWebView;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Racing racing;

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, Racing racing) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.racing = racing ;
    }
    @Override
    public void run() {
        try {
            racing.onCarPreparation(this);
            Thread.sleep(500 + (int)(Math.random() * 800));
            racing.onCarReady(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this , racing);
        }
        racing.onCarWin(this);
    }
}
