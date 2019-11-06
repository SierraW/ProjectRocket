package rocket.gameeco.variable;

import javafx.scene.paint.Color;
import rocket.rocketcore.rocketcontrol.RocketControl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class GlobalVariable {

    public static final int GAMEVER = 1000001; //Game version

    public static final String GAMETITLE = "Rocket Game";

    public static final float ROCKETVISIBLERANGE = 1000; //1km;

    public static final int PLAYERBLOCKS = 10;
    public static final int MIDBLOCKS = 6;

    public static final int GAMEMAP_LEFTPADDING = 1;
    public static final int GAMEMAP_RIGHTPADDING = 1;

    public static int screenWidth = 1200;
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

    public static File getUserRCFile(int id) {
        switch (id) {
            case 1:
                return new File( "Data/user2.dat");
            case 2:
                return new File("Data/user3.dat");
            case 3:
                return new File( "Data/user4.dat");
            case 4:
                return new File("Data/user5.dat");
            case 5:
                return new File( "Data/user6.dat");
            case 6:
                return new File("Data/user7.dat");
            case 7:
                return new File("Data/empty.dat");
            default:
                return new File( "Data/user1.dat");
        }
    }

    public static int currentSelectedRCFileIndex = 6;

    public static final float MAXPOWERTURNANGLE = 5;
}
