package rocket.rocketcore.rocketcontrol;

import rocket.gameeco.variable.GlobalVariable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RCSystemIO {

    public void fileOut() {
        FileOutputStream file;
        ObjectOutputStream outputStream;
        try {
            file = new FileOutputStream(GlobalVariable.getUserRCFile1());
            outputStream = new ObjectOutputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("rocketcontrol.RCSystemIO: File not found.");
        } catch (IOException e) {
            System.out.println("rocketcontrol.RCSystemIO: IOException");
        }


    }


}
