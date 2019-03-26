package rocket.environment;

public class Axis {
    private double xAxis;
    private double yAxis;
    private double angle;

    public Axis(double xAxis, double yAxis){
        this(xAxis, yAxis, Math.PI / 2);
    }

    public Axis(double xAxis, double yAxis, double angle) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.angle = angle;
    }

    public Axis(Axis axis) {
        this(axis.xAxis, axis.yAxis, axis.angle);
    }

    public double getXAxis() {
        return xAxis;
    }

    public void setXAxis(double xAxis) {
        this.xAxis = xAxis;
    }

    public double getYAxis() {
        return yAxis;
    }

    public void setYAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public static double angleConverter(double angle, boolean isDegree) {
        return isDegree ? angle * Math.PI / 180 : angle * 180 / Math.PI;
    }

    @Override
    public String toString() {
        return String.format("x:%7.4f y:%7.4f a:%7.4f", xAxis, yAxis, angle);
    }
}
