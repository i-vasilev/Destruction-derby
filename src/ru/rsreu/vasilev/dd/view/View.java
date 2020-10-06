package ru.rsreu.vasilev.dd.view;

import ru.rsreu.vasilev.dd.controller.Controller;

public class View implements Listener {
    private Controller controller;

    public View(Controller controller) {this.controller = controller;}

    public void initialize() {this.controller.addListener(this);}
}
