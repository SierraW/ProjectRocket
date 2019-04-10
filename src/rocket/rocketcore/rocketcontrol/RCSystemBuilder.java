package rocket.rocketcore.rocketcontrol;

import rocket.gameeco.variable.GlobalVariable;

import java.util.HashMap;
import java.util.Map;

public class RCSystemBuilder {
    private HashMap<Integer, Float> throttleControl;
    private int currentTCPosition;
    private HashMap<Integer, Float> powerTurnControl;
    private int currentPTCPosition;
    private Integer explodeTimer;

    public RCSystemBuilder() {
        throttleControl = new HashMap<>();
        powerTurnControl = new HashMap<>();
        currentPTCPosition = 0;
        currentTCPosition = 0;
    }

    public Map<Integer, Float> getPowerTurnControl() {
        return powerTurnControl;
    }

    public Map<Integer, Float> getThrottleControl() {
        return throttleControl;
    }



    public boolean addThrottleControl(Integer tick, Float throttle) {
        if (throttle <= 0 || throttle > 1) {
            return false;
        }
        return addControl(throttleControl, tick, throttle);
    }

    public boolean addPowerTurnControl(Integer tick, Float powerTurnAngel) {
        if (powerTurnAngel < -GlobalVariable.MAXPOWERTURNANGLE || powerTurnAngel > GlobalVariable.MAXPOWERTURNANGLE) {
            return false;
        }
        return addControl(powerTurnControl, tick, powerTurnAngel);
    }

    public boolean setThrottleControl(Integer tick, Float throttle) {
        return setControl(throttleControl, tick, throttle);
    }

    public boolean setPowerTurnControl(Integer tick, Float powerTurnAngel) {
        return setControl(powerTurnControl, tick, powerTurnAngel);
    }

    public boolean removeThrottleControl(Integer tick) {
        return removeControl(throttleControl, tick);
    }

    public boolean removePowerTurnControl(Integer tick) {
        return removeControl(powerTurnControl, tick);
    }

    private boolean addControl(HashMap<Integer, Float> map, Integer tick, Float control) {
        if (!map.containsKey(tick)) {
            return false;
        }
        map.put(tick, control);
        return true;
    }

    private boolean setControl(HashMap<Integer, Float> map, Integer tick, Float control) {
        if (!map.containsKey(tick)) {
            return false;
        }

        if (map.remove(tick, control)) {
            return addControl(map, tick, control);
        }
        return false;
    }

    private boolean removeControl(HashMap<Integer, Float> map, Integer tick) {
        if (!map.containsKey(tick)) {
            return false;
        }
        map.remove(tick);
        return true;
    }
}
