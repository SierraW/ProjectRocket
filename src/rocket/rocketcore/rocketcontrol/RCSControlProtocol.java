package rocket.rocketcore.rocketcontrol;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class RCSControlProtocol {
    protected Integer startTick;
    protected Float control;

    RCSControlProtocol(Integer startTick, Float control) throws IllegalArgumentException {
        setStartTick(startTick);
        setControl(control);
    }

    public void setStartTick( Integer startTick) {
        this.startTick = startTick;
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
