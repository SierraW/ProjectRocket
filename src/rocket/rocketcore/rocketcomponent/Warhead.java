package rocket.rocketcore.rocketcomponent;

import rocket.rocketcore.HoldingParts;
import rocket.rocketcore.RocketPart;
import rocket.rocketcore.rocketfragment.Explosive;

public class Warhead extends RocketPart implements HoldingParts {
    private Explosive[] explosives;

    public Warhead() {
        super(0);
    }

    public void setExplosives(Explosive[] explosives) {
        this.explosives = explosives;
    }

    public Explosive[] getExplosives() {
        return explosives;
    }

    @Override
    public float calWeight() {
        float weight = 0;
        for (Explosive explosive: explosives) {
            weight += explosive.getWeight();
        }
        return weight;
    }

    public float calExplosivePower() {
        float power = 0;
        for (Explosive explosive: explosives) {
            power += explosive.getPower();
        }
        return power;
    }
}
