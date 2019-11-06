package rocket.view.prepscene;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import rocket.gameeco.r1rocket.R1Rocket;
import rocket.gameeco.r2rocket.R2Rocket;
import rocket.gameeco.variable.GlobalVariable;
import rocket.rocketcore.Rocket;
import rocket.rocketcore.rocketcontrol.RCSystemIO;
import rocket.rocketcore.rocketcontrol.RocketControl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RocketPrepareScene {

    Label lblLandingInfo = new Label("");
    Label lblRocketInfo = new Label("");
    Label lblRocketFuel = new Label();
    Label lblRocketThrottleControl = new Label();
    Label lblRocketPowerTurnControl = new Label();
    Label lblRocketDetonateControl = new Label();
    Timeline timeline = new Timeline();


    public Scene getScene(AnchorPane backgroundPane, float rocketXAxis, Button btnBack) {

        Circle circle = new Circle(100);
        circle.setStroke(Color.BLACK);
        circle.setFill(GlobalVariable.globalBackgroundColor);

        Rectangle rectRocket = new Rectangle(90, 10, Color.BLACK);

        Rocket rocket = new R1Rocket(rocketXAxis);

        RCSystemIO rcSystemIO = RCSystemIO.getInstance();

        rocket.setRocketControl(rcSystemIO.rcsFileToControl(rcSystemIO.readRCMap(GlobalVariable.currentSelectedRCFileIndex)));
        rocket.setActive(true);

        Circle cirRocket = new Circle(3);

        Circle cirExplodeRing = new Circle(rocket.getExplodeRadius());
        cirExplodeRing.setVisible(false);
        cirExplodeRing.setStroke(Color.RED);
        cirExplodeRing.setFill(Color.TRANSPARENT);

        Text txtRocket = new Text("Rocket");
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(cirRocket,txtRocket);
        txtRocket.setTranslateY(10);


        Rectangle rectangle = new Rectangle(GlobalVariable.screenWidth, 1);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(lblLandingInfo,lblRocketInfo,lblRocketFuel,lblRocketThrottleControl,lblRocketPowerTurnControl,lblRocketDetonateControl);

        backgroundPane.getChildren().addAll(circle, rectangle, rectRocket, stackPane,btnBack, vBox ,cirExplodeRing);

        AnchorPane.setTopAnchor(circle, 10.0);
        AnchorPane.setRightAnchor(circle, 10.0);

        AnchorPane.setTopAnchor(rectRocket, 105.0);
        AnchorPane.setRightAnchor(rectRocket, 65.0);

        AnchorPane.setTopAnchor(rectangle, (double)GlobalVariable.getYPrefix());
        AnchorPane.setLeftAnchor(rectangle, 0.0);

        AnchorPane.setLeftAnchor(btnBack, 10.0);
        AnchorPane.setTopAnchor(btnBack, 10.0);

        AnchorPane.setTopAnchor(vBox,100.0);
        AnchorPane.setTopAnchor(vBox,120.0);

        Scene scene = new Scene(backgroundPane, GlobalVariable.screenWidth, GlobalVariable.screenHeight, GlobalVariable.globalBackgroundColor);

        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblLandingInfo.setText("t+: " + rocket.getTimer());
                lblRocketInfo.setText(rocket.toString());
                lblRocketFuel.setText(String.format("Fuel: %4.2f", rocket.getRocketFuelTank().getContain()));
                lblRocketThrottleControl.setText(String.format("Throttle: %4.2f", rocket.getRocketControl().getThrottle()));
                lblRocketPowerTurnControl.setText(String.format("Power turn angel: %4.2f", rocket.getRocketControl().getAngle()));
                lblRocketDetonateControl.setText(String.format("Explode in: %4d", rocket.getRocketControl().getExplodeTime() - rocket.getTimer().getTickPlus()));
            }
        }));
        timeline.playFrom(Duration.millis(10));

        Runnable runnable = () -> {

            rocket.update();

            stackPane.setTranslateX(rocket.getAxis().getXAxis());
            stackPane.setTranslateY(GlobalVariable.getYPrefix() - rocket.getAxis().getYAxis());

            if (rocket.isExploded()) {
                System.out.println("herh");
                cirExplodeRing.setTranslateX(rocket.getAxis().getXAxis()+17);
                cirExplodeRing.setTranslateY(GlobalVariable.getYPrefix() - rocket.getAxis().getYAxis());
                cirExplodeRing.setVisible(true);
            }

        };

        Runnable r2 = () -> {
            rectRocket.getTransforms().setAll(new Rotate(180-(int)rocket.getAxis().getAngle(), 45, 5));
            if(!rocket.isActive()) {
                service.shutdown();
            }
        };

        Runnable end = () -> {
            service.shutdown();
            btnBack.fire();
        };

        service.scheduleAtFixedRate(runnable,10,10, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(r2,10,200, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(end,60,60, TimeUnit.SECONDS);

        return scene;
    }

    private int count = 0;

    private int counter() {
        return count++;
    }
}
