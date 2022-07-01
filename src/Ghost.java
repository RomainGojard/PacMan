import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Monster {

    private static int counter = 0;

    private String role;

    public Ghost(int i, int j, Terrain terrain) {
        super(i, j, terrain);
        switch (counter) {
            case 0 -> {
                color = Color.ANSI_RED;
                role = "follow";
            }
            case 1 -> {
                color = Color.ANSI_PURPLE;
                role = "follow";
            }
            case 2 -> {
                color = Color.ANSI_GREEN;
                role = "random";
            }
        }
        forms = new char[]{'⋐', '⋑', '⋒', '⋓'};
        form = forms[0];
        counter ++;
    }

    public String whereToGo(Terrain terrain, PacMan pacMan) {
        String direction = "";
        int[] ghostCoord = this.getCoordinates();
        switch (this.role) {
            case "follow" -> {
                int[] pacManCoord = pacMan.getCoordinates();
                int[] vector = {ghostCoord[0] - pacManCoord[0], ghostCoord[1] - pacManCoord[1]};
                boolean isXDistanceBigger = Math.abs(vector[0]) >= Math.abs(vector[1]);
                if (isXDistanceBigger & (vector[0] > 0) & terrain.getIsTroughable(ghostCoord[0] - 1, ghostCoord[1])) {
                    direction = "up";
                } else if (isXDistanceBigger & (vector[0] < 0) & terrain.getIsTroughable(ghostCoord[0] + 1, ghostCoord[1])) {
                    direction = "down";
                } else {
                    if (vector[1] > 0 & terrain.getIsTroughable(ghostCoord[0], ghostCoord[1] - 1)) {
                        direction = "left";
                    } else if (vector[1] <= 0 & terrain.getIsTroughable(ghostCoord[0], ghostCoord[1] + 1)) {
                        direction = "right";
                    } else if (vector[0] > 0 & terrain.getIsTroughable(ghostCoord[0], ghostCoord[1])) {
                        direction = "up";
                    } else {
                        direction = "down";
                    }
                }
            }
            case "random" -> {
                boolean good = false;
                int r = new Random().nextInt(4);
                ArrayList<String> tabOfDirections = new ArrayList<>();
                tabOfDirections.add("left");
                tabOfDirections.add("up");
                tabOfDirections.add("right");
                tabOfDirections.add("down");
                direction = tabOfDirections.get(r);
                while (!good) {
                    switch (direction) {
                        case "left" -> good = terrain.getIsTroughable(ghostCoord[0], ghostCoord[1] - 1);
                        case "up" -> good = terrain.getIsTroughable(ghostCoord[0] - 1, ghostCoord[1]);
                        case "right" -> good = terrain.getIsTroughable(ghostCoord[0], ghostCoord[1] + 1);
                        case "down" -> good = terrain.getIsTroughable(ghostCoord[0] + 1, ghostCoord[1]);
                    }
                    if (!good) {
                        tabOfDirections.remove(r);
                        r = new Random().nextInt(tabOfDirections.size());
                        direction = tabOfDirections.get(r);
                    }
                }
            }
        }
        return direction;
    }
}
