package rocket.rocketcore.rocketcomponent;

import rocket.rocketcore.Disconnectable;
import rocket.rocketcore.RocketPart;
import rocket.rocketcore.rocketfragment.Fuel;

public class Engine extends RocketPart implements Disconnectable {
    private double powerPerTick;

    public Engine(double partWeight, double powerPerTick) {
        super(partWeight);
        this.powerPerTick = powerPerTick;
    }

    public boolean disconnect() {
        if (isConnected()) {
            setConnected(false);
            return true;
        }
        return false;
    }

    public double getPowerPerTick() {
        return powerPerTick;
    }

}
