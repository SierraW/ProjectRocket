package rocket.rocketcore;

import rocket.objcontrol.Obj;

public abstract class RocketPart extends Obj {
    private double weight;
    private boolean connected;

    public RocketPart(double weight) {
        this.weight = weight;
        connected = true;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void addWeight(double weight){
        this.weight += weight;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        return connected;
    }
}
