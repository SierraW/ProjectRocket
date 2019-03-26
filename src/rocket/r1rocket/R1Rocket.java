package rocket.r1rocket;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.rocketcore.Rocket;

public class R1Rocket extends Rocket {
    public R1Rocket() {
        super(new Axis(0,0, Math.PI / 2), new Velocity(0, Math.PI /2), new r1Engine(), new r1FuelTank(), new r1Warhead());
        this.setName("R1");
    }
}
