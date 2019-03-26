package rocket.environment;

import rocket.objcontrol.GameObj;
import rocket.objcontrol.LiveGameObj;

import java.util.ArrayList;

public class SceneController implements LiveGameObj {
    private ArrayList<GameObj> gameObjs;

    @Override
    public void update() {

    }

    public void addGameObjs(ArrayList<GameObj> gameObjs) {
        this.gameObjs = gameObjs;
    }

    public ArrayList<GameObj> getGameObjs() {
        return gameObjs;
    }
}
