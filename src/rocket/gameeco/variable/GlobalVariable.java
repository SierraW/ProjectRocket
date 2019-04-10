package rocket.gameeco.variable;

import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class GlobalVariable {

    public static final int GAMEVER = 1000001; //Game version

    public static final String GAMETITLE = "Rocket Game";

    public static final float ROCKETVISIBLERANGE = 1000; //1km;

    public static final int PLAYERBLOCKS = 20;
    public static final int MIDBLOCKS = 10;

    public static final int GAMEMAP_LEFTPADDING = 1;
    public static final int GAMEMAP_RIGHTPADDING = 1;

    public static int screenWidth = 1000;
    public static int screenHeight = 1000;

    public static int structureHeight = 17;

    public static Color globalBackgroundColor = Color.LIGHTGREY;

    public static final int SIZE_GRID_FILE = Integer.SIZE;

    public static float getYPrefix() {
        return screenHeight * 0.9f;
    }

    public static float getWidthPerGrid() {
        float totalGrids = GAMEMAP_LEFTPADDING + GAMEMAP_RIGHTPADDING + PLAYERBLOCKS + PLAYERBLOCKS + MIDBLOCKS;
        return screenWidth / totalGrids;
    }

    public static RandomAccessFile getUserFile() throws FileNotFoundException {
        return new RandomAccessFile("Data/GameGrid.dat", "rw");
    }

    public static String getUserRCFile1() {
        return "Data/user_rc1.dat";
    }

    public static final float MAXPOWERTURNANGLE = 5;
}
