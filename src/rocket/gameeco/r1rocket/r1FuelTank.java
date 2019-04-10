package rocket.gameeco.r1rocket;

import rocket.rocketcore.rocketcomponent.RocketFuelTank;
import rocket.gameeco.prefixrocketfragment.FuelCompressedGas;

public class r1FuelTank extends RocketFuelTank {
    r1FuelTank() {
        super(1, 8, new FuelCompressedGas());
    }
}
