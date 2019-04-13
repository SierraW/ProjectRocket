package rocket.gameeco.r2rocket;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.gameeco.r1rocket.r1Warhead;
import rocket.rocketcore.Rocket;

public class R2Rocket extends Rocket {
    public R2Rocket(float xAxis) {
        super(new Axis(xAxis, 0f), new Velocity(0,90), new R2Engine(), new R2FuelTank(), new r1Warhead());
    }
}
