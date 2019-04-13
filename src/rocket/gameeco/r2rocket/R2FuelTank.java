package rocket.gameeco.r2rocket;

import rocket.gameeco.prefixrocketfragment.FuelFossilFuel;
import rocket.rocketcore.rocketcomponent.RocketFuelTank;

public class R2FuelTank extends RocketFuelTank {
    R2FuelTank() {
        super(10,70, new FuelFossilFuel());
    }
}
