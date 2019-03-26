package rocket.objcontrol;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.environment.Timer;

public abstract class GameObj extends Obj{
    private Axis axis;
    private Velocity velocity;
    private boolean landed;
    private Timer timer;

    public GameObj(Axis axis, Velocity velocity) {
        this.axis = axis;
        this.velocity = velocity;
        landed = false;
        timer = new Timer();
    }

    public abstract String getSymbol();

    public abstract String printStatus();

    public Axis getAxis() {
        return new Axis(axis);
    }

    public void setAxis(Axis axis) {
        if (axis.getYAxis() <= 0) {
            setLanded(true);
            axis.setYAxis(0);
        }
        this.axis = axis;
    }

    public Velocity getSpeed() {
        return velocity;
    }

    public void setSpeed(Velocity velocity) {
        this.velocity = velocity;
    }

    public void setLanded(boolean landed) {
        this.landed = landed;
    }

    public boolean isLanded() {
        return landed;
    }

    public Timer getTimer() {
        return timer;
    }
}
