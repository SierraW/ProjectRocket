package rocket.view.prepscene;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import rocket.environment.Axis;
import rocket.environment.GameTimeline;
import rocket.environment.Timer;
import rocket.gameeco.r1rocket.R1Rocket;
import rocket.gameeco.variable.GlobalVariable;
import rocket.rocketcore.Rocket;
import rocket.rocketcore.rocketcontrol.RocketControl;

public class DevRocketPrepare extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane = new AnchorPane();

        Circle circle = new Circle(100);
        circle.setStroke(Color.BLACK);
        circle.setFill(GlobalVariable.globalBackgroundColor);

        Label lblTime = new Label();

        Rectangle rectRocket = new Rectangle(90, 10, Color.BLACK);

        Rocket rocket = new R1Rocket(GlobalVariable.GAMEMAP_LEFTPADDING);
        rocket.setRocketControl(new RocketControl(1, 65));
        rocket.setActive(true);

        Circle cirRocket = new Circle(3);
        Text txtRocket = new Text("r1rocket");
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(cirRocket,txtRocket);
        txtRocket.setTranslateY(10);


        Rectangle rectangle = new Rectangle(GlobalVariable.screenWidth, 1);

        pane.getChildren().addAll(circle, rectangle, rectRocket, stackPane,lblTime);

        AnchorPane.setTopAnchor(circle, 10.0);
        AnchorPane.setRightAnchor(circle, 10.0);

        AnchorPane.setTopAnchor(rectRocket, 105.0);
        AnchorPane.setRightAnchor(rectRocket, 65.0);

        AnchorPane.setTopAnchor(rectangle, (double)GlobalVariable.getYPrefix());
        AnchorPane.setLeftAnchor(rectangle, 0.0);

        AnchorPane.setLeftAnchor(lblTime, 10.0);
        AnchorPane.setTopAnchor(lblTime, 10.0);

        Scene scene = new Scene(pane, GlobalVariable.screenWidth, GlobalVariable.screenHeight, GlobalVariable.globalBackgroundColor);

        GameTimeline timeline = new GameTimeline();
        Timer timer = new Timer();
        Runnable runnable = () -> {
            timer.tick();
            rocket.update();
            stackPane.setTranslateX(rocket.getAxis().getXAxis());
            stackPane.setTranslateY(GlobalVariable.getYPrefix() - rocket.getAxis().getYAxis());
            rectRocket.getTransforms().setAll(new Rotate(180-(int)rocket.getAxis().getAngle(), 45, 5));
        };
        timeline.update(runnable);

        primaryStage.setScene(scene);
        primaryStage.show();


    }


}
