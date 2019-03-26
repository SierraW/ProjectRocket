package rocket.rocketcore.rocketcontrol;

import rocket.environment.Axis;

public class RocketControl {
    private double throttle;
    private double angle;

    public void setAngle(double angle, boolean isDegree) {
        if (isDegree) {
            this.angle = Axis.angleConverter(angle, true);
        } else {
            this.angle = angle;
        }
    }

    public double getAngle(boolean radian) {
        if (radian) {
            return angle;
        } else {
            return Axis.angleConverter(angle, false);
        }
    }

    public void setThrottle(double throttle) throws IllegalArgumentException {
        if (throttle > 1 || throttle < 0) {
            throw new IllegalArgumentException("RC: throttle must between 0 and 1");
        }
        this.throttle = throttle;
    }

    public double getThrottle() {
        return throttle;
    }
}
