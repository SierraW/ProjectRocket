package rocket.view;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.gameeco.r1rocket.R1Rocket;
import rocket.phisics.Calculator;
import rocket.phisics.Messaging;
import rocket.phisics.Resistance;
import rocket.rocketcore.Rocket;
import rocket.rocketcore.rocketcontrol.RocketControl;

public class TestCal {
    public static void main(String[] args) {
        Calculator calculator =  new Calculator();
        Velocity v0 = new Velocity(0,90);
        Rocket rocket = new R1Rocket(10f);
        rocket.setRocketControl(new RocketControl(1,75));
        rocket.setActive(true);
        Velocity power = rocket.burn();
        Velocity resistance = Resistance.getResistance(rocket.getSpeed(), new Axis(0,0));
        float weight = 1f;
        Axis axis = new Axis(0,0);
        Messaging<Velocity, Axis> messaging = Calculator.calRocket(v0, power, resistance, weight, axis);
        int count = 0;
        while (true) {
            rocket.update();
            messaging = Calculator.calRocket(messaging.VELOCITY, rocket.burn(), Resistance.getResistance(new Velocity(0,0),rocket.getAxis()), rocket.calWeight(), messaging.AXIS);
            System.out.println("angle " + messaging.AXIS.getAngle() + " x:"+messaging.AXIS.getXAxis() + " y:" +messaging.AXIS.getYAxis());
            if(rocket.getRocketFuelTank().getContain() < 6.0f) {
                System.out.println("nearly");
            }
        }

    }
}
