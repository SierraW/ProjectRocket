package rocket.environment;

public class Timer {
    private int tickPlus;

    public Timer() {
        tickPlus = 0;
    }

    public float gettPlus() {
        return (float) tickPlus / GlobalTickTime.getTickPerSecond();
    }

    public int getTickPlus() {
        return tickPlus;
    }

    public void tick() {
        tickPlus += 1;
    }
}
