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
        Ghost ghost = ghostToFree();
        if (pacMan.getScore() % 30 == 0 && ghost != null){
            ghost.free();
        }
    }

    public Ghost ghostToFree(){
        int i = 0;
        Ghost ghost = null;
        while (i < arrayOfGhost.size() && ghost == null){
            if (!arrayOfGhost.get(i).getFree()){
                ghost = arrayOfGhost.get(i);
            }
            i++;
        }
        return ghost;
    }
}
