package rocket.environment;

public class Velocity {
    private final float speed;
    private final float angle;

    public Velocity(float speed, float angle) {
        this.speed = speed;
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public float getSpeed() {
        return speed;
    }

    public Axis getXY() {
        double myAngle = angle;
        while (myAngle >= 360) {
            myAngle = myAngle - 360;
        }

        float vY;
        float vX;
        if (myAngle >= 0 && myAngle < 90) {
            vY = speed * (float)Math.sin(Math.toRadians(myAngle));
            vX = speed * (float)Math.cos(Math.toRadians(myAngle));
        } else if (myAngle >= 90 && myAngle < 180) {
            myAngle = 180 - myAngle;
            vY = speed * (float)Math.sin(Math.toRadians(myAngle));
            vX = -(speed * (float)Math.cos(Math.toRadians(myAngle)));
        } else if(myAngle>= 180 && myAngle < 3 * 90) {
            myAngle = myAngle - 180;
            vY = -(speed * (float)Math.sin(Math.toRadians(myAngle)));
            vX = -(speed * (float)Math.cos(Math.toRadians(myAngle)));
        } else {
            myAngle = 360 - myAngle;
            vY = -(speed * (float)Math.sin(Math.toRadians(myAngle)));
            vX = speed * (float)Math.cos(Math.toRadians(myAngle));
        }

        return new Axis(vX,vY);
    }

    public Velocity(double vX, double vY, boolean createNew) {
        if(vX == 0 && vY == 0) {
            this.speed = 0;
            this.angle = 0;
            return;
        }
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
        this.speed = (float)speed;
        this.angle = (float)Math.toDegrees(angle);
    }


    @Override
    public String toString() {
        return String.format("at %4.1f, a: %2.2f", speed, angle);
    }
}
