import java.util.Random;

public class Ghosts extends Monsters {
    private String color;

    private final char form = 'w';

    private String role;

    public Ghosts(int i, int j) {
        super(i, j);
        switch (j % 3) {
            case 0 -> {
                color = Colors.ANSI_RED;
                role = "follow";
            }
            case 1 -> {
                color = Colors.ANSI_PURPLE;
                role = "follow";
            }
            case 2 -> {
                color = Colors.ANSI_GREEN;
                role = "random";
            }
        }
    }

    public String whereToGo(Terrain terrain, PacMan pacMan) {
        String direction = "";
        int[] ghostCoord = this.getCoordinates();
        int[] terrainLenght = terrain.getLenght();
        switch (this.role) {
            case "follow" -> {
                int[] pacManCoord = pacMan.getCoordinates();
                int[] vector = {ghostCoord[0] - pacManCoord[0], ghostCoord[1] - pacManCoord[1]};
                if ((Math.abs(vector[0]) >= Math.abs(vector[1])) & (vector[0] > 0) & terrain.getIsTroughable(ghostCoord[0] - 1, ghostCoord[1])) {
                    direction = "up";
                } else if ((Math.abs(vector[0]) >= Math.abs(vector[1])) & (vector[0] < 0) & terrain.getIsTroughable(ghostCoord[0] + 1, ghostCoord[1])) {
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
                while (!good) {
                    switch (r) {
                        case 0 -> {
                            direction = "left";
                            good = terrain.getIsTroughable(ghostCoord[0], ghostCoord[1] - 1);
                        }

                        case 1 -> {
                            direction = "up";
                            good = terrain.getIsTroughable(ghostCoord[0] - 1, ghostCoord[1]);
                        }
                        case 2 -> {
                            direction = "right";
                            good = terrain.getIsTroughable(ghostCoord[0], ghostCoord[1] + 1);
                        }
                        case 3 -> {
                            direction = "down";
                            good = terrain.getIsTroughable(ghostCoord[0] + 1, ghostCoord[1]);
                        }
                    }
                    r = new Random().nextInt(4);
                }
            }
        }
        return direction;
    }

    @Override
    public String afficherMonster() {
        return color + form;
    }
}
