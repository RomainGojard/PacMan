import java.util.ArrayList;

public class Main {

    public static void clearConsoleScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public static void main(String[] args) throws InterruptedException {

        Terrain terrain = new Terrain();
        PacMan pacMan = terrain.getPacMan();
        ArrayList<Ghost> arrayOfGhosts = new ArrayList<>();
        arrayOfGhosts.add(new Ghost(1, 3));
        arrayOfGhosts.add(new Ghost(5, 25));
        arrayOfGhosts.add(new Ghost(8, 2));
        terrain.setStartGhosts(arrayOfGhosts);
        InputControllers inputControllers = new InputControllers();

        final long REFRESH_TIME = 250;
        boolean play = true;
        int maxScore = terrain.getMaxScore();
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

            if (pacMan.isGameOver(arrayOfGhosts)) {
                terrain.afficheTerrain();
                play = false;
                pacMan.gameOver(maxScore);
            }


            for (Ghost ghost : arrayOfGhosts
            ) {
                String direction = ghost.whereToGo(terrain, pacMan);
                ghost.deplacerMonster(terrain, direction);
            }

            terrain.afficheTerrain();

            if (pacMan.isGameOver(arrayOfGhosts)) {
                play = false;
                pacMan.gameOver(maxScore);
            } else if (pacMan.win(maxScore)) {
                play = false;
            } else {
                pacMan.afficheScore(maxScore);
                Thread.sleep(REFRESH_TIME);
            }
        }
    }
}