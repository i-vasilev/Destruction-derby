package ru.rsreu.vasilev.dd.view;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ru.rsreu.vasilev.dd.model.Car;

public class ObjectView extends Rectangle implements ObjectListener {
    private final Pane root;

    public ObjectView(Pane root) {
        this.root = root;
        setWidth(10);
        setHeight(20);
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
                });
            }
        }
    }
}
