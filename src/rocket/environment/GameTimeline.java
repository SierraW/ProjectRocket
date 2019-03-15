package rocket.environment;

import java.util.concurrent.*;

public class GameTimeline {
    void update(Runnable runnable) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.MILLISECONDS);
    }
}