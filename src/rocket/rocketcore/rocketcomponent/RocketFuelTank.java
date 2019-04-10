package rocket.rocketcore.rocketcomponent;

import rocket.rocketcore.Controllable;
import rocket.rocketcore.HoldingParts;
import rocket.rocketcore.RocketPart;
import rocket.rocketcore.rocketfragment.Fuel;

public class RocketFuelTank extends RocketPart implements Controllable, HoldingParts {
    private Fuel fuel;
    private float contain;

    public RocketFuelTank(float fuelTankWeight, float contain, Fuel fuel) {
        super(fuelTankWeight);
        this.fuel = fuel;
        this.contain = contain;
    }

    @Override
    public void disconnect() {
        setConnected(false);
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void burnFuel(float amount) throws IllegalArgumentException{
        if (amount > contain) {
            throw new IllegalArgumentException("RFT: amount too big");
        }
        contain -= amount;
    }

    public float getContain() {
        return contain;
    }

    public void setContain(float contain) {
        this.contain = contain;
    }

    @Override
    public float calWeight() {
        return super.getWeight() + (fuel.getWeight() * contain);
    }
}
