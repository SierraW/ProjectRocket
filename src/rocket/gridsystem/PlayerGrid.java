package rocket.gridsystem;

import rocket.environment.StructureType;
import rocket.gameeco.variable.GlobalVariable;

public class PlayerGrid extends MidGrid {
    private Grid[] grids;

    public PlayerGrid(float startFrom, float endAt) {
        super(startFrom, endAt);
        grids = new Grid[GlobalVariable.PLAYERBLOCKS];

        float eachGridSize = this.getPositionRange().getWidth() / GlobalVariable.PLAYERBLOCKS;
        float startPt = startFrom;
        float endPt = startPt + eachGridSize;

        for (int i = 0; i < GlobalVariable.PLAYERBLOCKS; i++) {
            grids[i] = new Grid(startPt, endPt);
            startPt = endPt;
            endPt = startPt + eachGridSize;
        }
    }

    public boolean setStructure(StructureType structure, int position) throws IllegalArgumentException {
        if (position < 0 || position >= GlobalVariable.PLAYERBLOCKS) {
            throw new IllegalArgumentException("rocket.gridsystem.PlayerGrid: invalid position.");
        }
        if (grids[position] != null) {
            grids[position].setStructureType(structure);
            return true;
        }
        return false;
    }
    
    public void setExposed(int pos) {
        if (pos < 0 || pos >= GlobalVariable.PLAYERBLOCKS) {
            throw new IllegalArgumentException("rocket.gridsystem.PlayerGrid.setExposed: invalid position.");
        }
        grids[pos].setExposed(true);
    }

    public void setExposedAll() {
        for(Grid grid: grids) {
            grid.setExposed(true);
        }
    }

    public Grid[] getGrid() {
        return grids;
    }

    public float getRocketLaunchPadXAxis() {
        for(Grid grid: grids) {
            if (grid.getStructureType() == StructureType.RocketLaunchPad) {
                return grid.getRange().getStartX();
            }
        }
        return 10;
    }
}
