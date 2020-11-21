package ru.rsreu.vasilev.dd.view;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ru.rsreu.vasilev.dd.model.Car;

public class CarView extends Rectangle implements ObjectListener {
    public static final int CAR_WIDTH = 10;
    public static final int CAR_HEIGHT = 20;
    private final Pane root;

    public CarView(Pane root) {
        this.root = root;
        setWidth(CAR_WIDTH);
        setHeight(CAR_HEIGHT);
        Platform.runLater(() -> root.getChildren().add(this));
    }

    @Override
    public void handle(Object object, ObjectEventType type) {
        if (object instanceof Car) {
            Car car = (Car) object;
            if (type == ObjectEventType.UPDATE) {
                Platform.runLater(() -> {
                    setTranslateX(car.getPosition().getX());
                    setTranslateY(root.getHeight() - car.getPosition().getY());
                    setRotate(car.getAngle());
                });
            }
        }
    }
}
