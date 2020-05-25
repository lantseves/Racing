package ru.gb.racing.core;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore semaphore ;
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore(2);
    }

    public Tunnel(int size) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore(size);
    }
    @Override
    public void go(Car c, Racing racing) {
        try {
            try {
                racing.onPreparingCarStage(c, this);
                semaphore.acquire();
                racing.onStartCarStage(c, this);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                racing.onEndCarStage(c, this);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

