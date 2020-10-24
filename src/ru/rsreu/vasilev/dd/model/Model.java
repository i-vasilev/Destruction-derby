package ru.rsreu.vasilev.dd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.view.Listener;

public class Model {
    private final List<Listener> listenerList;
    private final List<Car> cars;
    private final Car player;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private final int offsetX = 50;
    private final int startPositionY = 100;
    private final int countCars = 5;

    public Model() {
        listenerList = new ArrayList<>();
        cars = new ArrayList<>();
        player = new Car(countCars * offsetX, startPositionY, true);
    }

    public void initialize() {
        for (int i = 1; i < countCars; i++) {
            final Car car = new Car(i * offsetX, startPositionY);
            cars.add(car);
        }
        cars.add(player);
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

    public void addKey(KeyCode keyCode) {
        pressedKeys.add(keyCode);
    }

    public void removeKey(KeyCode keyCode) {
        pressedKeys.remove(keyCode);
    }

    public void movePlayer(KeyCode direction) {
        switch (direction) {
            case UP:
                player.speedUp();
                break;
            case DOWN:
                player.speedDown();
                break;
            case RIGHT:
                player.speedXUp(Direction.RIGHT);
                break;
            case LEFT:
                player.speedXUp(Direction.LEFT);
                break;
        }
    }
}
