package ru.rsreu.vasilev.dd.view;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import ru.rsreu.vasilev.dd.model.Car;

public class CarView extends Polygon implements ObjectListener {
    public static final int CAR_WIDTH = 10;
    public static final int CAR_HEIGHT = 20;
    private final Pane root;

    public CarView(Pane root) {
        this.root = root;
        getPoints().addAll(0., 0.,
                10.0, 0.,
                10.0, 20.,
                0., 20.);
//        setWidth(CAR_WIDTH);
//        setHeight(CAR_HEIGHT);
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
