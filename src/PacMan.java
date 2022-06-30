public class PacMan extends Monster {

    private int score = 0;

    public PacMan(int i, int j) {
        super(i, j);
        forms = new char[]{'<', '>', 'ʌ', 'v'};
        form = forms[0];
        color = Color.ANSI_YELLOW;
    }

    public void score() {
        this.score++;
    }

    public void afficheScore(int maxScore) {
        System.out.println(Color.ANSI_CYAN + this.score + " " + Color.ANSI_WHITE + "/ " + Color.ANSI_CYAN + maxScore);
    }

    public void gameOver(int maxScore) {
        System.out.println(Color.ANSI_PURPLE + "Vous avez perdu !\nVotre score est de " + Color.ANSI_CYAN + this.score + " " + Color.ANSI_WHITE + "/ " + Color.ANSI_CYAN + maxScore);
        System.exit(0);
    }

    public boolean win(int maxScore) {
        if (this.score >= maxScore) {
            System.out.println(Color.ANSI_PURPLE + "Vous avez gagné !\nVous avez atteint le score maximal de  " + Color.ANSI_CYAN + this.score);
            System.exit(0);
            return true;
        } else return false;
    }
}