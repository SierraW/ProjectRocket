package rocket.objcontrol;

import rocket.environment.Axis;

public class GameObj extends Obj{
    private Axis axis;

    public GameObj(Axis axis) {
        setAxis(axis);
    }

    public Axis getAxis() {
        return axis;
    }

    public void setAxis(Axis axis) {
        this.axis = axis;
    }
}
