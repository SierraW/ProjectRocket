package rocket.rocketcore.rocketcomponent;

import rocket.rocketcore.Disconnectable;
import rocket.rocketcore.HoldingParts;
import rocket.rocketcore.RocketPart;
import rocket.rocketcore.rocketfragment.Fuel;

public class RocketFuelTank extends RocketPart implements Disconnectable, HoldingParts {
    private Fuel fuel;
    private double contain;

    public RocketFuelTank(double fuelTankWeight, double contain, Fuel fuel) {
        super(fuelTankWeight);
        this.fuel = fuel;
        this.contain = contain;
    }

    public boolean disconnect() {
        if (isConnected()) {
            setConnected(false);
            return true;
        }
        return false;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void burnFuel(double amount) throws IllegalArgumentException{
        if (amount > contain) {
            throw new IllegalArgumentException("RFT: amount too big");
        }
        contain -= amount;
    }

    public double getContain() {
        return contain;
    }

    public void setContain(double contain) {
        this.contain = contain;
    }

    @Override
    public double calWeight() {
        return super.getWeight() + (fuel.getWeight() * contain);
    }
}
