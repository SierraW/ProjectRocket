package rocket.environment;

public class Range {
    private float startX;
    private float endX;

    public Range(float startX, float endX) {
        this.startX = startX;
        this.endX = endX;
    }

    public float getStartX() {
        return startX;
    }

    public float getEndX() {
        return endX;
    }

    public float getWidth() {
        return endX - startX;
    }
}
