package ru.rsreu.vasilev.dd.view;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.rsreu.vasilev.dd.model.Car;
import ru.rsreu.vasilev.dd.model.Model;

import java.util.Random;

public class CarView extends Polygon implements ObjectListener {
    public static final double CAR_WIDTH = 10;
    public static final double CAR_HEIGHT = 20;
    private final Pane root;
    private final Text carName;
    private final Text score;

    public CarView(Pane root, Car object) {
        this.root = root;
        getPoints().addAll(0., 0.,
                CAR_WIDTH, 0.,
                CAR_WIDTH, CAR_HEIGHT,
                0., CAR_HEIGHT);
        final Random random = new Random();
        Paint color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1);
        Font font = new Font(20.0);
        setFill(color);
        score = new Text();
        score.setX((object.getPosition().getX() - Model.START_POSITION_X) * 10);
        score.setY(25);
        score.setFill(color);
        score.setFont(font);
        carName = new Text();
        carName.setX((object.getPosition().getX() - Model.START_POSITION_X) * 10);
        carName.setY(75);
        carName.setFill(color);
        carName.setFont(font);
        Platform.runLater(() -> root.getChildren().add(this));
        Platform.runLater(() -> root.getChildren().add(score));
        Platform.runLater(() -> root.getChildren().add(carName));
    }

    @Override
    public void handle(Object object, ObjectEventType type) {
        if (object instanceof Car) {
            Car car = (Car) object;
            carName.setText(car.toString());
            score.setText(String.valueOf(car.getLives()));
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
