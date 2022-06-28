import java.util.Arrays;
import java.util.ArrayList;

public class Monsters {

    protected char form;
    protected String color;
    private int x;
    private int y;

    Monsters(int i, int j) {
        x = i;
        y = j;
    }

    public void deplacerMonster(Terrain terrain, String direction) {
        switch (direction) {
            case "right" -> {
                if (terrain.getIsTroughable(x, y + 1)) {
                    if (this instanceof PacMan) {
                        terrain.switchTuilePacMan(x, y, x, y + 1);
                        ((PacMan) this).pacManOrientation(direction);
                    } else if (this instanceof Ghosts) {
                        terrain.switchTuileGhost((Ghosts) this, x, y, x, y + 1);
                    }
                    y++;
                }
            }
            case "down" -> {
                if (terrain.getIsTroughable(x + 1, y)) {
                    if (this instanceof PacMan) {
                        terrain.switchTuilePacMan(x, y, x + 1, y);
                        ((PacMan) this).pacManOrientation(direction);
                    } else if (this instanceof Ghosts) {
                        terrain.switchTuileGhost((Ghosts) this, x, y, x + 1, y);
                    }
                    x++;
                }
            }
            case "left" -> {
                if (terrain.getIsTroughable(x, y - 1)) {
                    if (this instanceof PacMan) {
                        terrain.switchTuilePacMan(x, y, x, y - 1);
                        ((PacMan) this).pacManOrientation(direction);
                    } else if (this instanceof Ghosts) {
                        terrain.switchTuileGhost((Ghosts) this, x, y, x, y - 1);
                    }
                    y--;
                }
            }
            case "up" -> {
                if (terrain.getIsTroughable(x - 1, y)) {
                    if (this instanceof PacMan) {
                        terrain.switchTuilePacMan(x, y, x - 1, y);
                        ((PacMan) this).pacManOrientation(direction);
                    } else if (this instanceof Ghosts) {
                        terrain.switchTuileGhost((Ghosts) this, x, y, x - 1, y);
                    }
                    x--;
                }
            }
        }
    }

    public String afficherMonster() {
        return this.color + this.form;
    }

    public int[] getCoordinates() {
        return new int[]{x, y};
    }

    public boolean gameOver(ArrayList<Ghosts> arrayOfGhost) {
        boolean gameOver = false;
        for (Ghosts ghost : arrayOfGhost) {
            if (ghost.getCoordinates()[0] == this.getCoordinates()[0] & ghost.getCoordinates()[1] == this.getCoordinates()[1]) {
                gameOver = true;
            }
        }
        return gameOver;
    }
}