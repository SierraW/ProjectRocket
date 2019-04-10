package rocket.rocketcore.rocketcontrol;

import rocket.environment.Axis;

import java.util.HashMap;
import java.util.Map;

public class RocketControl {
    private float throttle;
    private float powerTurnAngel;
    private boolean detonate;
    
    private HashMap<Integer, Float> powerTurnControlMap;
    private HashMap<Integer, Float> throttleControlMap;
    private Integer explodeTime;


    public RocketControl() {
        throttle = 0;
        powerTurnAngel = 90;
        detonate = false;
        powerTurnControlMap = new HashMap<>();
        throttleControlMap = new HashMap<>();
        explodeTime = Integer.MAX_VALUE;
    }

    public RocketControl(float throttle, float powerTurnAngel) {
        this.throttle = throttle;

        this.powerTurnAngel = powerTurnAngel;
    }

    public float getAngle() {
        return powerTurnAngel;
    }

    public float getThrottle() {
        return throttle;
    }

    public boolean isDetonate() {
        return detonate;
    }

    public void setPowerTurnControlMap(HashMap<Integer, Float> powerTurnControlMap) {
        this.powerTurnControlMap = powerTurnControlMap;
    }

    public void setThrottleControlMap(HashMap<Integer, Float> throttleControlMap) {
        this.throttleControlMap = throttleControlMap;
    }

    public void setExplodeTime(Integer explodeTime) {
        this.explodeTime = explodeTime;
    }

    public void update(int tick) {
        if (powerTurnControlMap.containsKey(tick)) {
            this.powerTurnAngel = powerTurnControlMap.get(tick);
        }
        if (throttleControlMap.containsKey(tick)) {
            this.throttle = throttleControlMap.get(tick);
        }
        if (tick == explodeTime) {
            detonate = true;
        }
    }
}
