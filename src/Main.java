import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Terrain terrain = new Terrain();
        PacMan pacMan = terrain.getPacMan();
        ArrayList<Ghost> arrayOfGhosts = terrain.getArrayOfGhost();
        InputControllers inputControllers = new InputControllers();

        final long REFRESH_TIME = 5L;
        boolean play = true;
        while (play) {

            pacMan.checkMove();

            if (pacMan.isGameOver()) {
                terrain.afficheTerrain();
                play = false;
                System.out.println(pacMan.gameOver());
                System.exit(0);
            }

            for (Ghost ghost : arrayOfGhosts
            ) {
                ghost.checkMove();
            }

            terrain.afficheTerrain();

            if (pacMan.isGameOver()) {
                play = false;
                System.out.println(pacMan.gameOver());
                System.exit(0);
            } else if (pacMan.win()) {
                play = false;
            } else {
                Thread.sleep(REFRESH_TIME);
            }
        }
    }
}