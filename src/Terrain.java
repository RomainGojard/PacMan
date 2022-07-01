import java.util.ArrayList;

public class Terrain {

    private int maxScore = 0;

    private PacMan pacMan;

    private Tuile[][] terrainDeTuiles;

    private ArrayList<Ghost> arrayOfGhost;

    public Terrain() {
        String[] terrain = {
                "|------------||------------|",
                "|...⋐........||.........⋐..|",
                "|.----.-----.||.----.-----.|",
                "|.|  |.|   |.||.|  |.|   |.|",
                "|.----.-----.||.----.-----.|",
                "|...<......................|",
                "|.----.--.--------.--.----.|",
                "|.----.||.---||---.||.----.|",
                "|......||....  ....||..⋐...|",
                "|------||--|-||-|--||------|"
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

                if (terrainDeTuiles[i][j].isScorable()) {
                    maxScore++;
                }
            }
            i++;
        }
    }

    public void afficheTerrain() {
        String output = "";
        for (Tuile[] row : terrainDeTuiles) {
            for (Tuile tuile : row) {
                output = output.concat(tuile.afficheTuile());
            }
            output = output.concat("\n");
        }
        System.out.println(output);
    }

    public void setStartGhosts(ArrayList<Ghost> arrayOfGhosts) {
        for (Ghost ghost : arrayOfGhosts
        ) {
            int[] tab = ghost.getCoordinates();
            terrainDeTuiles[tab[0]][tab[1]].setGhost(ghost);
        }
    }

    public void getToTuile(int i, int j, Monster monster) {
        terrainDeTuiles[i][j].monsterGetOnTuile(monster);
    }

    public void leaveTuile(int i, int j, Monster monster) {
        terrainDeTuiles[i][j].monsterLeaveTuile(monster);
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
}