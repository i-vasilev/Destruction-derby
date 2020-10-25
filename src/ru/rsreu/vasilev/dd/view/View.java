package ru.rsreu.vasilev.dd.view;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ru.rsreu.vasilev.dd.controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class View implements Listener {
    private Controller controller;
    private Pane root;
    private List<Rectangle> rectangles;

    public View(Controller controller, Pane root) {
        this.controller = controller;
        this.root = root;
        rectangles = new ArrayList<>();
    }

    @Override
    public Object handle(Object object, EventType type) {
        if (type == EventType.CREATE_CAR) {
            return createCarView();
        }
        return null;
    }

    public ObjectListener createCarView() {
        ObjectView objectView = new ObjectView(root);
        rectangles.add(objectView);
        return objectView;
    }
}
