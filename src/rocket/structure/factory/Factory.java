package rocket.structure.factory;

import rocket.environment.Axis;
import rocket.gameeco.structure.FactoryHealth;
import rocket.objcontrol.GameObj;

public class Factory extends GameObj {
    private FactoryHealth health;

    public Factory(float xAxis) {
        super(new Axis(xAxis, 0));
        health = new FactoryHealth();
    }

    public Factory(float xAxis, int AP, int HP) {
        super(new Axis(xAxis, 0));
        health = new FactoryHealth(AP, HP);
    }

}
