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

    public void afficheScore() {
        System.out.println(Colors.ANSI_CYAN + this.score);
    }

    public void gameOver(){
        System.out.println(Colors.ANSI_WHITE + "Vous avez perdu !\nVotre score est de " + Colors.ANSI_CYAN + this.score);
    }

}