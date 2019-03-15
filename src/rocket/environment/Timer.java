package rocket.environment;

public class Timer {
    private int tickPlus;

    public Timer() {
        tickPlus = 0;
    }

    public double gettPlus() {
        return (double) tickPlus / 100;
    }

    public int getTickPlus() {
        return tickPlus;
    }

    public void tick() {
        tickPlus += 1;
    }
}
