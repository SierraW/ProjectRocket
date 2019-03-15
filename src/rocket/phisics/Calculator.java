package rocket.phisics;

import rocket.environment.Axis;
import rocket.environment.Speed;

public class Calculator {
    public static Messaging<Speed, Axis> calRocket(Speed v0, Speed power, Speed resistance, double weight, Axis axis) {
        double vy = v0.getSpeed();

        double ay;

        ay = power.getSpeed()/weight - resistance.getSpeed();

        double vAngle = 0;
        double fAngle = 0;

        if (ay > 0) {
            vAngle = Math.PI / 2;
        } else {
            vAngle = 3*Math.PI / 2;
        }

        double vfy = vy + ay;

        if (vfy > 0) {
            fAngle = Math.PI / 2;
        } else {
            fAngle = 3*Math.PI / 2;
        }

        double fy = axis.getYAxis() + vfy;

        return new Messaging<Speed, Axis>(new Speed(vfy, vAngle), new Axis(0, fy, fAngle));
    }
}
