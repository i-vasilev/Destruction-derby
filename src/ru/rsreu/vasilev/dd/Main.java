package ru.rsreu.vasilev.dd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.rsreu.vasilev.dd.controller.Controller;
import ru.rsreu.vasilev.dd.model.Model;
import ru.rsreu.vasilev.dd.view.View;

public class Main extends Application {

    private final int widthWindow = 400;

    private final int heightWindow = 600;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DD");
        Pane root = new Pane();
        root.setPrefSize(widthWindow, heightWindow);
        primaryStage.setScene(new Scene(root, widthWindow, heightWindow));
        primaryStage.show();
        Model model =
                new Model();//В машине происходит событие, которое обрабатывается в view - создание машины
        model.initialize();
        root.requestFocus();
        Controller controller = new Controller(model);
        View view = new View(controller, root);
        root.setOnKeyPressed(a -> controller.addKey(a.getCode()));
        root.setOnKeyReleased(a -> controller.removeKey(a.getCode()));
        view.initialize();
        model.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
