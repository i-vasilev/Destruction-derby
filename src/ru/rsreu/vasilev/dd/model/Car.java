package ru.rsreu.vasilev.dd.model;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.view.EventType;
import ru.rsreu.vasilev.dd.view.Listener;
import ru.rsreu.vasilev.dd.view.ObjectEventType;
import ru.rsreu.vasilev.dd.view.ObjectListener;
import ru.rsreu.vasilev.dd.view.View;

public class Car extends Thread {
    public static final double ANGLE_CHANGE = 0.2;
    public static final int TIME_SLEEP = 20;
    private final Point position;
    private final Listener listenerGame;
    private ObjectListener objectListener;
    private double speed = 0;
    private double angle = 0;
    private static final double ACCELERATION = 0.3;
    private boolean isPlayUser = false;
    private boolean isNeedUpdate = true;
    private static final double MAX_SPEED = 6;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private final Model modelGame;

    public Car(Model modelGame, int x, int y, Listener listener, boolean isPlayUser) {
        this(modelGame, x, y, listener);
        this.isPlayUser = isPlayUser;
    }

    public Car(Model modelGame, int x, int y, Listener listener) {
        this.position = new Point(x, y);
        this.modelGame = modelGame;
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
                moveCar(speedY, speedX);
                objectListener.handle(this, ObjectEventType.UPDATE);
            } else {
                speedDown();
            }
            try {
                sleep(TIME_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void moveCar(double speedY, double speedX) {
        double newY = position.getY() + speedY;
        double newX = position.getX() + speedX;
        if (modelGame.getHeight() > ((int)newY / View.WIDTH_SQUARE - 1) &&
                modelGame.getHeight() > ((int)newY / View.WIDTH_SQUARE) && newY > 0 &&
                modelGame.getWidth() > (int)newX / View.WIDTH_SQUARE && newX >= 0 &&
                modelGame.getMap()[modelGame.getHeight() - (int)newY / View.WIDTH_SQUARE - 1][
                        (int)newX / View.WIDTH_SQUARE]) {
            position.setY(newY);
            position.setX(newX);
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
        } else if (speed < 0) {
            speed += ACCELERATION;
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
