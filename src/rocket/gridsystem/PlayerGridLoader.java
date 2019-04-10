package rocket.gridsystem;

import rocket.environment.StructureType;
import rocket.gameeco.variable.GlobalVariable;

import java.io.RandomAccessFile;

public class PlayerGridLoader {
    public void load(RandomAccessFile raf, PlayerGrid playerGrid) throws Exception{
        raf.seek(0);
        if (raf.readInt() > GlobalVariable.GAMEVER) {
            throw new IllegalArgumentException("File version not match");
        }
        for(int i = 0; i < GlobalVariable.PLAYERBLOCKS; i++) {
            playerGrid.setStructure(StructureType.values()[raf.readInt()],i);
        }
    }
}
