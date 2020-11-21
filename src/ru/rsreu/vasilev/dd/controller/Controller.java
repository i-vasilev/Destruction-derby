package ru.rsreu.vasilev.dd.controller;

import javafx.scene.input.KeyCode;
import ru.rsreu.vasilev.dd.model.Direction;
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

    public void addKey(KeyCode keyCode) {
        if(keyCode==KeyCode.RIGHT){
            model.getFirstCar().getDirections().add(Direction.RIGHT);
        }
        if(keyCode==KeyCode.UP){
            model.getFirstCar().getDirections().add(Direction.UP);
        }
        if(keyCode==KeyCode.LEFT){
            model.getFirstCar().getDirections().add(Direction.LEFT);
        }
        if(keyCode==KeyCode.DOWN){
            model.getFirstCar().getDirections().add(Direction.DOWN);
        }
        if(keyCode==KeyCode.D){
            model.getSecondCar().getDirections().add(Direction.RIGHT);
        }
        if(keyCode==KeyCode.W){
            model.getSecondCar().getDirections().add(Direction.UP);
        }
        if(keyCode==KeyCode.A){
            model.getSecondCar().getDirections().add(Direction.LEFT);
        }
        if(keyCode==KeyCode.S){
            model.getSecondCar().getDirections().add(Direction.DOWN);
        }
    }

    public void removeKey(KeyCode keyCode) {
        if(keyCode==KeyCode.RIGHT){
            model.getFirstCar().getDirections().remove(Direction.RIGHT);
        }
        if(keyCode==KeyCode.UP){
            model.getFirstCar().getDirections().remove(Direction.UP);
        }
        if(keyCode==KeyCode.LEFT){
            model.getFirstCar().getDirections().remove(Direction.LEFT);
        }
        if(keyCode==KeyCode.DOWN){
            model.getFirstCar().getDirections().remove(Direction.DOWN);
        }
        if(keyCode==KeyCode.D){
            model.getSecondCar().getDirections().remove(Direction.RIGHT);
        }
        if(keyCode==KeyCode.W){
            model.getSecondCar().getDirections().remove(Direction.UP);
        }
        if(keyCode==KeyCode.A){
            model.getSecondCar().getDirections().remove(Direction.LEFT);
        }
        if(keyCode==KeyCode.S){
            model.getSecondCar().getDirections().remove(Direction.DOWN);
        }
    }
}
