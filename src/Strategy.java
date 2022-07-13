import java.util.ArrayList;
import java.util.Random;

public class Strategy {

    private final String name;

    public Strategy(String name) {
        this.name = name;
    }

    public String follow(Terrain terrain, Ghost ghost) {
        PacMan pacMan = terrain.getPacMan();
        String direction;
        int[] ghostCoord = ghost.getCoordinates();
        int[] pacManCoord = pacMan.getCoordinates();
        int[] vector = {ghostCoord[0] - pacManCoord[0], ghostCoord[1] - pacManCoord[1]};
        boolean isXDistanceBigger = Math.abs(vector[0]) >= Math.abs(vector[1]);
        if (isXDistanceBigger & (vector[0] > 0) & terrain.getIsTroughable(ghostCoord[0] - 1, ghostCoord[1])) {
            direction = "up";
        } else if (isXDistanceBigger & (vector[0] < 0) & terrain.getIsTroughable(ghostCoord[0] + 1, ghostCoord[1])) {
            direction = "down";
        } else if (isXDistanceBigger) {
            int r = new Random().nextInt(2);
            ArrayList<String> tabOfDirections = new ArrayList<>();
            tabOfDirections.add("left");
            tabOfDirections.add("right");
            String testDirection = tabOfDirections.get(r);
            int[] coord = ghost.coordinatesInDirection(testDirection);
            if (terrain.getIsTroughable(coord[0], coord[1])) {
                direction = testDirection;
            } else {
                tabOfDirections.remove(testDirection);
                direction = tabOfDirections.get(0);
            }

        } else {
            if (vector[1] > 0 & terrain.getIsTroughable(ghostCoord[0], ghostCoord[1] - 1)) {
                direction = "left";
            } else if (vector[1] <= 0 & terrain.getIsTroughable(ghostCoord[0], ghostCoord[1] + 1)) {
                direction = "right";
            } else {
                int r = new Random().nextInt(2);
                ArrayList<String> tabOfDirections = new ArrayList<>();
                tabOfDirections.add("up");
                tabOfDirections.add("down");
                String testDirection = tabOfDirections.get(r);
                int[] coord = ghost.coordinatesInDirection(testDirection);
                if (terrain.getIsTroughable(coord[0], coord[1])) {
                    direction = testDirection;
                } else {
                    tabOfDirections.remove(testDirection);
                    direction = tabOfDirections.get(0);
                }
            }
        }
        return direction;
    }

    public String random(Terrain terrain, Ghost ghost) {
        String direction;
        int[] ghostCoord = ghost.getCoordinates();
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
        return direction;
    }

    public String getName() {
        return name;
    }
}