package ru.rsreu.vasilev.dd.controller;

import ru.rsreu.vasilev.dd.model.Model;
import ru.rsreu.vasilev.dd.view.Listener;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void setListener(Listener listener) {
        model.setGameListener(listener);
    }
}
