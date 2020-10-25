package ru.rsreu.vasilev.dd.model;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.view.Listener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Model {
    private final List<Listener> listenerList;
    private final List<Car> cars;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private final int offsetX = 50;
    private final int startPositionY = 100;
    private final int countCars = 5;
    private Car player;
    private Listener gameListener;

    public Model() {
        listenerList = new ArrayList<>();
        cars = new ArrayList<>();
    }

    public void initialize(Listener gameListener) {
        this.gameListener = gameListener;
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
