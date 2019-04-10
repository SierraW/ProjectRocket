package rocket.objcontrol;

import rocket.environment.Axis;
import rocket.phisics.Resistance;
import rocket.environment.Timer;
import rocket.rocketcore.Rocket;
import rocket.rocketcore.rocketcontrol.RocketControl;


import java.util.ArrayList;

public class GameObjController {
    Timer timer;
    Resistance resistance;
    boolean fuelOut = true;

    public GameObjController() {
        timer = new Timer();
        resistance = new Resistance();
    }

    public void run() {
        timer.tick();
        ArrayList<RocketObj> rocketObjs = GameObjRegister.getGameObjects();
        for (RocketObj rocketObj : rocketObjs) {
            if (rocketObj instanceof Rocket) {
                Rocket rocket = (Rocket) rocketObj;
//                if (timer.getTickPlus() == 100) {
//                    rocketObj.setAxis(new Axis(0,200));
//                } else {
//                    land((Rocket)rocketObj);
//                }


                if (timer.getTickPlus() == 100) {
                    rocketObj.setActive(true);
                    rocketObj.setAxis(new Axis(0,200));
//                    ((Rocket) rocketObj).getRocketControl().setThrottle(1);
//                    ((Rocket) rocketObj).getRocketControl().setAngle(75,true);
                }
                if (timer.getTickPlus() == 200) {
                    ((Rocket) rocketObj).setRocketControl(new RocketControl());
                }
                if (timer.getTickPlus() == 300) {
                    ((Rocket) rocketObj).setRocketControl(new RocketControl(0,280));
                }
                ((Rocket) rocketObj).update();
            }

        }

//        if ((int) (timer.gettPlus() * 100) % 100 == 0) {
//            System.out.printf("Gametime: %4.2f rocket1: t+:%4.2f\n", timer.gettPlus(), rocketObjs.get(0).getTimer().gettPlus());
//        }
    }
}
