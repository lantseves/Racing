package ru.gb.racing.core;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Racing {
    private AtomicBoolean isWin ;
    private RacingListener listener ;
    private CyclicBarrier startingBarrier ;
    private CountDownLatch downLatch ;

    private Race race ;
    private Car[] cars ;

    public Racing(Race race, int carsCount, RacingListener listener) {
        this.listener = listener ;
        this.startingBarrier = new CyclicBarrier(carsCount, listener::onStartRace) ;
        this.race = race ;
        this.cars = new Car[carsCount] ;
        this.downLatch = new CountDownLatch(carsCount);
        this.isWin = new AtomicBoolean(false);
        listener.onStartPreparationRace();
        this.runRacing();
    }

    public void runRacing() {
        try {
            initCars() ;
            startCars();
            downLatch.await();
            listener.onEndRace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initCars() {
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), this);
        }
    }

    private void startCars() {
        for (Car car : cars) {
            new Thread(car).start();
        }
    }

    public void onCarPreparation(Car car) {
        listener.onCarPreparation(car);
    }

    public void onCarReady(Car car) {
        listener.onCarReady(car);
        try {
            startingBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void onCarWin(Car car) {
        if (!isWin.getAndSet(true))
        listener.onCarWin(car);
        downLatch.countDown();
    }

    public void onPreparingCarStage(Car car, Stage stage) {
        listener.onPreparingCarStage(car, stage);
    }

    public void onStartCarStage(Car car, Stage stage) {
        listener.onStartCarStage(car, stage);
    }

    public void onEndCarStage(Car car, Stage stage) {
        listener.onEndCarStage(car, stage);
    }
}
