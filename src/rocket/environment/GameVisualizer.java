package rocket.environment;

public class GameVisualizer {
    private String air = ".";
    private String ground = "=";
    private Map game;

    GameVisualizer(Map map){
        game = map;

    }

    public void draw() {
        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {

            }
        }
    }

}
