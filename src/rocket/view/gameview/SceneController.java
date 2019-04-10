package rocket.view.gameview;

import javafx.scene.Scene;

public abstract class SceneController {
    private GameController parentController;

    public SceneController(GameController parentController) {
        this.parentController = parentController;
    }

    public abstract Scene getScene();

    public GameController getParentController() {
        return parentController;
    }
}
