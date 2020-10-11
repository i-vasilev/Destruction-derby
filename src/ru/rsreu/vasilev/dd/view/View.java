package ru.rsreu.vasilev.dd.view;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import ru.rsreu.vasilev.dd.controller.Controller;
import ru.rsreu.vasilev.dd.model.Car;

public class View implements Listener {
    private Controller controller;
    private Pane root;
    private ConcurrentMap<Long, Rectangle> rectangleMap;
    private List<Path> edges;

    public View(Controller controller, Pane root) {
        this.controller = controller;
        this.root = root;
        rectangleMap = new ConcurrentHashMap<>();
    }

    public void initialize() {this.controller.addListener(this);}

    @Override
    public void showCar(Car car) {
        Rectangle rectangle;
        if (rectangleMap.containsKey(car.getId())) {
            rectangle = rectangleMap.get(car.getId());
        } else {
            rectangle = new Rectangle();
            rectangle.setWidth(10);
            rectangle.setHeight(20);
            Platform.runLater(() -> root.getChildren().add(rectangle));
            rectangleMap.put(car.getId(), rectangle);
        }
        Platform.runLater(() -> {
            rectangle.setTranslateX(car.getPosition().getX());
            rectangle.setTranslateY(root.getHeight() - car.getPosition().getY());
        });
    }

    @Override
    public List<Path> getEdges() {
        return edges;
    }
}
