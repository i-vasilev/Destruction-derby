package ru.rsreu.vasilev.dd.model;

import java.util.ArrayList;
import java.util.List;

import ru.rsreu.vasilev.dd.view.Listener;

public class Car extends Thread {

    private Point position;
    private List<Listener> listenerList;

    public Car(int x, int y) {
        this.position = new Point(x, y);
        listenerList = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        listenerList.add(listener);
    }

    @Override
    public void run() {
        try {
            while (true) {
                position.setY(position.getY() + 1);
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
