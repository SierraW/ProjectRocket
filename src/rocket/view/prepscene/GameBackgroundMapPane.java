package rocket.view.prepscene;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import rocket.environment.RocketGame;
import rocket.gameeco.variable.GlobalVariable;
import rocket.gridsystem.PlayerGridLoader;
import rocket.view.gameview.StructureView;

public class GameBackgroundMapPane {
    private static GameBackgroundMapPane gameBackgroundMapPane = null;
    private RocketGame rg;

    private GameBackgroundMapPane() {
        rg= new RocketGame(GlobalVariable.screenWidth);
    }

    public static GameBackgroundMapPane getInstance() {
        if (gameBackgroundMapPane == null) {
            gameBackgroundMapPane = new GameBackgroundMapPane();
            return gameBackgroundMapPane;
        } else {
            return gameBackgroundMapPane;
        }
    }

    public AnchorPane getAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();


        StructureView structureView = new StructureView();

        PlayerGridLoader playerGridLoader = new PlayerGridLoader();

        try {
            playerGridLoader.load(GlobalVariable.getUserFile(), rg.getPlayerGrid());
            playerGridLoader.load(GlobalVariable.getUserFile(), rg.getEnemyGrid());
        } catch (Exception e) {
            System.out.println("User file not found.");
        }

        anchorPane.getChildren().setAll(structureView.getGrids(rg));
        return anchorPane;
    }

    public float getRocketLauncherXAxis() {
        return rg.getPlayerGrid().getRocketLaunchPadXAxis();
    }
}
