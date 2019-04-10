package rocket.gridsystem;

import rocket.environment.Range;

public class MidGrid {
    private Range positionRange;

    public MidGrid(float startFrom, float endAt) {
        positionRange = new Range(startFrom, endAt);
    }

    public Range getPositionRange() {
        return positionRange;
    }
}
