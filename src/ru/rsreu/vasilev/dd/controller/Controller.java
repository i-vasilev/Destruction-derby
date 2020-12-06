package ru.rsreu.vasilev.dd.controller;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.model.Direction;
import ru.rsreu.vasilev.dd.model.Model;
import ru.rsreu.vasilev.dd.view.Listener;

import java.io.*;

public class Controller {
    private Model model;
    private Listener listener;

    public Controller(Model model) {
        this.model = model;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
        model.setGameListener(listener);
    }

    public void addKey(KeyCode keyCode) {
        if (keyCode == KeyCode.ESCAPE) {
            model.pause();
        }
        if (keyCode == KeyCode.RIGHT) {
            model.getFirstCar().getDirections().add(Direction.RIGHT);
        }
        if (keyCode == KeyCode.UP) {
            model.getFirstCar().getDirections().add(Direction.UP);
        }
        if (keyCode == KeyCode.LEFT) {
            model.getFirstCar().getDirections().add(Direction.LEFT);
        }
        if (keyCode == KeyCode.DOWN) {
            model.getFirstCar().getDirections().add(Direction.DOWN);
        }
        if (keyCode == KeyCode.D) {
            model.getSecondCar().getDirections().add(Direction.RIGHT);
        }
        if (keyCode == KeyCode.W) {
            model.getSecondCar().getDirections().add(Direction.UP);
        }
        if (keyCode == KeyCode.A) {
            model.getSecondCar().getDirections().add(Direction.LEFT);
        }
        if (keyCode == KeyCode.S) {
            model.getSecondCar().getDirections().add(Direction.DOWN);
        }
    }

    public void removeKey(KeyCode keyCode) {
        if (keyCode == KeyCode.RIGHT) {
            model.getFirstCar().getDirections().remove(Direction.RIGHT);
        }
        if (keyCode == KeyCode.UP) {
            model.getFirstCar().getDirections().remove(Direction.UP);
        }
        if (keyCode == KeyCode.LEFT) {
            model.getFirstCar().getDirections().remove(Direction.LEFT);
        }
        if (keyCode == KeyCode.DOWN) {
            model.getFirstCar().getDirections().remove(Direction.DOWN);
        }
        if (keyCode == KeyCode.D) {
            model.getSecondCar().getDirections().remove(Direction.RIGHT);
        }
        if (keyCode == KeyCode.W) {
            model.getSecondCar().getDirections().remove(Direction.UP);
        }
        if (keyCode == KeyCode.A) {
            model.getSecondCar().getDirections().remove(Direction.LEFT);
        }
        if (keyCode == KeyCode.S) {
            model.getSecondCar().getDirections().remove(Direction.DOWN);
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void openFile(File file) {
        model.stopAllCars();
        model = (Model) openObjectFromFile(file.getAbsolutePath());
        if (model != null) {
            model.setGameListener(listener);
            model.initialize();
            model.start();
        }
    }

    private Object openObjectFromFile(String path) {
        try (FileInputStream fis = new FileInputStream(path)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public void saveFile(File file) {
        saveObjectToFile(model, file.getAbsolutePath());
    }

    void saveObjectToFile(Object objForSaving, String path) {
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(objForSaving);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
