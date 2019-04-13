package rocket.rocketcore.rocketcontrol;

public class ThrottleControl extends RCSControlProtocol {
    public ThrottleControl(Integer at, Float throttle) {
        super(at, throttle);
    }
    @Override
    public void setControl(Float control) {
        if (control < 0 || control > 1) {
            throw new IllegalArgumentException("ThrottleControl: setControl error");
        }
        super.control = control;
    }
}
