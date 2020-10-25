package ru.rsreu.vasilev.dd.controller;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.model.Model;
import ru.rsreu.vasilev.dd.view.Listener;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void addKey(KeyCode keyCode) {
        model.addKey(keyCode);
    }

    public void removeKey(KeyCode keyCode) {
        model.removeKey(keyCode);
    }
}
