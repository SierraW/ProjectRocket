package rocket.phisics;

import rocket.environment.Axis;
import rocket.environment.Velocity;

public class Calculator {
    public static Messaging<Velocity, Axis> calRocket(Velocity v0, Velocity power, Velocity resistance, double weight, Axis axis) {
        double vy = v0.getXY().getYAxis();
        double vx = v0.getXY().getXAxis();

        double ay = power.getXY().getYAxis();
        double ax = power.getXY().getXAxis();

        ay = ay / weight + resistance.getXY().getYAxis();
        ax = ax / weight + resistance.getXY().getXAxis();

        double vfx = vx + ax;
        double vfy = vy + ay;

        double fx = axis.getXAxis() + vfx;
        double fy = axis.getYAxis() + vfy;

        Velocity fv = new Velocity(vfx,vfy,true);

        return new Messaging<Velocity, Axis>(fv, new Axis(fx, fy, fv.getAngle()));
    }

}