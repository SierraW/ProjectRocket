package rocket.view.gameview;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import rocket.objcontrol.GameObj;
import javafx.scene.control.Label;
import rocket.rocketcore.Rocket;
import sun.security.provider.SHA;

import java.util.ArrayList;

public class GameStage {
    private int xPrefix = 10;
    private int yPrefix = 1000;
    Shape getGameObj(Rocket rocket) {
        Circle circle = new Circle(1, Color.BLACK);
        Label label = new Label(rocket.getName());

        circle.setLayoutX(xPrefix + rocket.getAxis().getXAxis());
        circle.setLayoutY(yPrefix - rocket.getAxis().getYAxis());

        return circle;
    }

    Group getGroup(Shape shapes) {
        Group group = new Group();
        group.getChildren().add(shapes);
        return group;
    }

    Scene getScene(Group group){
        return new Scene(group,2800,1010);
    }

    Scene debugGetScene(Rocket rocket){
        return getScene(getGroup(getGameObj(rocket)));
    }
}
