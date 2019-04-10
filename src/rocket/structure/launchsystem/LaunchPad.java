package rocket.structure.launchsystem;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.objcontrol.GameObj;
import rocket.rocketcore.Rocket;
import rocket.rocketcore.rocketcontrol.RocketControl;
import rocket.gameeco.prefixrocketfragment.FuelCompressedGas;
import rocket.structure.factory.Factory;

public class LaunchPad extends Factory {
    private Rocket rocket;
    private FuelCompressedGas compressedGas;
    private float amount;
    RocketControl rc;

    public LaunchPad(float xAxis) {
        this(xAxis, 0, 1000, 500);
    }

    public LaunchPad(float xAxis, float amount, int AP, int HP) {
        super(xAxis, AP, HP);
        rocket = null;
        compressedGas = new FuelCompressedGas();
        this.amount = amount;
        rc = new RocketControl();
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
        rocket.setAxis(this.getAxis());
    }

    public void setRc(RocketControl rc) {
        this.rc = rc;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public RocketControl getRc() {
        return rc;
    }

    public void launch() {
        if (rocket == null) {
            return;
        } else {
            rocket.setSpeed(new Velocity(amount * compressedGas.getEnergyPerTon(), rc.getAngle()));
            rocket.getAxis().setAngle(this.getAxis().getAngle());
            rocket.setActive(true);
        }
    }
}
