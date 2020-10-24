package ru.rsreu.vasilev.dd.model;

import java.util.ArrayList;
import java.util.List;

import ru.rsreu.vasilev.dd.view.Listener;

public class Car extends Thread {
    private Point position;
    private List<Listener> listenerList;
    private double speedY = 0;
    private double speedX = 0;
    private double acceleration = 0.05;
    private boolean isPlayUser = false;
    private boolean isNeedUpdate = true;

    public Car(int x, int y, boolean isPlayUser) {
        this(x, y);
        this.isPlayUser = isPlayUser;
    }

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
        try {
            while (true) {
                if (isNeedUpdate) {
                    if (!isPlayUser) {
                        speedUp();
                    } else {
                        isNeedUpdate = false;
                    }
                    position.setY(position.getY() + speedY);
                    position.setX(position.getX() + speedX);
                    for (Listener listener : listenerList) {
                        listener.showCar(this);
                    }
                } else {
                    speedDown();
                    speedXDown();
                }
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void speedUp() {
        isNeedUpdate = true;
        double maxSpeed = 5;
        if (maxSpeed > speedY) {
            speedY += acceleration;
            if (speedX > 0) {
                speedX -= acceleration;
            } else if (speedX < 0) {
                speedX += acceleration;
            }
        }
    }

    public void speedXUp(Direction direction) {
        if (direction == Direction.LEFT) {
            speedX -= acceleration;
        } else {
            speedX += acceleration;
        }
    }

    public void speedXDown() {
        if (speedX > 0) {
            speedX -= acceleration;
        } else if (speedX < 0) {
            speedX += acceleration;
        }
    }

    public void speedDown() {
        isNeedUpdate = true;
        if (speedY > 0) {
            speedY -= acceleration;
        }
    }

    public Point getPosition() {
        return position;
    }
}
