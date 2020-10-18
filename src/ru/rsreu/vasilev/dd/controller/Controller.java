package ru.rsreu.vasilev.dd.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import ru.rsreu.vasilev.dd.model.Model;
import ru.rsreu.vasilev.dd.view.Listener;

public class Controller implements EventHandler<KeyEvent> {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void addListener(Listener listener) {
        model.addListener(listener);
    }

    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCode().getChar());
        model.movePlayer(event.getCode().getChar());
    }
}
