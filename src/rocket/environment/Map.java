package rocket.environment;

public class Map {
    private double groundLevel;
    private double width;
    private double height;

    Map() {
        groundLevel = 3;
        width = 100;
        height = 300;
    }

    public double getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(double groundLevel) {
        this.groundLevel = groundLevel;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
