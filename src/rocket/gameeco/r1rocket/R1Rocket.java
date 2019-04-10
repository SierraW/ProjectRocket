package rocket.gameeco.r1rocket;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.rocketcore.Rocket;

public class R1Rocket extends Rocket {
    public R1Rocket(float xAxis) {
        super(new Axis(xAxis,0, 90), new Velocity(0, 90), new r1Engine(), new r1FuelTank(), new r1Warhead());
        this.setName("R1");
    }
}
