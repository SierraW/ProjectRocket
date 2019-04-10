package rocket.objcontrol;

import java.util.ArrayList;
import java.util.HashMap;

public class GameObjRegister {
    private static int indexFactory = 0;
    private static HashMap<Integer, RocketObj> map = new HashMap<>();

    public static int register() {
        return indexFactory++;
    }

    public static boolean signObj(RocketObj rocketObj) {
        if (rocketObj == null) {
            System.out.println("GOR: null pointer.");
            return false;
        }
        if (map.containsValue(rocketObj)) {
            System.out.println("GOR: existing object.");
            return false;
        }
        map.put(rocketObj.getIdentifier(), rocketObj);
        return true;
    }

    public static ArrayList<RocketObj> getGameObjects() {
        return new ArrayList<>(map.values());
    }
}
