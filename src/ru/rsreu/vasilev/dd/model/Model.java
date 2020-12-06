package ru.rsreu.vasilev.dd.model;

import ru.rsreu.vasilev.dd.view.EventType;
import ru.rsreu.vasilev.dd.view.Listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Serializable {
    private static final transient String MAP_FILENAME = "./res/map.txt";
    private static final transient int OFFSET_X = 30;
    private static final transient int START_POSITION_Y = 100;
    private static final transient int COUNT_CARS = 2;
    public static final transient int START_POSITION_X = 700;
    public static final transient Object BLOCK = new Object();
    private final List<Car> cars;
    private transient Listener gameListener;
    private boolean[][] map;
    private int height;
    private int width;
    private final Car player;
    private final Car player2;
    private Point finalPoint;
    private boolean pause;


    public Model() throws IOException {
        cars = new ArrayList<>();
        player = new Car(this,
                COUNT_CARS * OFFSET_X + START_POSITION_X,
                START_POSITION_Y,
//                gameListener,
                "Car 2");
        cars.add(player);
        player2 = new Car(this,
                OFFSET_X + START_POSITION_X,
                START_POSITION_Y,
//                gameListener,
                "Car 1");
        cars.add(player2);
        loadMap();
    }

    public void initialize() {
        gameListener.handle(this, EventType.INIT);
        cars.forEach(Car::initialize);
    }

    private void loadMap() throws IOException {
        final FileReader fileReader = new FileReader(MAP_FILENAME);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            if (line != null) {
                String[] splittedLine = line.split(" ");
                int i = 0;
                int j;
                height = Integer.parseInt(splittedLine[0]);
                width = Integer.parseInt(splittedLine[1]);
                map = new boolean[height][width];
                line = reader.readLine();
                splittedLine = line.split(" ");
                finalPoint = new Point(Integer.parseInt(splittedLine[0]),
                        Integer.parseInt(splittedLine[1]));
                line = reader.readLine();
                while (line != null) {
                    j = 0;
                    splittedLine = line.split(" ");
                    for (String sign : splittedLine) {
                        map[i][j] = sign.equals("1");
                        j++;
                    }
                    i++;
                    line = reader.readLine();
                }
            }
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public void start() {
        for (Car car : cars) {
            car.start();
        }
    }

    public void stopAllCars(Car wonCar) {
        stopAllCars();
        gameListener.handle(wonCar, EventType.WIN);
    }

    public void stopAllCars() {
        for (Car car : cars) {
            car.setStopped(true);
        }
    }

    public void setGameListener(Listener gameListener) {
        this.gameListener = gameListener;
        for (Car car :
                cars) {
            car.setGameListener(gameListener);
        }
    }

    public boolean[][] getMap() {
        return map;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }

    public Car getFirstCar() {
        return player;
    }

    public Car getSecondCar() {
        return player2;
    }

    public void pause() {
        synchronized (BLOCK) {
            if (pause) {
                BLOCK.notifyAll();
            } else {
                gameListener.handle(this, EventType.PAUSE);
            }
            pause = !pause;
        }
    }

    public boolean isPause() {
        return pause;
    }
}
