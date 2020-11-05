package ru.rsreu.vasilev.dd.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.vasilev.dd.view.EventType;
import ru.rsreu.vasilev.dd.view.Listener;

public class Model {
    private final List<Car> cars;
    private boolean[][] map;
    private int height;
    private int width;
    private Car player;
    private Listener gameListener;

    private static final int OFFSET_X = 11;
    private static final int START_POSITION_Y = 100;
    private static final int START_POSITION_X = 705;
    private static final int COUNT_CARS = 5;

    public Model() {
        cars = new ArrayList<>();
    }

    public void initialize() throws IOException {
        for (int i = 1; i < COUNT_CARS; i++) {
            final Car car =
                    new Car(this, i * OFFSET_X + START_POSITION_X, START_POSITION_Y, gameListener);
            cars.add(car);
        }
        player = new Car(this, COUNT_CARS * OFFSET_X + START_POSITION_X, START_POSITION_Y,
                gameListener, true);
        cars.add(player);
        loadMap();
        gameListener.handle(this, EventType.INIT);
    }

    private void loadMap() throws IOException {
        final FileReader fileReader = new FileReader("./res/map.txt");
        final BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        if (line != null) {
            final String[] size = line.split(" ");
            int i = 0;
            int j;
            height = Integer.parseInt(size[0]);
            width = Integer.parseInt(size[1]);
            map = new boolean[height][width];
            line = reader.readLine();
            while (line != null) {
                j = 0;
                for (String sign : line.split(" ")) {
                    map[i][j] = sign.equals("1");
                    j++;
                }
                i++;
                line = reader.readLine();
            }
        }
    }

    public void start() {
        for (Car car : cars) {
            car.start();
        }
    }

    public void setGameListener(Listener gameListener) {
        this.gameListener = gameListener;
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
}
