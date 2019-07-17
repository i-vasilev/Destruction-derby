package ru.rsreu.vasilev.dd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.rsreu.vasilev.dd.controller.Controller;
import ru.rsreu.vasilev.dd.model.Model;
import ru.rsreu.vasilev.dd.view.View;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private final int widthWindow = 400;

    private final int heightWindow = 600;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("DD");
        Pane root = new Pane();
        root.getStylesheets().add("file://./shapes/mainScene.css");
        root.setPrefSize(widthWindow, heightWindow);
        primaryStage.setScene(new Scene(root, widthWindow, heightWindow));
        primaryStage.show();
        Model model = new Model();
        root.requestFocus();
        Controller controller = new Controller(model);
        View view = new View(controller, root);
        controller.setListener(view);
        model.initialize();
        model.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
