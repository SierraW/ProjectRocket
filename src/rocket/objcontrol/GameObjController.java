package rocket.objcontrol;

import rocket.environment.Axis;
import rocket.environment.Resistance;
import rocket.environment.Speed;
import rocket.environment.Timer;
import rocket.phisics.Calculator;
import rocket.phisics.Messaging;
import rocket.rocketcore.Rocket;


import java.util.ArrayList;

public class GameObjController implements Runnable {
    Timer timer;
    Resistance resistance;
    boolean fuelOut = true;

    public GameObjController() {
        timer = new Timer();
        resistance = new Resistance();
    }

    public void rocketController(Rocket rocket) {
        if (rocket.getThrottle() <= 0.0 && rocket.getAxis().getYAxis() <= 0.0) {
            return;
        }

        rocket.getTimer().tick();

        if (rocket.isLanded()) {
            return;
        }

        if (rocket.getTimer().gettPlus() * 100 % 10 == 0 || rocket.getTimer().getTickPlus() == 1) {
//            System.out.print("t+:" + rocket.getTimer().gettPlus() + " : ");
//            System.out.println(rocket.printStatus());
            for(int i = 0; i<rocket.getAxis().getYAxis(); i++) {
                System.out.print("=");
            }
            System.out.printf(" t+:%5.2f y:%2.1f\n", rocket.getTimer().gettPlus(), rocket.getAxis().getYAxis());
        }


        Axis axis = rocket.getAxis();
        Speed v0 = rocket.getSpeed();
        Speed p = rocket.burn(rocket.getThrottle());
        if (rocket.getRocketFuelTank().getContain() == 0.0 && fuelOut) {
            fuelOut = false;
            System.out.println("fuel out");
        }
        double m = rocket.calWeight();
        Speed f = resistance.getResistance(p);

        Messaging<Speed, Axis> messaging = Calculator.calRocket(v0, p, f, m, axis);

        rocket.setAxis(messaging.AXIS);
        rocket.setSpeed(messaging.SPEED);
        if (rocket.isLanded()) {
            System.out.println("t+:" + rocket.getTimer().gettPlus() + " " + rocket.printStatus() + " Landed cause:" + rocket.getWarhead().calExplosivePower());
        }
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


    @Override
    public void run() {
        timer.tick();
        ArrayList<GameObj> gameObjs = GameObjRegister.getGameObjects();
        for (GameObj gameObj : gameObjs) {
            if (gameObj instanceof Rocket) {
                if (timer.getTickPlus() == 100) {
                    gameObj.setAxis(new Axis(0,200));
                } else {
                    land((Rocket)gameObj);
                }
                rocketController((Rocket)gameObj);
            }

        }

//        if ((int) (timer.gettPlus() * 100) % 100 == 0) {
//            System.out.printf("Gametime: %4.2f rocket1: t+:%4.2f\n", timer.gettPlus(), gameObjs.get(0).getTimer().gettPlus());
//        }
    }
}
