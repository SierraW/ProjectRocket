package rocket.environment;

import rocket.gameeco.variable.GlobalVariable;
import rocket.gridsystem.MidGrid;
import rocket.gridsystem.PlayerGrid;
import rocket.objcontrol.LiveGameObj;

public class RocketGame implements LiveGameObj {
    private PlayerGrid playerGrid;
    private PlayerGrid enemyGrid;
    private MidGrid midGrid;

    private float screenSize;

    public RocketGame(float screenSize){
        this.screenSize = screenSize;

        float leftPadding = GlobalVariable.GAMEMAP_LEFTPADDING * GlobalVariable.getWidthPerGrid() ;
        float midStarts = leftPadding + GlobalVariable.PLAYERBLOCKS * GlobalVariable.getWidthPerGrid();
        float midEnds = midStarts + GlobalVariable.MIDBLOCKS * GlobalVariable.getWidthPerGrid();

        playerGrid = new PlayerGrid(leftPadding, midStarts);
        playerGrid.setExposedAll();
        midGrid = new MidGrid(midStarts , midEnds);
        enemyGrid = new PlayerGrid(midEnds, midEnds + GlobalVariable.PLAYERBLOCKS * GlobalVariable.getWidthPerGrid());

    }

    public PlayerGrid getPlayerGrid() {
        return playerGrid;
    }

    public MidGrid getMidGrid() {
        return midGrid;
    }

    public PlayerGrid getEnemyGrid() {
        return enemyGrid;
    }

    @Override
    public void update() {

    }
}
