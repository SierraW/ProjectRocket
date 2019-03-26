package rocket.environment;

public class GlobalTickTime {
    private static double tickPerSecond = 100;

    public static double getTickPerSecond() {
        return tickPerSecond;
    }

    public static void setTickPerSecond(double tickPerSecond) {
        GlobalTickTime.tickPerSecond = tickPerSecond;
    }
}
