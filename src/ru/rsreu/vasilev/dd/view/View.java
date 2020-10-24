package ru.rsreu.vasilev.dd.view;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ru.rsreu.vasilev.dd.controller.Controller;
import ru.rsreu.vasilev.dd.model.Car;

public class View implements Listener {
    private Controller controller;
    private Pane root;
    private ConcurrentMap<Long, Rectangle> rectangleMap;

    public View(Controller controller, Pane root) {
        this.controller = controller;
        this.root = root;
        rectangleMap = new ConcurrentHashMap<>();
    }

    public void initialize() {this.controller.addListener(this);}

    @Override
    public void showCar(Car car) {
//        Platform.runLater(() -> {
//            rectangle.setTranslateX(car.getPosition().getX());
//            rectangle.setTranslateY(root.getHeight() - car.getPosition().getY());
//        });
    }

    public Rectangle createCarView() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(20);
        Platform.runLater(() -> root.getChildren().add(rectangle));
        return rectangle;
    }
}
