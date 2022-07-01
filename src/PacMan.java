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
}