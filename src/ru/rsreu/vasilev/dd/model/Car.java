package ru.rsreu.vasilev.dd.model;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.view.EventType;
import ru.rsreu.vasilev.dd.view.Listener;
import ru.rsreu.vasilev.dd.view.ObjectEventType;
import ru.rsreu.vasilev.dd.view.ObjectListener;

import java.util.HashSet;
import java.util.Set;

public class Car extends Thread {
    private final Point position;
    private final Listener listenerGame;
    private final ObjectListener objectListener;
    private double speedY = 0;
    private double speedX = 0;
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
        objectListener = (ObjectListener) listenerGame.handle(this, EventType.CREATE_CAR);
    }

    @Override
    public void run() {
        while (true) {
            if (isNeedUpdate) {
                if (!isPlayUser) {
                    speedUp();
                } else {
                    isNeedUpdate = false;
                    updateSpeeds();
                }
                position.setY(position.getY() + speedY);
                position.setX(position.getX() + speedX);
                objectListener.handle(this, ObjectEventType.UPDATE);
            } else {
                speedDown();
                speedXDown();
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
            speedUp();
        } else if (pressedKeys.contains(KeyCode.DOWN)) {
            speedDown();
        }
        if (pressedKeys.contains(KeyCode.RIGHT)) {
            speedXUp(XDirection.RIGHT);
        } else if (pressedKeys.contains(KeyCode.LEFT)) {
            speedXUp(XDirection.LEFT);
        }
    }

    public void speedUp() {
        isNeedUpdate = true;
        if (MAX_SPEED > speedY) {
            speedY += ACCELERATION;
            if (speedX > 0) {
                speedX -= ACCELERATION / 2;
            } else if (speedX < 0) {
                speedX += ACCELERATION / 2;
            }
        }
    }

    public void speedXUp(XDirection xDirection) {
        if (xDirection == XDirection.LEFT) {
            speedX -= ACCELERATION;
        } else {
            speedX += ACCELERATION;
        }
    }

    public void speedXDown() {
        if (speedX > 0) {
            speedX -= ACCELERATION;
        } else if (speedX < 0) {
            speedX += ACCELERATION;
        }
    }

    public void speedDown() {
        isNeedUpdate = true;
        if (speedY > 0) {
            speedY -= ACCELERATION;
        }
    }

    public Point getPosition() {
        return position;
    }

    public void addKey(KeyCode keyCode) {
        pressedKeys.add(keyCode);
    }

    public void removeKey(KeyCode keyCode) {
        pressedKeys.remove(keyCode);
    }
}
