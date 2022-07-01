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
            if (inputControllers.touchePressee) {
                switch (inputControllers.touche) {
                    case (37) -> pacMan.deplacerMonster(terrain, "left");
                    case (38) -> pacMan.deplacerMonster(terrain, "up");
                    case (39) -> pacMan.deplacerMonster(terrain, "right");
                    case (40) -> pacMan.deplacerMonster(terrain, "down");
                }
            }
            if (pacMan.isGameOver(arrayOfGhosts)) {
                terrain.afficheTerrain();
                play = false;
                System.out.println(pacMan.gameOver());
                System.exit(0);
            }
            for (Ghost ghost : arrayOfGhosts
            ) {
                String direction = ghost.whereToGo(terrain, pacMan);
                ghost.deplacerMonster(terrain, direction);
            }

            terrain.afficheTerrain();

            if (pacMan.isGameOver(arrayOfGhosts)) {
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