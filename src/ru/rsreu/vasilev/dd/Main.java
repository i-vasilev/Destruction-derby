package ru.rsreu.vasilev.dd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.rsreu.vasilev.dd.controller.Controller;
import ru.rsreu.vasilev.dd.model.Model;
import ru.rsreu.vasilev.dd.view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DD");
        Pane root = new Pane();
        root.setPrefSize(400, 600);
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
        Model model = new Model();
        root.requestFocus();
        Controller controller = new Controller(model);
        View view = new View(controller, root);
        view.initialize();
        model.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
