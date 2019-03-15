package rocket.environment;

import rocket.objcontrol.GameObjController;
import rocket.objcontrol.GameObjRegister;
import rocket.r1rocket.R1Rocket;
import rocket.rocketcore.Rocket;

public class Test {
    public static void main(String[] args) {
        GameObjController tick = new GameObjController();
        Rocket myRocket = new R1Rocket();
        GameObjRegister.signObj(myRocket);

        GameTimeline timeline = new GameTimeline();
        timeline.update(tick);


    }

}
