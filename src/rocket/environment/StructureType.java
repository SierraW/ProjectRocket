package rocket.environment;

import javafx.scene.control.Label;

public enum StructureType {
    Empty, CommandCentre, RocketF1, RocketF2, RocketF3, FuelSupply, SolidFuelSupply, SteelF, RDX_F, HMX_F, CommTower; //RocketLaunchPad;

    public boolean getRequired() {
        switch (this) {
            case CommandCentre:
                return true;
            case RocketF1:
                return true;
            case RocketF2:
                return true;
            case RocketF3:
                return true;
            case FuelSupply:
                return true;
//            case RocketLaunchPad:
//                return true;
            case CommTower:
                return true;
            case SteelF:
                return true;
            case RDX_F:
                return false;
            case HMX_F:
                return false;
            case SolidFuelSupply:
                return false;
            default:
                return false;
        }
    }

    public int getMaxExist() {
        switch (this) {
            case CommTower:
                return 2;
            case RocketF1:
                return 1;
            case RocketF2:
                return 1;
            case RocketF3:
                return 1;
            case FuelSupply:
                return 10;
            case SolidFuelSupply:
                return 1;
            case SteelF:
                return 10;
            case RDX_F:
                return 1;
            case HMX_F:
                return 1;
            case CommandCentre:
                return 1;
//            case RocketLaunchPad:
//                return 15;
            default:
                return 20;
        }
    }

    public String getName() {
        switch (this) {
            case CommandCentre:
                return "Command Centre";
            case SolidFuelSupply:
                return "Solid Fuel Factory";
            case RocketF1:
            case RocketF2:
            case RocketF3:
                return "Rocket Factory and Launch Pad";
            case CommTower:
                return "Communication Tower";
            case SteelF:
                return "Steel Factory";
            case FuelSupply:
                return "Normal Fuel Factory";
            case HMX_F:
                return "HMX Factory";
            case RDX_F:
                return "RDX Factory";
//            case RocketLaunchPad:
//                return "RocketLaunchPad";
            default:
                return "Empty";
        }
    }

    public Label getLabel() {
        switch (this) {
            case CommandCentre:
                return new Label("CC");
            case FuelSupply:
                return new Label("FF");
            case SolidFuelSupply:
                return new Label("SF");
            case RocketF1:
            case RocketF2:
            case RocketF3:
                return new Label("RF");
            case CommTower:
                return new Label("CT");
            case SteelF:
                return new Label("SF");
            case RDX_F:
                return new Label("RF");
            case HMX_F:
                return new Label("HF");
//            case RocketLaunchPad:
//                return new Label("LP");
            default:
                return new Label("");
        }
    }
}
