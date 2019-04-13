package rocket.rocketcore.rocketcontrol;

import rocket.gameeco.variable.GlobalVariable;

public class PowerTurnControl extends RCSControlProtocol {
    public PowerTurnControl(Integer at, Float angel) {
        super(at, angel);
    }

    @Override
    public void setControl(Float control) {
        if (control < -GlobalVariable.MAXPOWERTURNANGLE || control > GlobalVariable.MAXPOWERTURNANGLE) {
            throw new IllegalArgumentException("PowerTurnControl: setControl error");
        }
        super.control = control;
    }
}
