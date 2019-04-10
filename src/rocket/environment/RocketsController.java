package rocket.environment;

import rocket.objcontrol.RocketObj;
import rocket.objcontrol.LiveGameObj;

import java.util.ArrayList;

public class RocketsController implements LiveGameObj {
    private ArrayList<RocketObj> rocketObjs;

    private boolean active;
    private long start;
    private long end;
    private long timePlus;

    public RocketsController(){
        rocketObjs = new ArrayList<RocketObj>();
        active = false;
    }

    @Override
    public void update() {
        if (active) {
            for (RocketObj rocketObj : rocketObjs) {
                if (rocketObj instanceof LiveGameObj) {
                    ((LiveGameObj) rocketObj).update();
                }
            }
            timePlus = System.currentTimeMillis() - start;
        }
    }

    public void activate() {
        this.active = true;
        start = System.currentTimeMillis();
    }

    public void deactivate() {
        this.active = false;
        end = System.currentTimeMillis();
        timePlus = end - start;
    }

    public boolean isActive() {
        return active;
    }

    public void addGameObjs(RocketObj rocketObj) {
        this.rocketObjs.add(rocketObj);
    }

    public void setRocketObjs(ArrayList<RocketObj> rocketObjs) {
        this.rocketObjs = rocketObjs;
    }

    public ArrayList<RocketObj> getRocketObjs() {
        return rocketObjs;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public long getTimePlus() {
        return timePlus;
    }
}
