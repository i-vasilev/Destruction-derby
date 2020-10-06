package ru.rsreu.vasilev.dd.model;

import java.util.ArrayList;
import java.util.List;

import ru.rsreu.vasilev.dd.view.Listener;

public class Model {
    private final List<Listener> listenerList;
    private final List<Car> cars;

    public Model() {
        listenerList = new ArrayList<>();
        cars = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            final Car car = new Car(100, i * 100);
            cars.add(car);
        }
    }

    public void addListener(Listener listener) {
        listenerList.add(listener);
        for (Car car : cars) {
            car.addListener(listener);
        }
    }

    public void start() {
        for (Car car : cars) {
            car.start();
        }
    }
}
