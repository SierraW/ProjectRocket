package rocket.view.prepscene;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import rocket.objcontrol.LiveGameObj;

import java.awt.event.ActionEvent;

public class ControlWriterGridPane {


    private GridPane currentPane;
    TextField txtThrottleStart = new TextField();
    TextField txtThrottleControl = new TextField();

    TextField txtPowerTurnStart = new TextField();
    TextField txtPowerTurnControl = new TextField();

    TextField txtDetonateControl = new TextField();

    ControlWriterGridPane() {
        currentPane = getGridPane();
    }

    private GridPane getGridPane() {
        GridPane gridPane = new GridPane();

        Label lblTitle = new Label("Controller");

        Label lblThrottleControl = new Label("Throttle Control (0 ~ 1)");
        Label lblPowerTurnControl = new Label("Power Turn Control (-5 ~ +5)");
        Label lblDetonateTimer = new Label("Explode at:");

        Label lblFrom = new Label("At(ticks)");
        Label lblControl = new Label("Control");

        Button btnEdit = new Button();
        Button btnThrottle = new Button("Throttle");
        btnThrottle.setId("throttle");
        Button btnPowerTurn = new Button("Power Turn");
        btnPowerTurn.setId("powerTurn");
        Button btnDetonate = new Button("Detonate control");
        btnDetonate.setId("detonate");

        gridPane.add(lblTitle, 0, 0, 1, 1);
        gridPane.add(lblFrom, 1, 0);
        gridPane.add(lblControl, 2, 0);
        gridPane.add(lblThrottleControl, 0, 1);
        gridPane.add(txtThrottleStart, 1,1);
        gridPane.add(txtThrottleControl,2,1);

        gridPane.add(lblPowerTurnControl, 0, 2);
        gridPane.add(txtPowerTurnStart, 1, 2);
        gridPane.add(txtPowerTurnControl, 2,2);

        gridPane.add(lblDetonateTimer, 0, 3);
        gridPane.add(txtDetonateControl, 2, 3);

        GridPane.setHalignment(lblTitle, HPos.CENTER);

        gridPane.setHgap(10);
        gridPane.setVgap(5);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col23 = new ColumnConstraints();
        col1.setMinWidth(10);
        col23.setPercentWidth(10);
        col1.setPercentWidth(15);
        gridPane.getColumnConstraints().addAll(col1, col23, col23);

        return gridPane;
    }


    public GridPane getCurrentPane() {
        return currentPane;
    }
}
