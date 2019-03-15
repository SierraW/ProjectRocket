package rocket.r1rocket;

import rocket.rocketcore.rocketcomponent.RocketFuelTank;
import rocket.rocketcore.rocketfragment.FuelCompressedGas;

public class r1FuelTank extends RocketFuelTank {
    r1FuelTank() {
        super(1, 5, new FuelCompressedGas());
    }
}
