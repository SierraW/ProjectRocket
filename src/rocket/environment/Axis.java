package rocket.environment;

public class Axis {
    private float xAxis;
    private float yAxis;
    private float angle;

    public Axis(float xAxis, float yAxis){
        this(xAxis, yAxis, 90);
    }

    public Axis(float xAxis, float yAxis, float angle) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.angle = angle;
    }

    public Axis(Axis axis) {
        this(axis.xAxis, axis.yAxis, axis.angle);
    }

    public float getXAxis() {
        return xAxis;
    }

    public void setXAxis(float xAxis) {
        this.xAxis = xAxis;
    }

    public float getYAxis() {
        return yAxis;
    }

    public void setYAxis(float yAxis) {
        this.yAxis = yAxis;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

//    public static float angleConverter(float angle, boolean isDegree) {
//        return isDegree ? angle * Math.PI / 180 : angle * 180 / Math.PI;
//    }

    @Override
    public String toString() {
        return String.format("x:%7.4f y:%7.4f angel:%7.4f", xAxis, yAxis, angle);
    }
}
