package ru.rsreu.vasilev.dd.model;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.view.EventType;
import ru.rsreu.vasilev.dd.view.Listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Model {
    private final List<Car> cars;
    private boolean[][] map;
    private int height;
    private int width;
    private Car player;
    private Car player2;
    private Listener gameListener;
    private Point finalPoint;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private static final String MAP_FILENAME = "./res/map.txt";

    private static final int OFFSET_X = 11;
    private static final int START_POSITION_Y = 100;
    private static final int START_POSITION_X = 716;
    private static final int COUNT_CARS = 2;

    public Model() {
        cars = new ArrayList<>();
    }

    public void initialize() throws IOException {
        HashMap<Direction, KeyCode> keys = new HashMap<>();
        keys.put(Direction.UP, KeyCode.UP);
        keys.put(Direction.DOWN, KeyCode.DOWN);
        keys.put(Direction.RIGHT, KeyCode.RIGHT);
        keys.put(Direction.LEFT, KeyCode.LEFT);
        player = new Car(this, COUNT_CARS * OFFSET_X + START_POSITION_X, START_POSITION_Y,
                gameListener, keys);
        cars.add(player);
        keys = new HashMap<>();
        keys.put(Direction.UP, KeyCode.W);
        keys.put(Direction.DOWN, KeyCode.S);
        keys.put(Direction.RIGHT, KeyCode.D);
        keys.put(Direction.LEFT, KeyCode.A);
        player2 = new Car(this, OFFSET_X + START_POSITION_X, START_POSITION_Y,
                gameListener, keys);
        cars.add(player2);
        loadMap();
        gameListener.handle(this, EventType.INIT);
    }

    private void loadMap() throws IOException {
        final FileReader fileReader = new FileReader(MAP_FILENAME);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            if (line != null) {
                final String[] size = line.split(" ");
                int i = 0;
                int j;
                height = Integer.parseInt(size[0]);
                width = Integer.parseInt(size[1]);
                map = new boolean[height][width];
                line = reader.readLine();
                finalPoint = new Point(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));
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
    }

    public void start() {
        for (Car car : cars) {
            car.start();
        }
    }

    public void stopAllCars() {
        for (Car car : cars) {
            car.interrupt();
        }
        gameListener.handle(this, EventType.WIN);
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

    public void addKey(KeyCode keyCode) {
        pressedKeys.add(keyCode);
    }

    public void removeKey(KeyCode keyCode) {
        pressedKeys.remove(keyCode);
    }

    public Set<KeyCode> getPressedKeys() {
        return pressedKeys;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }
}
