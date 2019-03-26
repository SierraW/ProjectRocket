package rocket.objcontrol;

import rocket.environment.Axis;
import rocket.phisics.Resistance;
import rocket.environment.Velocity;
import rocket.environment.Timer;
import rocket.phisics.Calculator;
import rocket.phisics.Messaging;
import rocket.rocketcore.Rocket;


import java.util.ArrayList;

public class GameObjController {
    Timer timer;
    Resistance resistance;
    boolean fuelOut = true;

    public GameObjController() {
        timer = new Timer();
        resistance = new Resistance();
    }



    private void land(Rocket rocket) {
        if (rocket.getTimer().getTickPlus() == 560) {
            System.out.println("active!");
            rocket.setThrottle(1);
        } else if (rocket.getTimer().getTickPlus() == 580) {
            rocket.setThrottle(0);
        } else if (rocket.getTimer().getTickPlus() == 670) {
            rocket.setThrottle(1);
        } else if (rocket.getTimer().getTickPlus() > 680 && rocket.getTimer().getTickPlus() < 700) {
            if (rocket.getSpeed().getSpeed() > -0.08) {
                rocket.setThrottle(0);
            } else {
                rocket.setThrottle(1);
            }
        } else if (rocket.getTimer().getTickPlus() > 700) {
            if (rocket.getAxis().getYAxis() < 0.5 && timer.getTickPlus() >= 300) {
                if (rocket.getSpeed().getSpeed() > -0.001) {
                    rocket.setThrottle(0);
                } else {
                    rocket.setThrottle(0.11);
                }
            } else {
                if (rocket.getSpeed().getSpeed() > -0.05) {
                    rocket.setThrottle(0);
                } else {
                    rocket.setThrottle(0.5);
                }
            }
        }
    }


    public void run() {
        timer.tick();
        ArrayList<GameObj> gameObjs = GameObjRegister.getGameObjects();
        for (GameObj gameObj : gameObjs) {
            if (gameObj instanceof Rocket) {
                Rocket rocket = (Rocket) gameObj;
//                if (timer.getTickPlus() == 100) {
//                    gameObj.setAxis(new Axis(0,200));
//                } else {
//                    land((Rocket)gameObj);
//                }

                if (timer.getTickPlus() == 100) {
                    ((Rocket) gameObj).setThrottle(1);
                }
                ((Rocket)gameObj).update();
            }

        }

//        if ((int) (timer.gettPlus() * 100) % 100 == 0) {
//            System.out.printf("Gametime: %4.2f rocket1: t+:%4.2f\n", timer.gettPlus(), gameObjs.get(0).getTimer().gettPlus());
//        }
    }
}
