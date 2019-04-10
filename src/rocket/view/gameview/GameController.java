package rocket.view.gameview;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import rocket.view.mainmenu.MainMenuSceneController;

public class GameController implements EventHandler<ActionEvent> {
    public enum GameStatus {
        MENU, STAGES, SELECT, TRAINER, MAIN, END;
    }

    private GameStatus gameStatus;

    private SceneController menuScene;
    private SceneController stageSelect;
    private SceneController rocketSelect;
    private SceneController rocketTrainer;
    private SceneController gameScene;
    private SceneController scoreBoard;

    private Scene currentScene;

    public GameController() {
        gameStatus = GameStatus.MENU;
        menuScene = new MainMenuSceneController(this);
        currentScene = menuScene.getScene();
    }

    public void update() {
        switch (gameStatus) {
            case STAGES:
                currentScene = stageSelect.getScene();
                break;
            case SELECT:
                currentScene = rocketSelect.getScene();
                break;
            case TRAINER:
                currentScene = rocketTrainer.getScene();
                break;
            case MAIN:
                currentScene = gameScene.getScene();
                break;
            case END:
                currentScene = scoreBoard.getScene();
                break;
            case MENU:
            default:
                currentScene = menuScene.getScene();
                break;
        }
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        update();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof MainMenuSceneController) {
            setGameStatus(GameStatus.SELECT);
        }
    }
}
