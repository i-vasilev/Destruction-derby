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
    public Object handle(Object object, EventType type) {
        if (type == EventType.CREATE_CAR) {
            root.setOnKeyPressed(a -> ((Car) object).addKey(a.getCode()));
            root.setOnKeyReleased(a -> ((Car) object).removeKey(a.getCode()));
            return createCarView((Car) object);
        }
        return null;
    }

    public ObjectListener createCarView(Car car) {
        ObjectView objectView = new ObjectView(root);
        rectangles.add(objectView);
        return objectView;
    }
}
