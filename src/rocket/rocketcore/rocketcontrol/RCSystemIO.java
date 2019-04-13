package rocket.rocketcore.rocketcontrol;

import rocket.gameeco.variable.GlobalVariable;

import java.io.*;
import java.util.HashMap;

public class RCSystemIO {

    private static RCSystemIO rcSystemIO = null;

    private RCSystemIO() {

    }

    public static RCSystemIO getInstance() {
        if (rcSystemIO == null) {
            rcSystemIO = new RCSystemIO();
            return rcSystemIO;
        } else {
            return rcSystemIO;
        }
    }

    public void writeRCObj(HashMap<Integer, Float> throttleControlMap, HashMap<Integer, Float> powerTurnControlMap, Integer time, int fileID) {
        FileOutputStream file;
        ObjectOutputStream outputStream;
        RCSFile rcsFile = new RCSFile(powerTurnControlMap, throttleControlMap, time);
        try {
            file = new FileOutputStream(GlobalVariable.getUserRCFile(fileID));
            outputStream = new ObjectOutputStream(file);
            outputStream.writeObject(rcsFile);
            outputStream.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("rocketcontrol.RCSystemIO: File not found.");
        } catch (IOException e) {
            System.out.println("rocketcontrol.RCSystemIO: IOException");
        }
    }

    public RCSFile readRCMap(int fileID) {
        try {
            FileInputStream file = new FileInputStream(GlobalVariable.getUserRCFile(fileID));
            ObjectInputStream objectInputStream = new ObjectInputStream(file);
            RCSFile rcsFile= (RCSFile) objectInputStream.readObject();
            objectInputStream.close();
            file.close();
            return rcsFile;
        }catch (FileNotFoundException e) {
            System.out.println("rocketcontrol.RCSystemIO: File not found.");
        } catch (IOException e) {
            System.out.println("rocketcontrol.RCSystemIO: IOException");
        } catch (IllegalArgumentException e){
            System.out.println("rocketcontrol.RCSystemIO: No such file");
        }catch (ClassNotFoundException e) {
            System.out.println("rocketcontrol.RCSystemIO: Class not found.");
        }
        throw new IllegalArgumentException("rocketcontrol.RCSystemIO: No such file");
    }

    public RocketControl rcsFileToControl(RCSFile rcsFile) {
        RocketControl rocketControl = new RocketControl();
        rocketControl.setThrottleControlMap(rcsFile.getThrottleMap());
        rocketControl.setPowerTurnControlMap(rcsFile.getPowerTurnMap());
        rocketControl.setExplodeTime(rcsFile.getExplodeTime());
        return rocketControl;
    }


}
