package ru.gb.racing.ui;

import ru.gb.racing.core.*;

public class ConsoleUI implements RacingListener {


    public ConsoleUI() {
        new Racing(new Race(new Road(60), new Tunnel(), new Road(40)) , 4 , this) ;
    }

    @Override
    public void onStartPreparationRace() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
    }

    @Override
    public void onStartRace() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
    }

    @Override
    public void onEndRace() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    @Override
    public void onCarPreparation(Car car) {
        System.out.println(car.getName() + " готовится");
    }

    @Override
    public void onCarReady(Car car) {
        System.out.println(car.getName() + " готов");
    }

    @Override
    public void onCarWin(Car car) {
        System.out.println(car.getName()+ " - WIN");
    }

    @Override
    public void onPreparingCarStage(Car car, Stage stage) {
        System.out.println(car.getName() + " готовится к этапу(ждет): " + stage.getDescription());
    }

    @Override
    public void onStartCarStage(Car car, Stage stage) {
        System.out.println(car.getName() + " начал этап: " + stage.getDescription());
    }

    @Override
    public void onEndCarStage(Car car, Stage stage) {
        System.out.println(car.getName() + " закончил этап: " + stage.getDescription());
    }
}
