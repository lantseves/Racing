package ru.gb.racing.core;
/*
Сделал, чтобы реализовывать UI независимо от логики гонки
 */
public interface RacingListener {
    void onStartPreparationRace() ;
    void onStartRace() ;
    void onEndRace() ;

    void onCarPreparation(Car car) ;
    void onCarReady(Car car) ;
    void onCarWin(Car car) ;

    void onPreparingCarStage(Car car, Stage stage) ;
    void onStartCarStage(Car car, Stage stage) ;
    void onEndCarStage(Car car, Stage stage) ;

}
