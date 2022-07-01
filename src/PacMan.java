public class PacMan extends Monster {

    private int score = 0;

    public PacMan(int i, int j, Terrain terrain) {
        super(i, j, terrain);
        forms = new char[]{'<', '>', 'ʌ', 'v'};
        form = forms[0];
        color = Color.ANSI_YELLOW;
    }

    public void score() {
        this.score++;
    }

    public String afficheScore() {
        return (Color.ANSI_CYAN + this.score + " " + Color.ANSI_WHITE + "/ " + Color.ANSI_CYAN + terrain.getMaxScore());
    }

    public String gameOver() {
        return (Color.ANSI_PURPLE + "Vous avez perdu !\nVotre score est de " + Color.ANSI_CYAN + this.score + " " + Color.ANSI_WHITE + "/ " + Color.ANSI_CYAN + terrain.getMaxScore());
    }

    public boolean win() {
        if (this.score >= terrain.getMaxScore()) {
            System.out.println(Color.ANSI_PURPLE + "Vous avez gagné !\nVous avez atteint le score maximal de  " + Color.ANSI_CYAN + this.score);
            System.exit(0);
            return true;
        } else return false;
    }

    public void choiceMove() {
        int key = InputControllers.getControl();
        switch (key) {
            case (37) -> this.newDirection = "left";
            case (38) -> this.newDirection = "up";
            case (39) -> this.newDirection = "right";
            case (40) -> this.newDirection = "down";
        }
        if (this.currentDirection == null) {
            actualizeDirection();
        }
    }

    public String whereToGo() {
        String direction = null;
        if (newDirection != null) {
            int[] coord1 = this.coordinatesInDirection(this.newDirection);
            int[] coord2 = this.coordinatesInDirection(this.currentDirection);
            if (terrain.getIsTroughable(coord1[0], coord1[1])) {
                direction = newDirection;
                actualizeDirection();
            } else if (terrain.getIsTroughable(coord2[0], coord2[1])) {
                direction = currentDirection;
            }
        } else direction = currentDirection;
        return direction;
    }

    private void actualizeDirection() {
        this.currentDirection = newDirection;
        this.newDirection = null;
    }


}