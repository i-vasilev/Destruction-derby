package ru.rsreu.vasilev.dd.view;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.rsreu.vasilev.dd.controller.Controller;
import ru.rsreu.vasilev.dd.model.Car;
import ru.rsreu.vasilev.dd.model.Model;

public class View implements Listener {
    public static final int WIDTH_WINDOW = 800;
    public static final int HEIGHT_WINDOW = 600;
    public static final int WIDTH_SQUARE = 100;
    public static final int HEIGHT_TOP_PANEL = 100;
    public static final int WIN_MESSAGE_WINDOW_X = 150;
    public static final int WIN_MESSAGE_WINDOW_Y = 200;
    public static final int WIN_MESSAGE_WINDOWS_WIDTH = 500;
    public static final int WIN_MESSAGE_WINDOWS_HEIGHT = 300;

    private final Controller controller;
    private final BorderPane root;

    private final ViewPause pauseMenu;

    public View(Controller controller, BorderPane root) {
        this.controller = controller;
        this.root = root;
        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu gameMenu = new Menu("Game");

        Menu openMenu = new Menu("Open...");
        Menu saveMenu = new Menu("Save...");
        Menu closeMenu = new Menu("Close");
        closeMenu.setOnAction((actionEvent) -> System.exit(0));

        gameMenu.getItems().addAll(openMenu, saveMenu, closeMenu);

        menuBar.getMenus().add(gameMenu);
        root.setTop(menuBar);
        pauseMenu = createPauseMenu();
    }

    @Override
    public void handle(Object object, EventType type) {
        if (type == EventType.INIT) {
            initGame((Model) object);
            root.setOnKeyPressed(a -> controller.addKey(a.getCode()));
            root.setOnKeyReleased(a -> controller.removeKey(a.getCode()));
        }
        if (type == EventType.CREATE_CAR) {
            ((Car) object).setObjectListener(createCarView((Car) object));
        }
        if (type == EventType.WIN) {
            Platform.runLater(() -> {
                final double grayIntensity = 0.8;
                Paint fillRect = new Color(grayIntensity, grayIntensity, grayIntensity, 1);
                Rectangle rectangle = new Rectangle();
                rectangle.setX(WIN_MESSAGE_WINDOW_X);
                rectangle.setY(WIN_MESSAGE_WINDOW_Y);
                rectangle.setWidth(WIN_MESSAGE_WINDOWS_WIDTH);
                rectangle.setHeight(WIN_MESSAGE_WINDOWS_HEIGHT);
                rectangle.setFill(fillRect);
                root.getChildren().add(rectangle);
                final Text text = new Text(290, 345, object.toString() + " is won!");
                text.setFont(new Font(40));
                root.getChildren().add(text);
            });
        }
        if (type == EventType.PAUSE) {
            pauseMenu.showMenu();
        }
    }

    private void initGame(Model model) {
        root.setPrefSize(WIDTH_WINDOW, HEIGHT_WINDOW + HEIGHT_TOP_PANEL);
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                final int y = i;
                final int x = j;
                if (y == model.getFinalPoint().getY() && x == model.getFinalPoint().getX()) {
                    Platform.runLater(() -> {
                        Paint fillSquare = new Color(0, 0, 0, 0.1);
                        final Rectangle finalRectangle = new Rectangle();
                        finalRectangle.setX(x * WIDTH_SQUARE);
                        finalRectangle.setY(y * WIDTH_SQUARE + HEIGHT_TOP_PANEL);
                        finalRectangle.setHeight(WIDTH_SQUARE);
                        finalRectangle.setWidth(WIDTH_SQUARE);
                        finalRectangle.setFill(fillSquare);
                        root.getChildren().add(finalRectangle);
                    });
                }
                if (x != 0 && model.getMap()[y][x - 1] != model.getMap()[y][x]) {
                    Platform.runLater(() -> root.getChildren()
                            .add(new Line(x * WIDTH_SQUARE, y * WIDTH_SQUARE + HEIGHT_TOP_PANEL, x * WIDTH_SQUARE,
                                    (y + 1) * WIDTH_SQUARE + HEIGHT_TOP_PANEL)));
                }

                if (y != 0 && model.getMap()[y - 1][x] != model.getMap()[y][x]) {
                    Platform.runLater(() -> root.getChildren()
                            .add(new Line(x * WIDTH_SQUARE, y * WIDTH_SQUARE + HEIGHT_TOP_PANEL,
                                    (x + 1) * WIDTH_SQUARE, y * WIDTH_SQUARE + HEIGHT_TOP_PANEL)));
                }
            }
        }
        Platform.runLater(() -> root.getChildren()
                .add(new Line(0, HEIGHT_TOP_PANEL, WIDTH_WINDOW, HEIGHT_TOP_PANEL)));
    }

    private ObjectListener createCarView(Car object) {
        return new CarView(root, object);
    }

    private ViewPause createPauseMenu() {
        return new ViewPause(root);
    }
}
