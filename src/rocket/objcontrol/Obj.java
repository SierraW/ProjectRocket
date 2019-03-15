package rocket.objcontrol;

public class Obj {
    private int identifier;

    public Obj() {
        setIdentifier();
    }

    public void setIdentifier() {
        this.identifier = GameObjRegister.register();
    }

    public int getIdentifier() {
        return identifier;
    }
}
