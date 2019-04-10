package rocket.view.gameview;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import rocket.environment.GameTimeline;
import rocket.environment.RocketGame;
import rocket.gameeco.variable.GlobalVariable;
import rocket.gridsystem.PlayerGridLoader;
import rocket.objcontrol.GameObjController;
import rocket.objcontrol.GameObjRegister;
import rocket.gameeco.r1rocket.R1Rocket;
import rocket.rocketcore.Rocket;

public class GameVisualizer extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        String stylesheet = getClass().getResource("main.css").toExternalForm();

        GameController gameController = new GameController();

        Scene scene = gameController.getCurrentScene();

        scene.getStylesheets().add(stylesheet);

        primaryStage.setScene(scene);
        primaryStage.show();

        GameTimeline timeline = new GameTimeline();
        Runnable gameTick = () -> {

        };

        timeline.update(gameTick);
    }


}
