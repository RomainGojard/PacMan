import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Monster {

    private static int counter = 0;

    private Strategy strategy;

    private boolean free;

    public Ghost(int i, int j, Terrain terrain) {
        super(i, j, terrain);
        switch (counter) {
            case 0 -> {
                color = Color.ANSI_RED;
                strategy = new Strategy("follow");
                free = true;
            }
            case 1 -> {
                color = Color.ANSI_PURPLE;
                strategy = new Strategy("follow");
                free = false;
            }
            case 2 -> {
                color = Color.ANSI_GREEN;
                strategy = new Strategy("random");
                free = false;
            }
            case 3 -> {
                color = Color.ANSI_CYAN;
                strategy = new Strategy("random");
                free = false;
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

    public void free() {
        if (!free) {
            terrain.leaveTuile(x, y, this);
            terrain.getToTuile(x, y + 7, this);
            y = y + 7;
            free = true;
            lastMove = now;
        }
    }

    public boolean getFree() {
        return free;
    }

}
