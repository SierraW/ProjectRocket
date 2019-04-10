package rocket.phisics;

import rocket.environment.Axis;
import rocket.environment.Velocity;

public class Calculator {
    public static Messaging<Velocity, Axis> calRocket(Velocity v0, Velocity power, Velocity resistance, float weight, Axis axis) {
        float vy = v0.getXY().getYAxis();
        float vx = v0.getXY().getXAxis();

        float ay = power.getXY().getYAxis();
        float ax = power.getXY().getXAxis();

        Axis resistanceAxis = resistance.getXY();

        ay = ay / weight + resistanceAxis.getYAxis();
        ax = ax / weight + resistanceAxis.getXAxis();

        float vfx = vx + ax;
        float vfy = vy + ay;

        float fx = axis.getXAxis() + vfx;
        float fy = axis.getYAxis() + vfy;

        Velocity fv = new Velocity(vfx,vfy,true);

        return new Messaging<Velocity, Axis>(fv, new Axis(fx, fy, fv.getAngle()));
    }

}