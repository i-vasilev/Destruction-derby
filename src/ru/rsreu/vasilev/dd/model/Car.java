package ru.rsreu.vasilev.dd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.rsreu.vasilev.dd.view.Listener;

public class Car extends Thread {
    private Point position;
    private List<Listener> listenerList;

    public Car(int x, int y) {
        this.position = new Point(x, y);
        listenerList = new ArrayList<>();
        setDaemon(true);
    }

    public void addListener(Listener listener) {
        listenerList.add(listener);
    }

    @Override
    public void run() {
        Random rnd = new Random();
        double speed = rnd.nextDouble();
        try {
            while (true) {
                position.setY(position.getY() + speed);
                for (Listener listener : listenerList) {
                    listener.showCar(this);
                }
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Point getPosition() {
        return position;
    }
}
