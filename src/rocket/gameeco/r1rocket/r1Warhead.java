package rocket.gameeco.r1rocket;

import rocket.rocketcore.rocketcomponent.Warhead;
import rocket.rocketcore.rocketfragment.Explosive;
import rocket.gameeco.prefixrocketfragment.ExplosiveTNT;

public class r1Warhead extends Warhead {
    public r1Warhead() {
        super();
        Explosive[] explosives = {new ExplosiveTNT()};
        setExplosives(explosives);
    }
}
