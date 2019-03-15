package rocket.objcontrol;

import java.util.ArrayList;
import java.util.HashMap;

public class GameObjRegister {
    private static int indexFactory = 0;
    private static HashMap<Integer, GameObj> map = new HashMap<>();

    public static int register() {
        return indexFactory++;
    }

    public static boolean signObj(GameObj gameObj) {
        if (gameObj == null) {
            System.out.println("GOR: null pointer.");
            return false;
        }
        if (map.containsValue(gameObj)) {
            System.out.println("GOR: existing object.");
            return false;
        }
        map.put(gameObj.getIdentifier(), gameObj);
        return true;
    }

    public static ArrayList<GameObj> getGameObjects() {
        return new ArrayList<>(map.values());
    }
}
