package rocket.objcontrol;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.environment.Timer;

public abstract class RocketObj extends GameObj{
    private Velocity velocity;
    private boolean active;
    private Timer timer;

    public RocketObj(Axis axis, Velocity velocity) {
        super(axis);
        this.velocity = velocity;
        active = false;
        timer = new Timer();
    }

    public abstract String getSymbol();

    public abstract String printStatus();

    public void setAxis(Axis axis) {
        if (axis.getYAxis() < 0) {
            setActive(false);
            axis.setYAxis(0);
        }
        super.setAxis(axis);
    }

    public Velocity getSpeed() {
        return velocity;
    }

    public void setSpeed(Velocity velocity) {
        this.velocity = velocity;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public Timer getTimer() {
        return timer;
    }
}
