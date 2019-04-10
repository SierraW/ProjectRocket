package rocket.view.mainmenu;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import rocket.gameeco.variable.GlobalVariable;
import rocket.view.gameview.GameController;
import rocket.view.gameview.SceneController;

public class MainMenuSceneController extends SceneController{

    public MainMenuSceneController(GameController gameController) {
        super(gameController);
    }

    public Scene getScene() {
        Label label = new Label(GlobalVariable.GAMETITLE);
        label.setId("maintitle");//todo style
        Button btnStart = new Button("Start");
        Button btnStop = new Button("Stop");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, btnStart, btnStop);

        btnStart.setOnAction(super.getParentController());
        btnStop.setOnAction(event -> System.exit(0));

        return new Scene(vBox, GlobalVariable.screenWidth, GlobalVariable.screenHeight);
    }
}
