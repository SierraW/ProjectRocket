package rocket.objcontrol;

import rocket.environment.Axis;
import rocket.environment.Speed;
import rocket.environment.Timer;

public abstract class GameObj extends Obj{
    private Axis axis;
    private Speed speed;
    private boolean landed;
    private Timer timer;

    public GameObj(Axis axis, Speed speed) {
        this.axis = axis;
        this.speed = speed;
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

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
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
