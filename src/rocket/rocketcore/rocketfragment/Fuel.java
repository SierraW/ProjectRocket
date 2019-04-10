package rocket.rocketcore.rocketfragment;

import rocket.rocketcore.RocketPart;

public class Fuel extends RocketPart {
    private float energyPerTon;

    public Fuel(float weightPerTon, float energyPerTon) {
        super(weightPerTon);
        this.energyPerTon = energyPerTon;
    }

    public float getEnergyPerTon() {
        return energyPerTon;
    }
}
