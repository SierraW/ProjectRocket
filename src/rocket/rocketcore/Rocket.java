package rocket.rocketcore;

import rocket.environment.Axis;
import rocket.environment.Velocity;
import rocket.objcontrol.GameObj;
import rocket.objcontrol.LiveGameObj;
import rocket.phisics.Calculator;
import rocket.phisics.Messaging;
import rocket.phisics.Resistance;
import rocket.rocketcore.rocketcomponent.Engine;
import rocket.rocketcore.rocketcomponent.RocketFuelTank;
import rocket.rocketcore.rocketcomponent.Warhead;

public class Rocket extends GameObj implements HoldingParts, LiveGameObj {
    private int itemID = 1;// todo remove
    private String name;

    private double throttle;//todo remove

    private Engine engine;
    private RocketFuelTank rocketFuelTank;
    private Warhead warhead;

    public Rocket(Axis axis, Velocity speed, Engine engine, RocketFuelTank rocketFuelTank, Warhead warhead) {
        super(axis, speed);
        this.name = "Rocket";
        this.engine = engine;
        this.rocketFuelTank = rocketFuelTank;
        this.warhead = warhead;
        this.throttle = 0.0;
    }

    public Velocity burn(double throttles) throws IllegalArgumentException {
        return burn(throttles, this.getAxis().getAngle());
    }

    public Velocity burn(double throttles, double angle) throws IllegalArgumentException {
        if (throttle <= 0.0) {
            return new Velocity(0, 0);
        }
        if (rocketFuelTank.getContain() <= 0.0) {
            return new Velocity(0, 0);
        }
        double pwrPerTick = engine.getPowerPerTick() * throttles;
        double fuelBurnt = pwrPerTick / rocketFuelTank.getFuel().getEnergyPerTon();
        if (rocketFuelTank.getContain() < fuelBurnt) { // if not enouht fuel
            double power = rocketFuelTank.getFuel().getEnergyPerTon() * rocketFuelTank.getContain();
            rocketFuelTank.setContain(0);
            return new Velocity(power, angle);
        }
        rocketFuelTank.burnFuel(fuelBurnt);
        return new Velocity(fuelBurnt * rocketFuelTank.getFuel().getEnergyPerTon(), angle);
    }

    @Override
    public void update() {
        if (this.getThrottle() <= 0.0 && this.getAxis().getYAxis() <= 0.0) {
            return;
        }

        this.getTimer().tick();

        if (this.isLanded()) {
            return;
        }

        Axis axis = this.getAxis();
        Velocity v0 = this.getSpeed();
        Velocity p = this.burn(this.getThrottle(), Math.PI / 4);
        double m = this.calWeight();
        Velocity f = Resistance.getResistance(p, axis);

        Messaging<Velocity, Axis> messaging = Calculator.calRocket(v0, p, f, m, axis);

        this.setAxis(messaging.AXIS);
        this.setSpeed(messaging.VELOCITY);
        if (this.isLanded()) {
            System.out.println("t+:" + this.getTimer().gettPlus() + " " + this.printStatus() + " Landed cause:" + this.getWarhead().calExplosivePower());
        }
    }

    public double getThrottle() {
        return throttle;
    }

    public void setThrottle(double throttle) {
        this.throttle = throttle;
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

    public void setAngle(double angle) {
        this.getAxis().setAngle(angle);
    }

    @Override
    public String getSymbol() {
        return "Rocket [" + this.getIdentifier() + "] " + name + " ID: " + itemID;
    }

    @Override
    public double calWeight() {
        return this.engine.getWeight() + this.rocketFuelTank.calWeight() + this.warhead.calWeight();
    }

    @Override
    public String printStatus() {
        return String.format("%s %s fuel:%5.2f weight:%5.2f throttle:%s speed:%5.3fm/s momentum:%5.2f", getSymbol(), getAxis().toString(), rocketFuelTank.getContain(), this.calWeight(), this.throttle, this.getSpeed().getSpeed() * 100, this.calWeight() * this.getSpeed().getSpeed());
    }

}
