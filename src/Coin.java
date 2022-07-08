import java.util.ArrayList;

public class Coin extends Item {

    public Coin(PacMan pacMan, ArrayList<Ghost> arrayOfGhost) {
        super(pacMan, arrayOfGhost);
    }

    public void score() {
        pacMan.score();
        checkCoins();
    }

    public void checkCoins() {
        switch (pacMan.getScore()) {
            case (20) -> arrayOfGhost.get(1).free();
            case (40) -> arrayOfGhost.get(2).free();
            case (60) -> arrayOfGhost.get(3).free();
        }
    }
}
