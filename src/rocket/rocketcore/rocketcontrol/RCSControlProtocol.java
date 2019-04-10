package rocket.rocketcore.rocketcontrol;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class RCSControlProtocol {
    private Integer startTick;
    private Float control;

    RCSControlProtocol(HashMap<Integer, Float> map, Integer startTick, Float control) throws IllegalArgumentException {
        if (!setStartTick(map, startTick)) {
            throw new IllegalArgumentException();
        }
        setControl(control);
    }

    public boolean setStartTick(HashMap<Integer, Float> map, Integer startTick) {
        if (map.containsKey(startTick)) {
            return false;
        }
        this.startTick = startTick;
        return true;
    }

    public abstract void setControl(Float control);

    public Integer getStartTick() {
        return startTick;
    }

    public Float getControl() {
        return control;
    }

    public HashMap<Integer, Float> toMapElement() {
        HashMap<Integer, Float> hashMap = new HashMap<>(startTick, control);
        return hashMap;
    }
}
