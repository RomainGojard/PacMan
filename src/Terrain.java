import java.util.ArrayList;

public class Terrain {

    private final int xLenght;

    private final int yLenght;
    private Tuile[][] terrainDeTuiles;

    public Terrain() {
        String[] terrain = {
                "|------------||------------|",
                "|............||............|",
                "|.----.-----.||.----.-----.|",
                "|.|  |.|   |.||.|  |.|   |.|",
                "|.----.-----.||.----.-----.|",
                "|...<......................|",
                "|.----.--.--------.--.----.|",
                "|.----.||.---||---.||.----.|",
                "|......||....||....||......|",
                "|------||--|-||-|--||------|"
        };

        terrainDeTuiles = new Tuile[terrain.length][terrain[0].length()];
        int i = 0;
        for (String row : terrain) {
            for (int j = 0; j < row.length(); j++) {
                Tuile tuile = new Tuile(row.charAt(j), i, j);
                terrainDeTuiles[i][j] = tuile;
            }
            i++;
        }
        xLenght = terrainDeTuiles.length;
        yLenght = terrainDeTuiles[0].length;
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

    public PacMan searchStartPacMan() {
        int coordI = 0;
        int coordJ = 0;
        for (int i = 0; i < terrainDeTuiles.length; i++) {
            for (int j = 0; j < terrainDeTuiles[0].length; j++) {
                if (terrainDeTuiles[i][j].getIsPacManHere()) {
                    coordI = i;
                    coordJ = j;
                }
            }
        }
        return terrainDeTuiles[coordI][coordJ].getPacMan();
    }

    public void setStartGhosts(ArrayList<Ghosts> arrayOfGhosts) {
        for (Ghosts ghost : arrayOfGhosts
        ) {
            int[] tab = ghost.getCoordinates();
            terrainDeTuiles[tab[0]][tab[1]].setGhost(ghost);
        }
    }

    public void switchTuilePacMan(int oldX, int oldY, int newX, int newY) {
        terrainDeTuiles[oldX][oldY] = terrainDeTuiles[oldX][oldY].swicthTuilePacMan(terrainDeTuiles[newX][newY]);
    }

    public void switchTuileGhost(Ghosts ghost, int oldX, int oldY, int newX, int newY) {
        terrainDeTuiles[oldX][oldY].switchTuileGhost(ghost, terrainDeTuiles[newX][newY]);
    }

    public boolean getIsTroughable(int x, int y) {
        return terrainDeTuiles[x][y].getIsTroughable();
    }

    public int[] getLenght(){
        return new int[] {this.xLenght, this.yLenght};
    }
}