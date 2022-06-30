import java.util.ArrayList;

public class Monster {

    public static char[] forms = {'T'};

    protected char form;
    protected String color;
    private int x;
    private int y;

    Monster(int i, int j) {
        x = i;
        y = j;

    }

    private int[] coordinatesInDirection(String direction) {
        int[] coord;
        switch (direction) {
            case "right" -> coord = new int[]{x, y + 1};
            case "down" -> coord = new int[]{x + 1, y};
            case "left" -> coord = new int[]{x, y - 1};
            case "up" -> coord = new int[]{x - 1, y};
            default -> coord = null;
        }
        return coord;
    }

    public void deplacerMonster(Terrain terrain, String direction) {
        int[] coord = this.coordinatesInDirection(direction);
        int newX = coord[0];
        int newY = coord[1];
        if (terrain.getIsTroughable(newX, newY)) {
            terrain.leaveTuile(x, y, this);
            terrain.getToTuile(newX, newY, this);
            x = newX;
            y = newY;
            this.monsterOrientation(direction);
        }
    }

    public String afficherMonster() {
        return this.color + this.form;
    }

    public int[] getCoordinates() {
        return new int[]{x, y};
    }

    public boolean isGameOver(ArrayList<Ghost> arrayOfGhost) {
        boolean gameOver = false;
        for (Ghost ghost : arrayOfGhost) {
            if (ghost.getCoordinates()[0] == this.getCoordinates()[0] & ghost.getCoordinates()[1] == this.getCoordinates()[1]) {
                gameOver = true;
            }
        }
        return gameOver;
    }

    public void monsterOrientation(String direction) {
        switch (direction) {
            case "right" -> this.form = forms[0];
            case "left" -> this.form = forms[1];
            case "down" -> this.form = forms[2];
            case "up" -> this.form = forms[3];
        }
    }
}