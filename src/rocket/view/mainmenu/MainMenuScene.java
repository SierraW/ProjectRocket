package rocket.view.mainmenu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import rocket.gameeco.variable.GlobalVariable;

public class MainMenuScene {
    Label lblGameName;
    Button btnMakeGrid;
    Button btnControlWriter;
    Button btnExit;
    Button btnReadMe;

    public MainMenuScene(Button btnMakeGrid, Button btnControlWriter, Button btnReadMe) {
        this.btnControlWriter = btnControlWriter;
        this.btnMakeGrid = btnMakeGrid;
        this.btnReadMe = btnReadMe;
        btnExit = new Button("Exit");
        lblGameName = new Label("Rocket!");
    }

    public Scene getScene() {
        btnExit.setOnAction(event -> System.exit(0));
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(lblGameName, btnControlWriter, btnMakeGrid, btnReadMe ,btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        borderPane.setCenter(vBox);
        borderPane.setPrefSize(200,200);


        return new Scene(borderPane);
    }



}
