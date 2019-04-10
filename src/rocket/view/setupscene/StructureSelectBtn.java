package rocket.view.setupscene;

import javafx.scene.control.Button;
import rocket.environment.StructureType;

public class StructureSelectBtn extends Button {
    private StructureType structureType;
    private boolean structureSelection;
    private int setCount;

    public StructureSelectBtn(boolean structureSelection) {
        super();
        structureType = StructureType.Empty;
        setCount = 0;
        this.structureSelection = structureSelection;
    }

    public void setStructureType(StructureType structureType) {
        this.structureType = structureType;

        updateText();
    }

    private void updateText() {
        if (structureSelection) {
            this.setText(structureType.getName() + " " + (structureType.getMaxExist() - setCount));
        } else {
            this.setText(structureType.getName());
        }
    }

    public boolean isStructureSelection() {
        return structureSelection;
    }

    public boolean setToGrid() {
        if (setCount < structureType.getMaxExist()) {
            setCount++;
            updateText();
            return true;
        }
        return false;
    }

    public StructureType getStructureType() {
        return structureType;
    }
}
