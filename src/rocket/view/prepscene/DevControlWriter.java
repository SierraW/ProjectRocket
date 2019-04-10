package rocket.view.prepscene;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rocket.environment.RocketGame;
import rocket.gameeco.variable.GlobalVariable;
import rocket.gridsystem.PlayerGridLoader;
import rocket.view.gameview.StructureView;

public class DevControlWriter extends Application {

    AnchorPane anchorPane = new AnchorPane();
    Scene scene = new Scene(anchorPane, GlobalVariable.screenWidth, GlobalVariable.screenHeight);
    ControlWriterGridPane cwg = new ControlWriterGridPane();
    GridPane gridPane = cwg.getCurrentPane();

    @Override
    public void start(Stage primaryStage) throws Exception {

        String stylesheet = getClass().getResource("../gameview/main.css").toExternalForm();

        Group group = new Group();

        RocketGame rg= new RocketGame(GlobalVariable.screenWidth);

        StructureView structureView = new StructureView();

        PlayerGridLoader playerGridLoader = new PlayerGridLoader();

        try {
            playerGridLoader.load(GlobalVariable.getUserFile(), rg.getPlayerGrid());
            playerGridLoader.load(GlobalVariable.getUserFile(), rg.getEnemyGrid());
        } catch (Exception e) {
            System.out.println("User file not found.");
        }

        group.getChildren().addAll(structureView.getGrids(rg));

        anchorPane.getChildren().addAll(group, gridPane);


        scene.getStylesheets().addAll(stylesheet);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void refresh() {
        gridPane = cwg.getCurrentPane();
        anchorPane.getChildren().set(1, gridPane);
    }

}
