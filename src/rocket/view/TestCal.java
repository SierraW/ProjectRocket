package rocket.view;

import rocket.environment.Axis;
import rocket.environment.GameTimeline;
import rocket.environment.Velocity;
import rocket.gameeco.r1rocket.R1Rocket;
import rocket.objcontrol.RocketObj;
import rocket.phisics.Calculator;
import rocket.phisics.Messaging;
import rocket.phisics.Resistance;
import rocket.rocketcore.Rocket;
import rocket.rocketcore.rocketcontrol.RCSFile;
import rocket.rocketcore.rocketcontrol.RocketControl;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestCal{
    public static void main(String[] args) {


        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        GameTimeline gameTimeline = new GameTimeline();

        Runnable runnable = () -> {
            System.out.println("gugu");
        };
        //service.scheduleAtFixedRate(runnable,1, 1 , TimeUnit.SECONDS);

        gameTimeline.update(runnable);


    }
}
