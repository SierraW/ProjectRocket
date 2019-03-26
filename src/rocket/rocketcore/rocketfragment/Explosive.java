package rocket.rocketcore.rocketfragment;

import rocket.rocketcore.RocketPart;

public class Explosive extends RocketPart {
    private double power;

    public Explosive(double weight, double power) {
        super(weight);
        this.power = power;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
