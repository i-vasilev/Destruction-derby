package ru.rsreu.vasilev.dd.model;

import java.util.ArrayList;
import java.util.List;

import ru.rsreu.vasilev.dd.view.Listener;

public class Model {
    private final List<Listener> listenerList;
    private final List<Car> cars;
    private final Car player;

    public Model() {
        listenerList = new ArrayList<>();
        cars = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            final Car car = new Car(i * 50, 100);
            cars.add(car);
        }
        player = new Car(250, 100, true);
    }

    public void addListener(Listener listener) {
        listenerList.add(listener);
        for (Car car : cars) {
            car.addListener(listener);
        }
        player.addListener(listener);
    }

    public void start() {
        for (Car car : cars) {
            car.start();
        }
//        player.start();
    }

    public void movePlayer(String direction) {
        switch (direction) {
            case "&":
                player.speedUp();
                break;
            case "(":
                player.speedDown();
                break;
            case "'":
                player.speedXUp(Direction.RIGHT);
                break;
            case "%":
                player.speedXUp(Direction.LEFT);
                break;
        }
    }
}
