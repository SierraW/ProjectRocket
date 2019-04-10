package rocket.view.gameview;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import rocket.environment.Range;
import rocket.environment.RocketGame;
import rocket.environment.StructureType;
import rocket.gameeco.variable.GlobalVariable;
import rocket.gridsystem.Grid;
import rocket.gridsystem.PlayerGrid;

import java.util.ArrayList;

public class StructureView {
    public Pane getStructureView(Grid grid, boolean isFriendly) {
        Range range = grid.getRange();
        StructureType structureType = grid.getStructureType();
        Rectangle rectangle = new Rectangle(range.getStartX(), GlobalVariable.getYPrefix(), range.getWidth(), GlobalVariable.structureHeight);
        Label label = grid.getLabel();
        label.setTranslateX(range.getStartX());
        label.setTranslateY(GlobalVariable.getYPrefix());
        rectangle.setId(structureType.getName());
        if (isFriendly) {
            rectangle.getStyleClass().add("player");
        } else {
            rectangle.getStyleClass().add("enemy");
        }

        return new Pane(rectangle,label);
    }

    public ArrayList<Pane> getDetailGrids(PlayerGrid playerGrid, boolean isFriendly) {
        ArrayList<Pane> panes = new ArrayList<>();
        for (int i = 0 ; i< GlobalVariable.PLAYERBLOCKS; i++) {
            panes.add(getStructureView(playerGrid.getGrid()[i], isFriendly));
        }
        return panes;
    }

    public ArrayList<Pane> getGrids(RocketGame rocketGame) {
        ArrayList<Pane> panes = new ArrayList<>();

        panes.addAll(getDetailGrids(rocketGame.getPlayerGrid(),true));
        panes.add(getGrid(rocketGame.getMidGrid().getPositionRange(), false, true));
        panes.addAll(getDetailGrids(rocketGame.getEnemyGrid(),false));

        return panes;
    }

    public Pane getGrid(Range range, boolean isFriendly, boolean isMidLand) {
        Rectangle rectangle = new Rectangle(range.getStartX(), GlobalVariable.getYPrefix(), range.getWidth(), GlobalVariable.structureHeight);
        Label label = new Label();
        label.setTranslateX(range.getStartX());
        label.setTranslateY(GlobalVariable.getYPrefix());
        if (isFriendly) {
            rectangle.setId("player");
        } else {
            if (isMidLand) {
                rectangle.setId("midland");
                label.setText("Midland");
            } else {
                rectangle.setId("enemy");
            }
        }

        return new Pane(rectangle,label);
    }

}
