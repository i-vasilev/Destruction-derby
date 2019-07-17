package ru.rsreu.vasilev.dd.model;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.view.EventType;
import ru.rsreu.vasilev.dd.view.Listener;
import ru.rsreu.vasilev.dd.view.ObjectEventType;
import ru.rsreu.vasilev.dd.view.ObjectListener;

import java.util.HashSet;
import java.util.Set;

public class Car extends Thread {
    public static final double ANGLE_CHANGE = 0.1;
    private final Point position;
    private final Listener listenerGame;
    private ObjectListener objectListener;
    private double speed = 0;
    private double angle = 0;
    private static final double ACCELERATION = 0.4;
    private boolean isPlayUser = false;
    private boolean isNeedUpdate = true;
    private static final double MAX_SPEED = 10;
    private final Set<KeyCode> pressedKeys = new HashSet<>();

    public Car(int x, int y, Listener listener, boolean isPlayUser) {
        this(x, y, listener);
        this.isPlayUser = isPlayUser;
    }

    public Car(int x, int y, Listener listener) {
        this.position = new Point(x, y);
        setDaemon(true);
        listenerGame = listener;
        listenerGame.handle(this, EventType.CREATE_CAR);
    }

    @Override
    public void run() {
        while (true) {
            if (isNeedUpdate) {
                if (!isPlayUser) {
                    speedUp(YDirection.FORWARD);
                } else {
                    isNeedUpdate = false;
                    updateSpeeds();
                }
                double speedY = Math.cos(angle) * speed;
                double speedX = Math.sin(angle) * speed;
                position.setY(position.getY() + speedY);
                position.setX(position.getX() + speedX);
                objectListener.handle(this, ObjectEventType.UPDATE);
            } else {
                speedDown();
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateSpeeds() {
        if (pressedKeys.contains(KeyCode.UP)) {
            speedUp(YDirection.FORWARD);
        } else if (pressedKeys.contains(KeyCode.DOWN)) {
            speedUp(YDirection.BACKWARD);
        }
        if (pressedKeys.contains(KeyCode.RIGHT)) {
            rotate(XDirection.RIGHT);
        } else if (pressedKeys.contains(KeyCode.LEFT)) {
            rotate(XDirection.LEFT);
        }
    }

    public void speedUp(YDirection yDirection) {
        isNeedUpdate = true;
        if (MAX_SPEED > speed) {
            speed += ACCELERATION * (yDirection == YDirection.BACKWARD ? -1 : 1);
        }
    }

    public void rotate(XDirection xDirection) {
        if (xDirection == XDirection.LEFT) {
            angle -= ANGLE_CHANGE;
        } else {
            angle += ANGLE_CHANGE;
        }
    }

    public void speedDown() {
        isNeedUpdate = true;
        if (speed > 0) {
            speed -= ACCELERATION;
        }
    }

    public Point getPosition() {
        return position;
    }

    public void setObjectListener(ObjectListener objectListener) {
        this.objectListener = objectListener;
    }

    public double getAngle() {
        return (angle * 180) / Math.PI;
    }

    public void addKey(KeyCode keyCode) {
        pressedKeys.add(keyCode);
    }

    public void removeKey(KeyCode keyCode) {
        pressedKeys.remove(keyCode);
    }
}
