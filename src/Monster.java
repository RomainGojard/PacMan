import java.util.ArrayList;

public class Monster {

    protected char[] forms = {};
    protected Terrain terrain;

    protected long now;

    protected final long moveDelay = 200;

    protected long lastMove;
    protected char form;
    protected String color;
    protected int x;
    protected int y;

    Monster(int i, int j, Terrain terrain) {
        this.terrain = terrain;
        x = i;
        y = j;
        terrain.getToTuile(i, j, this);
        lastMove = 0;
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
        now = System.currentTimeMillis();
        if (now - lastMove >= moveDelay) {
            int[] coord = this.coordinatesInDirection(direction);
            int newX = coord[0];
            int newY = coord[1];
            if (terrain.getIsTroughable(newX, newY)) {
                terrain.leaveTuile(x, y, this);
                terrain.getToTuile(newX, newY, this);
                x = newX;
                y = newY;
                this.monsterOrientation(direction);
                lastMove = now;
            }
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

    private void monsterOrientation(String direction) {
        switch (direction) {
            case "right" -> this.form = this.forms[0];
            case "left" -> this.form = this.forms[1];
            case "down" -> this.form = this.forms[2];
            case "up" -> this.form = this.forms[3];
        }
    }
}