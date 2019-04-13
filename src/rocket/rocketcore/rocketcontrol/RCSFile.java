package rocket.rocketcore.rocketcontrol;

import java.io.Serializable;
import java.util.HashMap;

public class RCSFile implements Serializable {
    private HashMap<Integer, Float> powerTurnMap;
    private HashMap<Integer, Float> throttleMap;
    private Integer explodeTime;

    public RCSFile(HashMap<Integer, Float> powerTurnMap, HashMap<Integer, Float> throttleMap, Integer time) {
        this.powerTurnMap = powerTurnMap;
        this.throttleMap = throttleMap;
        this.explodeTime = time;
    }

    public HashMap<Integer, Float> getPowerTurnMap() {
        return powerTurnMap;
    }

    public HashMap<Integer, Float> getThrottleMap() {
        return throttleMap;
    }

    public Integer getExplodeTime() {
        return explodeTime;
    }
}
