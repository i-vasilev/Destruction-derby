package ru.rsreu.vasilev.dd.model;

import ru.rsreu.vasilev.dd.view.Listener;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Listener> listenerList;
    private final List<Car> cars;
    private final int offsetX = 50;
    private final int startPositionY = 100;
    private final int countCars = 5;
    private Car player;
    private Listener gameListener;

    public Model() {
        listenerList = new ArrayList<>();
        cars = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 1; i < countCars; i++) {
            final Car car = new Car(i * offsetX, startPositionY, gameListener);
            cars.add(car);
        }
        player = new Car(countCars * offsetX, startPositionY, gameListener, true);
        cars.add(player);
    }


    public void start() {
        for (Car car : cars) {
            car.start();
        }
    }

    public void setGameListener(Listener gameListener) {
        this.gameListener = gameListener;
    }

}
