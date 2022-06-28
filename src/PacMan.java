public class PacMan extends Monsters {
    private char form = '<';

    private int score = 0;

    public PacMan(int i, int j) {
        super(i, j);
    }

    @Override
    public String afficherMonster() {
        String color = Colors.ANSI_YELLOW;

        return color + form;
    }

    public void pacManOrientation(String direction) {
        switch (direction) {
            case "right" -> this.form = '<';

            case "down" -> form = 'ʌ';

            case "left" -> form = '>';

            case "up" -> form = 'v';
        }
    }

    public void score() {
        this.score++;
    }

    public void afficheScore(int maxScore) {
        System.out.println(Colors.ANSI_CYAN + this.score + " " + Colors.ANSI_WHITE + "/ " + Colors.ANSI_CYAN + maxScore);
    }

    public void gameOver(int maxScore) {
        System.out.println(Colors.ANSI_PURPLE + "Vous avez perdu !\nVotre score est de " + Colors.ANSI_CYAN + this.score + " " + Colors.ANSI_WHITE + "/ " + Colors.ANSI_CYAN + maxScore);
    }

    public boolean win(int maxScore) {
        if (this.score >= maxScore) {
            System.out.println(Colors.ANSI_PURPLE + "Vous avez gagné !\nVous avez atteint le score maximal de  " + Colors.ANSI_CYAN + this.score);
            return true;
        } else return false;
    }
}