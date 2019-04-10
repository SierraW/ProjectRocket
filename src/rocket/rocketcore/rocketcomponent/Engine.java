package rocket.rocketcore.rocketcomponent;

import rocket.environment.GlobalTickTime;
import rocket.rocketcore.Controllable;
import rocket.rocketcore.RocketPart;

public class Engine extends RocketPart implements Controllable {
    private float powerPerTick;
    private float turnDomain;

    public Engine(float partWeight, float powerPerSec) {
        super(partWeight);
        this.powerPerTick = powerPerSec / GlobalTickTime.getTickPerSecond();
    }

    @Override
    public void disconnect() {
        setConnected(false);
    }

    public float getPowerPerTick() {
        return powerPerTick;
    }

}
