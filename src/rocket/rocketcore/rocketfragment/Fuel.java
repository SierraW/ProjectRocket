package rocket.rocketcore.rocketfragment;

import rocket.rocketcore.RocketPart;

public class Fuel extends RocketPart {
    private float energyPerTon;

    public Fuel(float energyPerTon) {
        super(1);
        this.energyPerTon = energyPerTon;
    }

    public float getEnergyPerTon() {
        return energyPerTon;
    }
}
