package rocket.view.gameview;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import rocket.environment.GameTimeline;
import rocket.objcontrol.GameObjController;
import rocket.objcontrol.GameObjRegister;
import rocket.r1rocket.R1Rocket;
import rocket.rocketcore.Rocket;

public class GameVisualizer extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameStage game = new GameStage();

        GameObjController tick = new GameObjController();
        Rocket myRocket = new R1Rocket();
        GameObjRegister.signObj(myRocket);

        Circle circle = new Circle(3);
        Label label = new Label("c1",circle);
        Group group = new Group();
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);
        gc.beginPath();
        gc.moveTo(0,0);
        gc.moveTo(100,100);
        gc.closePath();

        group.getChildren().addAll(circle,canvas);

        Scene scene = new Scene(label,1800,1010,Color.LIGHTCORAL);

        primaryStage.setScene(scene);
        primaryStage.show();

        GameTimeline timeline = new GameTimeline();
        Runnable gameTick = () -> {
            tick.run();
            circle.setTranslateY(400-myRocket.getAxis().getYAxis()*2);
            circle.setTranslateX(myRocket.getAxis().getXAxis());
            //gc.lineTo(myRocket.getTimer().getTickPlus()*0.1, -myRocket.getAxis().getYAxis());
        };

        timeline.update(gameTick);
    }
}
