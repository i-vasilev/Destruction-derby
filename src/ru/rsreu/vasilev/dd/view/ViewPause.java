package ru.rsreu.vasilev.dd.view;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ViewPause extends Rectangle {
    private final Pane root;
    private final Text txt;

    public ViewPause(Pane root) {
        this.root = root;
        txt = new Text("Пауза");

    }

    public void showMenu() {
        if (root.getChildren().contains(this)) {
            Platform.runLater(() -> root.getChildren().remove(this));
            Platform.runLater(() -> root.getChildren().remove(txt));
        } else {
            Platform.runLater(() -> root.getChildren().add(this));
            Platform.runLater(() -> root.getChildren().add(txt));
        }
    }
}
