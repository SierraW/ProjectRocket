package rocket.environment;

public class GlobalTickTime {
    private static float tickPerSecond = 100f;

    public static float getTickPerSecond() {
        return tickPerSecond;
    }

    public static void setTickPerSecond(float tickPerSecond) {
        GlobalTickTime.tickPerSecond = tickPerSecond;
    }
}
