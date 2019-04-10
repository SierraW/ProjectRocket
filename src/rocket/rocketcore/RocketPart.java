package rocket.rocketcore;

import rocket.objcontrol.Obj;

public abstract class RocketPart extends Obj { // change it to RocketObj
    private float weight;
    private boolean connected;

    public RocketPart(float weight) {
        this.weight = weight;
        connected = true;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void addWeight(float weight){
        this.weight += weight;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        return connected;
    }
}
