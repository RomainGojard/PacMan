import java.util.ArrayList;

public class Game {
    private Terrain terrain;
    private PacMan pacMan;
    private ArrayList<Ghost> arrayOfGhost;
    private int score;
    private int maxScore;


    public Game() {
        this.terrain = new Terrain();
        pacMan = this.terrain.getPacMan();
        arrayOfGhost = this.terrain.getArrayOfGhost();
        score = 0;
        maxScore = terrain.getMaxScore();
    }

    public void loopGame() {
        pacMan.checkMove();

        if (pacMan.isGameOver()) {
            terrain.afficheTerrain();
            System.out.println(pacMan.gameOver());
            System.exit(0);
        }

        for (Ghost ghost : arrayOfGhost
        ) {
            if (ghost.getFree()) {
                ghost.checkMove();
            }
        }

        terrain.afficheTerrain();

        if (pacMan.isGameOver()) {
            System.out.println(pacMan.gameOver());
            System.exit(0);
        } else {
            pacMan.win();
        }
    }


}
