package rocket.rocketcore.rocketfragment;

import rocket.rocketcore.RocketPart;

public class Explosive extends RocketPart {
    private float power;

    public Explosive(float weight, float power) {
        super(weight);
        this.power = power;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }
}
