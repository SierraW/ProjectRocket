package rocket.gameeco.prefixstage;

import rocket.environment.RocketsController;

public class PrefixStage {
    public enum Stage {
        ONE, TWO, THREE;
    }

    private static PrefixStage prefixStage = null;

    private Stage currentSelectedStage;

    private PrefixStage() {
        currentSelectedStage = Stage.ONE;
    }

    public static PrefixStage getInstance() {
        if (prefixStage == null) {
            prefixStage = new PrefixStage();
            return prefixStage;
        } else {
            return prefixStage;
        }
    }

    public Stage getCurrentSelectedStage() {
        return currentSelectedStage;
    }

    public void setCurrentSelectedStage(Stage currentSelectedStage) {
        this.currentSelectedStage = currentSelectedStage;
    }

    public RocketsController getCurrentRocketsController() {
        RocketsController rc= new RocketsController();
        switch (currentSelectedStage) {
            case TWO:
                //break;
            case THREE:
                //break;
            default:
            case ONE:
                return rc;
        }

    }
}
