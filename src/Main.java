import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void clearConsoleScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public static void main(String[] args) throws InterruptedException {

        Terrain terrain = new Terrain();
        PacMan pacMan = terrain.searchStartPacMan();
        ArrayList<Ghosts> arrayOfGhosts = new ArrayList<>();
        arrayOfGhosts.add(new Ghosts(1, 3));
        arrayOfGhosts.add(new Ghosts(5, 25));
        arrayOfGhosts.add(new Ghosts(8, 2));
        terrain.setStartGhosts(arrayOfGhosts);

        InputControllers inputControllers = new InputControllers();

        final long REFRESH_TIME = 500;
        boolean play = true;
        while (play) {
            clearConsoleScreen();

            if (inputControllers.touchePressee) {
                switch (inputControllers.touche) {
                    case (37) -> pacMan.deplacerMonster(terrain, "left");
                    case (38) -> pacMan.deplacerMonster(terrain, "up");
                    case (39) -> pacMan.deplacerMonster(terrain, "right");
                    case (40) -> pacMan.deplacerMonster(terrain, "down");
                }
            }

            play = !pacMan.gameOver(arrayOfGhosts);

            if (play) {

                for (Ghosts ghost : arrayOfGhosts
                ) {
                    String direction = ghost.whereToGo(terrain, pacMan);
                    ghost.deplacerMonster(terrain, direction);
                }

                play = !pacMan.gameOver(arrayOfGhosts);
            }

            terrain.afficheTerrain();

            Thread.sleep(REFRESH_TIME);
        }
    }
}

