import java.util.ArrayList;

public class Game {
    private Terrain terrain;
    private PacMan pacMan;
    private ArrayList<Ghost> arrayOfGhost;
    private int maxScore;

    public Game() {
        this.terrain = new Terrain();
        pacMan = this.terrain.getPacMan();
        arrayOfGhost = this.terrain.getArrayOfGhost();
        maxScore = terrain.getMaxScore();
    }

    public void loopGame() {
        pacMan.checkMove();
        checkPacManAndGhostCollision();
        ghostsMove();
        terrain.afficheTerrain();
        checkPacManAndGhostCollision();
        checWin();
    }

    public void checkPacManAndGhostCollision() {
        ArrayList<Ghost> arrayOfGhostToRemove = new ArrayList<>();
        for (Ghost ghost : arrayOfGhost) {
            if (ghost.getCoordinates()[0] == pacMan.getCoordinates()[0] && ghost.getCoordinates()[1] == pacMan.getCoordinates()[1]) {
                if (pacMan.activeBonus != null) {
                    arrayOfGhostToRemove.add(ghost);
                    terrain.removeGhost(ghost.getCoordinates());
                } else {
                    terrain.afficheTerrain();
                    System.out.println(pacMan.gameOver());
                    System.exit(0);
                }
            }
        }
        for (Ghost ghost : arrayOfGhostToRemove
        ) {
            arrayOfGhost.remove(ghost);
        }
    }

    public void ghostsMove() {
        for (Ghost ghost : arrayOfGhost
        ) {
            if (ghost.getFree()) {
                ghost.checkMove();
            }
        }
    }

    public void checWin() {
        if (this.pacMan.getScore() >= terrain.getMaxScore()) {
            System.out.println(pacMan.win());
            System.exit(0);
        }
    }
}
