package rocket.rocketcore.rocketfragment;

import rocket.rocketcore.RocketPart;

public class Fuel extends RocketPart {
    private double energyPerTon;

    public Fuel(double weightPerTon, double energyPerTon) {
        super(weightPerTon);
        this.energyPerTon = energyPerTon;
    }

    public double getEnergyPerTon() {
        return energyPerTon;
    }
}
