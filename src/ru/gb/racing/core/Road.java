package ru.gb.racing.core;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c, Racing racing) {
        try {
            racing.onStartCarStage(c, this);
            Thread.sleep(length / c.getSpeed() * 1000);
            racing.onEndCarStage(c, this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

