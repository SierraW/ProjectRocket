package rocket.environment;

public class Range2D extends Range{
    private float startY;
    private float endY;

    public Range2D(float startX, float endX) {
        this(startX, endX, 0,0);
    }

    public Range2D(float startX, float endX, float startY, float endY ) {
        super(startX, endX);
        this.startY = startY;
        this.endY = endY;
    }
}
