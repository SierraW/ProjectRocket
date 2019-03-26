package rocket.rocketcore.rocketcomponent;

import rocket.environment.GlobalTickTime;
import rocket.rocketcore.Controllable;
import rocket.rocketcore.RocketPart;

public class Engine extends RocketPart implements Controllable {
    private double powerPerTick;
    private double turnDomain;

    public Engine(double partWeight, double powerPerSec) {
        super(partWeight);
        this.powerPerTick = powerPerSec / GlobalTickTime.getTickPerSecond();
    }

    @Override
    public void disconnect() {
        setConnected(false);
    }

    public double getPowerPerTick() {
        return powerPerTick;
    }

}
