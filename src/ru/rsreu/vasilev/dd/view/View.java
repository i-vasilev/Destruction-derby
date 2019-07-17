package ru.rsreu.vasilev.dd.view;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ru.rsreu.vasilev.dd.controller.Controller;
import ru.rsreu.vasilev.dd.model.Car;

import java.util.ArrayList;
import java.util.List;

public class View implements Listener {
    private final Controller controller;
    private final Pane root;
    private final List<Rectangle> rectangles;

    public View(Controller controller, Pane root) {
        this.controller = controller;
        this.root = root;
        rectangles = new ArrayList<>();
    }

    @Override
    public void handle(Object object, EventType type) {
        if (type == EventType.CREATE_CAR) {
            Car car = (Car) object;
            root.setOnKeyPressed(a -> car.addKey(a.getCode()));
            root.setOnKeyReleased(a -> car.removeKey(a.getCode()));
            car.setObjectListener(createCarView());
        }
        return;
    }

    public ObjectListener createCarView() {
        CarView carView = new CarView(root);
        rectangles.add(carView);
        return carView;
    }
}
