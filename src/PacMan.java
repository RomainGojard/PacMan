public class PacMan extends Monsters{
    private char form = '<';

    public PacMan(int i, int j){
        super(i, j);
    }

    @Override
    public String afficherMonster() {
        String color = Colors.ANSI_YELLOW;

        return color + form;
    }

    public void pacManOrientation(String direction){
        switch(direction){
            case "right" -> this.form = '<';

            case "down" -> form = 'ʌ';

            case "left" -> form = '>';

            case "up" -> form = 'v';
        }
    }
}