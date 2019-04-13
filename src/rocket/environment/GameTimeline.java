package rocket.environment;

import java.util.concurrent.*;

public class GameTimeline {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
    public void update(Runnable runnable) {

        executor.scheduleAtFixedRate(runnable, 10, 10, TimeUnit.MILLISECONDS);
    }
}
