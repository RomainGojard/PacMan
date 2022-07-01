import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Monster {

    private static int counter = 0;

    private Strategy strategy;

    public Ghost(int i, int j, Terrain terrain) {
        super(i, j, terrain);
        switch (counter) {
            case 0 -> {
                color = Color.ANSI_RED;
                strategy = new Strategy("follow");
            }
            case 1 -> {
                color = Color.ANSI_PURPLE;
                strategy = new Strategy("follow");
            }
            case 2 -> {
                color = Color.ANSI_GREEN;
                strategy = new Strategy("random");
            }
            case 3 -> {
                color = Color.ANSI_CYAN;
                strategy = new Strategy("random");
            }
        }
        forms = new char[]{'⋐', '⋑', '⋒', '⋓'};
        form = forms[0];
        counter++;
    }

    public String whereToGo() {
        String direction;
        switch (strategy.getName()) {
            case ("follow") -> direction = strategy.follow(terrain, this);
            case ("random") -> direction = strategy.random(terrain, this);
            default -> direction = null;
        }
        return direction;
    }

}
