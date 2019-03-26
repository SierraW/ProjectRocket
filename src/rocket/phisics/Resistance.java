package rocket.phisics;

import rocket.environment.Axis;
import rocket.environment.GlobalTickTime;
import rocket.environment.Velocity;

public class Resistance {
    public static Velocity getResistance(Velocity speed, Axis axis) {
        //todo add drag


        return new Velocity( 0 , -getG(axis),true);
    }

    private static double getG(Axis axis) {
        final double g1 = 9.8/ 100 / GlobalTickTime.getTickPerSecond();
        final double r1 = 6400000;
        final double r2 = 6400000 + axis.getYAxis();

        return g1 / Math.pow(r2/r1, 2.0);
    }

    private static double getDrag(double cd, double density, Velocity velocity, double a) { //todo enable
        return cd * ((density*Math.pow(velocity.getSpeed(), 2.0))/2) * a;
    }
}
