public class Monster {
    protected char[] forms = {};
    protected Terrain terrain;
    protected long now;
    protected long moveDelay = 200;
    protected long lastMove;
    protected char form;
    protected String color;
    protected int x;
    protected int y;
    protected String currentDirection;
    protected String newDirection;
    protected Bonus activeBonus;

    Monster(int i, int j, Terrain terrain) {
        this.terrain = terrain;
        x = i;
        y = j;
        terrain.getToTuile(i, j, this);
        lastMove = 0;
        currentDirection = null;
        newDirection = null;
    }

    protected int[] coordinatesInDirection(String direction) {
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


    public void moveMonster(String direction) {
        int[] coord = this.coordinatesInDirection(direction);
        int newX = coord[0];
        int newY = coord[1];
        if (terrain.getIsTroughable(newX, newY)) {
            terrain.leaveTuile(x, y, this);
            x = newX;
            y = newY;
            terrain.getToTuile(newX, newY, this);
            this.monsterOrientation(direction);
            lastMove = now;
        }
    }

    public void checkMove() {
        checkBonus();
        if (this instanceof PacMan) {
            ((PacMan) this).choiceMove();
            if (currentDirection == null) {
                return;
            }
        }
        now = System.currentTimeMillis();
        String direction;
        if (now - lastMove >= moveDelay) {
            if (this instanceof PacMan) {
                direction = ((PacMan) this).whereToGo();
                if (direction != null) this.moveMonster(direction);
            } else if (this instanceof Ghost) {
                direction = ((Ghost) this).whereToGo();
                moveMonster(direction);
            }
        }
    }

    public String afficherMonster() {
        return this.color + this.form;
    }

    public int[] getCoordinates() {
        return new int[]{x, y};
    }

    private void monsterOrientation(String direction) {
        switch (direction) {
            case "right" -> this.form = this.forms[0];
            case "left" -> this.form = this.forms[1];
            case "down" -> this.form = this.forms[2];
            case "up" -> this.form = this.forms[3];
        }
    }

    public void checkBonus() {
        if (activeBonus != null && activeBonus.getIsEndOfBonus()) {
            if (this instanceof PacMan) {
                moveDelay += activeBonus.getBonusValue();
                color = ((PacMan) this).BASE_COLOR;
            } else if (this instanceof Ghost) {
                moveDelay -= activeBonus.getMalusValue();
            }
            activeBonus = null;
        }
    }

    public void setCoordinates(int[] coordinates) {
        x = coordinates[0];
        y = coordinates[1];
    }
}