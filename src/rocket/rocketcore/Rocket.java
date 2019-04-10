package rocket.rocketcore;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.gameeco.variable.GlobalVariable;
import rocket.objcontrol.RocketObj;
import rocket.objcontrol.LiveGameObj;
import rocket.phisics.Calculator;
import rocket.phisics.Messaging;
import rocket.phisics.Resistance;
import rocket.rocketcore.rocketcomponent.Engine;
import rocket.rocketcore.rocketcomponent.RocketFuelTank;
import rocket.rocketcore.rocketcomponent.Warhead;
import rocket.rocketcore.rocketcontrol.RocketControl;

public class Rocket extends RocketObj implements HoldingParts, LiveGameObj {
    private int itemID = 1;// todo remove
    private String name;

    private RocketControl rocketControl;

    private Engine engine;
    private RocketFuelTank rocketFuelTank;
    private Warhead warhead;

    private boolean exploded;
    private boolean visibleByEnemy;

    private boolean fuelOut;

    public Rocket(Axis axis, Velocity speed, Engine engine, RocketFuelTank rocketFuelTank, Warhead warhead) {
        super(axis, speed);
        this.name = "Rocket";
        this.engine = engine;
        this.rocketFuelTank = rocketFuelTank;
        this.warhead = warhead;
        rocketControl = new RocketControl();
        this.exploded = false;
        this.fuelOut = false;
        this.visibleByEnemy = false;
    }

    public Velocity burn() throws IllegalArgumentException {
        if (rocketControl.getThrottle() <= 0.0f) {
            return new Velocity(0f, 90);
        }
        if (rocketFuelTank.getContain() <= 0.0f) {
            if (!fuelOut) {
                fuelOut = true;
                System.out.println("Fuel out at " + getTimer().gettPlus()); // todo remove debug
            }
            return new Velocity(0f, 90);
        }
        float pwrPerTick = engine.getPowerPerTick() * rocketControl.getThrottle();
        float fuelBurnt = pwrPerTick / rocketFuelTank.getFuel().getEnergyPerTon();
        if (rocketFuelTank.getContain() < fuelBurnt) { // if not enouht fuel
            float power = rocketFuelTank.getFuel().getEnergyPerTon() * rocketFuelTank.getContain();
            rocketFuelTank.setContain(0);
            return new Velocity(power, rocketControl.getAngle());
        }
        rocketFuelTank.burnFuel(fuelBurnt);
        return new Velocity(fuelBurnt * rocketFuelTank.getFuel().getEnergyPerTon(), rocketControl.getAngle());
    }

    @Override
    public void update() {
        rocketControl.update(this.getTimer().getTickPlus()); // must be update first.

        if (this.rocketControl.getThrottle() <= 0.0 && this.getAxis().getYAxis() <= 0.0) {
            return;
        }

        this.getTimer().tick();

        if (!this.isActive()) {
            return;
        }

        Axis axis = this.getAxis();
        Velocity v0 = this.getSpeed();
        Velocity p = this.burn();
        float m = this.calWeight();
        Velocity f = Resistance.getResistance(p, axis);

        Messaging<Velocity, Axis> messaging = Calculator.calRocket(v0, p, f, m, axis);

        this.setAxis(messaging.AXIS);

        this.setSpeed(messaging.VELOCITY);

        if (this.getAxis().getYAxis() > GlobalVariable.ROCKETVISIBLERANGE) {//check mid point
            this.visibleByEnemy = true;
        }

        if (!this.isActive()) {
            System.out.println("t+:" + this.getTimer().gettPlus() + " " + this.printStatus() + " Landed cause:" + this.getWarhead().calExplosivePower());
        }
    }

    public RocketControl getRocketControl() {
        return rocketControl;
    }

    public void setRocketControl(RocketControl rocketControl) {
        this.rocketControl = rocketControl;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
        this.setActive(false);
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Warhead getWarhead() {
        return warhead;
    }

    public RocketFuelTank getRocketFuelTank() {
        return rocketFuelTank;
    }

    public void setAngle(float angle) {
        this.getAxis().setAngle(angle);
    }

    @Override
    public String getSymbol() {
        return "Rocket [" + this.getIdentifier() + "] " + name + " ID: " + itemID;
    }

    @Override
    public float calWeight() {
        return this.engine.getWeight() + this.rocketFuelTank.calWeight() + this.warhead.calWeight();
    }

    @Override
    public String printStatus() {
        return String.format("%s %s fuel:%5.2f weight:%5.2f throttle:%s speed:%5.3fm/s momentum:%5.2f", getSymbol(), getAxis().toString(), rocketFuelTank.getContain(), this.calWeight(), this.rocketControl.getThrottle(), this.getSpeed().getSpeed() * 100, this.calWeight() * this.getSpeed().getSpeed());
    }

}
