package rocket.rocketcore.rocketcontrol;

import rocket.gameeco.variable.GlobalVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RCSystemBuilder {
    private ArrayList<ThrottleControl> throttleControl;
    private int currentTCPosition;
    private ArrayList<PowerTurnControl> powerTurnControl;
    private int currentPTCPosition;
    private Integer explodeTimer;

    public RCSystemBuilder() {
        throttleControl = new ArrayList<>();
        powerTurnControl = new ArrayList<>();
        currentPTCPosition = 0;
        currentTCPosition = 0;
    }

    public ArrayList<PowerTurnControl> getPowerTurnControl() {
        return powerTurnControl;
    }

    public ArrayList<ThrottleControl> getThrottleControl() {
        return throttleControl;
    }

    public Integer getExplodeTimer() {
        return explodeTimer;
    }

    public boolean addThrottleControl(Integer tick, Float throttle) {
        if (throttle < 0 || throttle > 1) {
            return false;
        }
        if (checkThrottleControlContain(tick)) {
            return false;
        }
        throttleControl.add(new ThrottleControl(tick, throttle));
        return true;
    }

    public boolean addPowerTurnControl(Integer tick, Float powerTurnAngel) {
        if (powerTurnAngel < -GlobalVariable.MAXPOWERTURNANGLE || powerTurnAngel > GlobalVariable.MAXPOWERTURNANGLE) {
            return false;
        }
        if (checkPowerTurnControlContain(tick)) {
            return false;
        }
        powerTurnControl.add(new PowerTurnControl(tick, powerTurnAngel));
        return true;
    }

    public boolean addDetonateControl(Integer tick) {
        if (tick < 0) {
            return false;
        }
        explodeTimer = tick;
        return true;
    }

    public void removeThrottleControl() {
        throttleControl.remove(currentTCPosition);
    }

    public void removePowerTurnControl() {
        powerTurnControl.remove(currentPTCPosition);
    }

    public ThrottleControl getCurrentThrottleControl() {
        if (throttleControl.size() == 0) {
            return new ThrottleControl(0, 0f);
        }
        if (currentTCPosition >= throttleControl.size()) {
            currentTCPosition = throttleControl.size() - 1;
        }
        return throttleControl.get(currentTCPosition);
    }

    public PowerTurnControl getCurrentPowerTurnControl() {
        if (powerTurnControl.size() == 0) {
            return new PowerTurnControl(0, 0f);
        }
        if (currentPTCPosition >= powerTurnControl.size()) {
            currentPTCPosition = powerTurnControl.size() - 1;
        }
        return powerTurnControl.get(currentPTCPosition);
    }

    public boolean movePointerToLeft(boolean isThrottle) {
        if (isThrottle) {
            if (currentTCPosition == 0 || throttleControl.size() == 0) {
                return false;
            } else {
                currentTCPosition--;
                return true;
            }
        }
        if (currentPTCPosition == 0 || powerTurnControl.size() == 0) {
            return false;
        } else {
            currentPTCPosition--;
            return true;
        }
    }

    public boolean movePointerToRight(boolean isThrottle) {
        if (isThrottle) {
            if (throttleControl.size() == 0) {
                return false;
            }
            if (currentTCPosition == throttleControl.size() - 1) {
                return false;
            } else {
                currentTCPosition++;
                return true;
            }
        }
        if (powerTurnControl.size() == 0) {
            return false;
        }
        if (currentPTCPosition == powerTurnControl.size() - 1) {
            return false;
        } else {
            currentPTCPosition++;
            return true;
        }
    }

    public boolean setPointer(Integer position,boolean isThrottle) {
        if (isThrottle) {
            if (throttleControl.size() <= position) {
                return false;
            }
            currentTCPosition = position;
            return true;
        }
        if (powerTurnControl.size() <= position) {
            return false;
        }
        currentPTCPosition = position;
        return true;
    }

    public HashMap<Integer, Float> toTCMap() {
        HashMap<Integer, Float> hashMap = new HashMap<>();
        if (throttleControl.size() == 0) {
            return hashMap;
        }
        for (ThrottleControl tc : throttleControl) {
            hashMap.put(tc.getStartTick(), tc.getControl());
        }
        return hashMap;
    }

    public HashMap<Integer, Float> toPTCMap() {
        HashMap<Integer, Float> hashMap = new HashMap<>();
        if (powerTurnControl.size() == 0) {
            return hashMap;
        }
        for (PowerTurnControl ptc : powerTurnControl) {
            hashMap.put(ptc.getStartTick(), ptc.getControl());
        }
        return hashMap;
    }

    public void readFromFile(RCSFile rcsFile) {
        Integer tick;
        Float control;
        throttleControl = new ArrayList<>();
        powerTurnControl = new ArrayList<>();
        currentPTCPosition = 0;
        currentTCPosition = 0;
        for (Integer key : rcsFile.getThrottleMap().keySet()) {
            addThrottleControl(key, rcsFile.getThrottleMap().get(key));
        }
        for (Integer key : rcsFile.getPowerTurnMap().keySet()) {
            addPowerTurnControl(key, rcsFile.getPowerTurnMap().get(key));
        }
        explodeTimer = rcsFile.getExplodeTime();
    }

    public boolean searchByTick(Integer tick) {
        boolean condition = false;
        if (throttleControl.size() > 0) {
            for (int i = 0; i < throttleControl.size(); i++) {
                if (throttleControl.get(i).getStartTick().equals(tick)) {
                    currentTCPosition = i;
                }
            }
            condition = true;
        }
        if (powerTurnControl.size() > 0) {
            for (int i = 0; i < powerTurnControl.size(); i++) {
                if (powerTurnControl.get(i).getStartTick().equals(tick)) {
                    currentPTCPosition = i;
                }
            }
            condition = true;
        }
        return condition;
    }

    private boolean checkThrottleControlContain(Integer tick) {
        for (ThrottleControl tcs : throttleControl) {
            if (tcs.getStartTick().equals(tick)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPowerTurnControlContain(Integer tick) {
        for (PowerTurnControl rtcs : powerTurnControl) {
            if (rtcs.getStartTick().equals(tick)) {
                return true;
            }
        }
        return false;
    }

    private ThrottleControl getTCByKey(Integer key) throws IllegalArgumentException {
        for (ThrottleControl tc : throttleControl) {
            if (tc.getStartTick().equals(key)) {
                return tc;
            }
        }
        return new ThrottleControl(0, 0f);
    }

    private PowerTurnControl getPTCByKey(Integer key) throws IllegalArgumentException {
        for (PowerTurnControl ptc : powerTurnControl) {
            if (ptc.getStartTick().equals(key)) {
                return ptc;
            }
        }
        return new PowerTurnControl(0, 0f);
    }
}
