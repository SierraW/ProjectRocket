package rocket.r1rocket;

import rocket.rocketcore.rocketcomponent.Warhead;
import rocket.rocketcore.rocketfragment.Explosive;
import rocket.rocketcore.rocketfragment.prefixmodel.ExplosiveTNT;

public class r1Warhead extends Warhead {
    r1Warhead() {
        super();
        Explosive[] explosives = {new ExplosiveTNT()};
        setExplosives(explosives);
    }
}
