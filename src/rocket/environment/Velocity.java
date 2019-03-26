package rocket.environment;

public class Velocity {
    private final double speed;
    private final double angle;

    public Velocity(double speed, double angle) {
        this.speed = speed;
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public double getSpeed() {
        return speed;
    }

    public Axis getXY() {
        double myAngle = angle;
        while (myAngle >= 2 * Math.PI) {
            myAngle = myAngle - 2 * Math.PI;
        }
        double vY;
        double vX;
        if (myAngle >= 0 && myAngle < Math.PI / 2) {
            vY = speed * Math.sin(myAngle);
            vX = speed * Math.cos(myAngle);
        } else if (myAngle >= Math.PI / 2 && myAngle < Math.PI) {
            myAngle = Math.PI - myAngle;
            vY = speed * Math.sin(myAngle);
            vX = -(speed * Math.cos(myAngle));
        } else if(myAngle>= Math.PI && myAngle < 3 * Math.PI / 2) {
            myAngle = myAngle - Math.PI;
            vY = -(speed * Math.sin(myAngle));
            vX = -(speed * Math.cos(myAngle));
        } else {
            myAngle = 2* Math.PI - myAngle;
            vY = -(speed * Math.sin(myAngle));
            vX = speed * Math.cos(myAngle);
        }

        return new Axis(vX,vY);
    }

    public Velocity(double vX, double vY, boolean createNew) {
        double speed;
        double angle;
        if (vX < 0) {
            if (vY < 0) {
                vX = Math.abs(vX);
                vY = Math.abs(vY);
                speed = Math.sqrt(Math.pow(vX,2.0) + Math.pow(vY, 2.0));
                angle = Math.asin(vY/speed) + Math.PI;
            } else {
                vX = Math.abs(vX);
                speed = Math.sqrt(Math.pow(vX,2.0) + Math.pow(vY, 2.0));
                angle = Math.PI - Math.asin(vY/speed);
            }
        } else {
            if (vY < 0) {
                vY = Math.abs(vY);
                speed = Math.sqrt(Math.pow(vX,2.0) + Math.pow(vY, 2.0));
                angle = 2*Math.PI - Math.asin(vY/speed);
            } else {
                speed = Math.sqrt(Math.pow(vX,2.0) + Math.pow(vY, 2.0));
                angle = Math.asin(vY/speed);
            }
        }
        this.speed = speed;
        this.angle = angle;
    }


    @Override
    public String toString() {
        return String.format("at %4.1f, a: %2.2f", speed, angle);
    }
}
