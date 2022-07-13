import java.util.ArrayList;

public class Terrain {

    private int maxScore = 0;

    private PacMan pacMan;

    private boolean change;

    private final static String clearScreen = "\033[H\033[2J";

    private Tuile[][] terrainDeTuiles;

    private ArrayList<Ghost> arrayOfGhost;

    public Terrain() {
        String[] terrain =
                {
                        "|------------||------------|",
                        "|............||............|",
                        "|.----.-----.||.----.-----.|",
                        "|o|  |.|   |.||.|  |.|   |o|",
                        "|.----.-----.||.----.-----.|",
                        "|..........................|",
                        "|.----.--.--------.--.----.|",
                        "|.----.||.---||---.||.----.|",
                        "|......||....||....||......|",
                        "|-----.||--| || |--||.-----|",
                        "     |.||--| -- |--||.|     ",
                        "     |.||     ⋐    ||.|     ",
                        "     |.|| -------- ||.|     ",
                        "-----|.|| |   ⋐  | ||.|-----",
                        "҉     .   |   ⋐  |   .     ҉",
                        "-----|.|| |   ⋐  | ||.|-----",
                        "     |.|| -------- ||.|     ",
                        "     |.||          ||.|     ",
                        "     |.|| -------- ||.|     ",
                        "-----|.|| -------- ||.|-----",
                        "|............||............|",
                        "|.----.-----.||.-----.----.|",
                        "|.----.-----.||.-----.----.|",
                        "|o..||.......<........||..o|",
                        "|--.||.||.--------.||.||.--|",
                        "|--.||.||.--------.||.||.--|",
                        "|......||....||....||......|",
                        "|.----------.||.----------.|",
                        "|.----------.||.----------.|",
                        "|..........................|",
                        "|--------------------------|"
                };

        arrayOfGhost = new ArrayList<>();
        terrainDeTuiles = new Tuile[terrain.length][terrain[0].length()];
        int i = 0;
        for (String row : terrain) {
            for (int j = 0; j < row.length(); j++) {
                char form = row.charAt(j);
                terrainDeTuiles[i][j] = new Tuile(form);
                if (form == '<') {
                    pacMan = new PacMan(i, j, this);
                } else if (form == '⋐') {
                    arrayOfGhost.add(new Ghost(i, j, this));
                }
            }
            i++;
        }

        i = 0;
        int j = 0;
        for (Tuile[] row : terrainDeTuiles) {
            for (Tuile tuile : row
            ) {
                if (tuile.isItemHere()) {
                    createItem(tuile, i, j);
                    if (tuile.getScorable()) {
                        maxScore++;
                    }
                }
                j++;
            }
            j=0;
            i++;
        }

        change = true;
    }

    public void afficheTerrain() {
        if (change) {
            change = false;

            StringBuilder output = new StringBuilder(clearScreen);
            output.append(pacMan.afficheScore()).append('\n');
            for (Tuile[] row : terrainDeTuiles) {
                for (Tuile tuile : row) {
                    output.append(tuile.afficheTuile());
                }
                output.append("\n");
            }
            System.out.println(output);
        }
    }

    public void getToTuile(int i, int j, Monster monster) {
        terrainDeTuiles[i][j].monsterGetOnTuile(monster);
        change = true;
    }

    public void leaveTuile(int i, int j, Monster monster) {
        terrainDeTuiles[i][j].monsterLeaveTuile(monster);
    }

    public void removeGhost(Ghost ghost) {
        int[] coordinates = ghost.getCoordinates();
        terrainDeTuiles[coordinates[0]][coordinates[1]].monsterLeaveTuile(ghost);
    }

    public void createItem(Tuile tuile, int i, int j) {
        switch (tuile.getForm()) {
            case ('.') -> tuile.setItem(new Coin(pacMan, arrayOfGhost));
            case ('o') -> tuile.setItem(new Bonus(pacMan, arrayOfGhost));
            case ('҉') -> tuile.setItem(new Portal(pacMan, arrayOfGhost, this, i, j));
        }
    }

    public boolean getIsTroughable(int x, int y) {
        return terrainDeTuiles[x][y].getIsTroughable();
    }


    public int getMaxScore() {
        return this.maxScore;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public ArrayList<Ghost> getArrayOfGhost() {
        return arrayOfGhost;
    }

    public int[] getLenght(){
        return new int[]{terrainDeTuiles.length, terrainDeTuiles[0].length};
    }
}