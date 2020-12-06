package ru.rsreu.vasilev.dd;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.rsreu.vasilev.dd.controller.Controller;
import ru.rsreu.vasilev.dd.model.Model;
import ru.rsreu.vasilev.dd.view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DD");
        BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root, View.WIDTH_WINDOW, View.HEIGHT_WINDOW + View.HEIGHT_TOP_PANEL));
        primaryStage.show();
        try {
            Model model = new Model();
            root.requestFocus();
            Controller controller = new Controller(model);
            View view = new View(controller, root);
            controller.setListener(view);
            model.initialize();
            model.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
