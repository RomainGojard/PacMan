import java.util.ArrayList;

public class Bonus extends Item {

    private long startActive;
    private final long bonusDuration = 8000;
    private final int malusValue = 30; //Pourcentage
    private final int bonusValue = 30; //Pourcentage

    public Bonus(PacMan pacMan, ArrayList<Ghost> arrayOfGhost) {
        super(pacMan, arrayOfGhost);
    }

    public void useBonus() {
        pacMan.activeBonus = this;
        pacMan.moveDelay -= bonusValue;
        for (Ghost ghost : arrayOfGhost
        ) {
            ghost.activeBonus = this;
            ghost.moveDelay += malusValue;
        }
        startActive = System.currentTimeMillis();
    }

    public boolean getIsEndOfBonus() {
        return System.currentTimeMillis() - startActive >= bonusDuration;
    }

    public int getBonusValue() {
        return bonusValue;
    }

    public int getMalusValue() {
        return malusValue;
    }
}
