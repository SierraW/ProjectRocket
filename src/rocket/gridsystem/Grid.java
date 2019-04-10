package rocket.gridsystem;

import javafx.scene.control.Label;
import rocket.environment.Range;
import rocket.environment.StructureType;

public class Grid {
    private Range range;
    private StructureType structureType;
    private boolean exposed;

    public Grid(float startFrom, float endAt) {
        range = new Range(startFrom, endAt);
        structureType = StructureType.Empty;
        exposed = false;
    }

    public Range getRange() {
        return range;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public void setStructureType(StructureType structureType) {
        this.structureType = structureType;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public Label getLabel() {
        if (isExposed()) {
            return structureType.getLabel();
        }
        return new Label();
    }
}
