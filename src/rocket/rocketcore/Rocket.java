package rocket.rocketcore;

import rocket.environment.Axis;
import rocket.environment.Speed;
import rocket.objcontrol.GameObj;
import rocket.rocketcore.rocketcomponent.Engine;
import rocket.rocketcore.rocketcomponent.RocketFuelTank;
import rocket.rocketcore.rocketcomponent.Warhead;

public class Rocket extends GameObj implements HoldingParts{
    private int itemID = 1;
    private String name;

    private double throttle;

    private Engine engine;
    private RocketFuelTank rocketFuelTank;
    private Warhead warhead;

    public Rocket(Axis axis, Speed speed, Engine engine, RocketFuelTank rocketFuelTank, Warhead warhead) {
        super(axis, speed);
        this.name = "Rocket";
        this.engine = engine;
        this.rocketFuelTank = rocketFuelTank;
        this.warhead = warhead;
        this.throttle = 0.0;
    }

    public Speed burn(double throttles) throws IllegalArgumentException {
        return burn(throttles, this.getAxis().getAngle());
    }

    public Speed burn(double throttles, double angle) throws IllegalArgumentException{
        if (throttle <= 0.0) {
            return new Speed(0,0);
        }
        if (rocketFuelTank.getContain() <= 0.0) {
            return new Speed(0,0);
        }
        double pwrPerTick = engine.getPowerPerTick() * throttles;
        double fuelBurnt = pwrPerTick / rocketFuelTank.getFuel().getEnergyPerTon();
        if (rocketFuelTank.getContain() < fuelBurnt) { // if not enouht fuel
            double power = rocketFuelTank.getFuel().getEnergyPerTon() * rocketFuelTank.getContain();
            rocketFuelTank.setContain(0);
            return new Speed(power, angle);
        }
        rocketFuelTank.burnFuel(fuelBurnt);
        return new Speed(fuelBurnt * rocketFuelTank.getFuel().getEnergyPerTon(), angle);
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
        return String.format("%s %s fuel:%5.2f weight:%5.2f throttle:%s momentum:%5.2f", getSymbol(), getAxis().toString(), rocketFuelTank.getContain(), this.calWeight(),this.throttle, this.calWeight()*this.getSpeed().getSpeed());
    }

}
