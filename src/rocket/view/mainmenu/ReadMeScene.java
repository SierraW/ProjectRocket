package rocket.view.mainmenu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import rocket.gameeco.variable.GlobalVariable;

public class ReadMeScene {
    Label lblInstructions = new Label("Control won't change until your next control point.\n\nGame time is tick: 0.01sec/tick\n\nMake gird system: remember [rocket launch pad] is where your rocket takeoff.\nControl writer: always save before load the control file to Rocket.\n\nTry to load test-file to the rocket!");
    Button btnBack;

    public ReadMeScene(Button btnBack) {
        this.btnBack = btnBack;
    }

    public Scene getScene() {
        AnchorPane anchorPane = new AnchorPane();

        anchorPane.getChildren().addAll(lblInstructions, btnBack);
        AnchorPane.setTopAnchor(btnBack, 5.0);
        AnchorPane.setLeftAnchor(btnBack,5.0);

        AnchorPane.setTopAnchor(lblInstructions, 50.0);

        return new Scene(anchorPane, 500,200);
    }
}
