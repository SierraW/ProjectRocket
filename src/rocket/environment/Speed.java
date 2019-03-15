package rocket.environment;

public class Speed {
    private final double speed;
    private final double angle;

    public Speed(double speed, double angle) {
        this.speed = speed;
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public double getSpeed() {
        return speed;
    }


    @Override
    public String toString() {
        return String.format("at %4.1f, a: %2.2f", speed, angle);
    }
}
