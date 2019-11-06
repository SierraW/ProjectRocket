package rocket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import rocket.view.mainmenu.MainMenuScene;
import rocket.view.mainmenu.ReadMeScene;
import rocket.view.prepscene.ControlWriterScene;
import rocket.view.prepscene.GameBackgroundMapPane;
import rocket.view.prepscene.RocketPrepareScene;
import rocket.view.setupscene.MakeGridScene;

public class MainGame extends Application {

    //scene main menu
    Button btnMakeGrid = new Button("Make Grid!");
    Button btnControlWriter = new Button("Control writer");
    Button btnReadMe = new Button("Read Me First!");

    //scene make grid
    Button btnMGBack = new Button("Back");


    //scene control writer
    Button btnCWBack = new Button("Back");
    Scene sceneCW;
    String stylesheet = getClass().getResource("view/gameview/main.css").toExternalForm();
    GameBackgroundMapPane gameBackgroundMapPane = GameBackgroundMapPane.getInstance();
    ControlWriterScene cwg = new ControlWriterScene(btnCWBack);
    RocketPrepareScene rps = new RocketPrepareScene();
    Button btnSimulate = new Button("GO TEST!");
    Button btnBack = new Button("Back");

    //scene read me
    Button btnRMBack = new Button("Back");

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Control writer
        setupControlWriter();
        btnSimulate.setOnAction(event -> {
            primaryStage.setScene(startRocketSim());
        });
        btnCWBack.setOnAction(event -> primaryStage.setScene(getMainMenu()));

        //Rocket Sim
        btnBack.setOnAction(event -> primaryStage.setScene(sceneCW));

        //main menu
        btnMakeGrid.setOnAction(event -> primaryStage.setScene(getMakeGrid()));
        btnControlWriter.setOnAction(event -> primaryStage.setScene(sceneCW));

        //scene make grid
        btnMGBack.setOnAction(event -> primaryStage.setScene(getMainMenu()));

        //read me scene
        btnRMBack.setOnAction(event -> primaryStage.setScene(getMainMenu()));
        btnReadMe.setOnAction(event -> primaryStage.setScene(getReadMe()));


        primaryStage.setScene(getMainMenu());
        primaryStage.show();

    }

    public Scene startRocketSim() {
        rps = new RocketPrepareScene();
        Scene scene = rps.getScene(gameBackgroundMapPane.getAnchorPane(), gameBackgroundMapPane.getRocketLauncherXAxis(), btnBack);
        scene.getStylesheets().addAll(stylesheet);
        return scene;
    }

    public void setupControlWriter() {
        sceneCW = cwg.getScene(gameBackgroundMapPane.getAnchorPane(), btnSimulate);
        sceneCW.getStylesheets().addAll(stylesheet);
    }


    public Scene getMainMenu() {
        MainMenuScene mms = new MainMenuScene(btnMakeGrid, btnControlWriter, btnReadMe);
        Scene sceneMM = mms.getScene();
        return sceneMM;
    }

    public Scene getMakeGrid() {
        MakeGridScene makeGridScene = new MakeGridScene(btnMGBack);
        GameBackgroundMapPane gmp = GameBackgroundMapPane.getInstance();
        try {
            Scene scene = makeGridScene.getScene(gmp.getAnchorPane());
            return scene;
        } catch (Exception e) {
            System.out.println("MainGame: No such file");
        }
        return null;
    }

    public Scene getReadMe() {
        ReadMeScene readMeScene = new ReadMeScene(btnRMBack);
        return readMeScene.getScene();
    }
}
