import java.util.ArrayList;

public class Bonus extends Item {

    private long startActive;
    private final long BONUS_DURATION = 8000;
    private final int MALUS_VALUE = 75;
    private final int BONUS_VALUE = 75;
    private final String COLOR_BONUS = Color.ANSI_CYAN;

    public Bonus(PacMan pacMan, ArrayList<Ghost> arrayOfGhost) {
        super(pacMan, arrayOfGhost);
    }

    public void useBonus() {
        pacMan.activeBonus = this;
        pacMan.moveDelay -= BONUS_VALUE;
        pacMan.color = COLOR_BONUS;
        for (Ghost ghost : arrayOfGhost
        ) {
            ghost.activeBonus = this;
            ghost.moveDelay += MALUS_VALUE;
            ghost.setNewStrategy(new Strategy("flee"));
        }
        startActive = System.currentTimeMillis();
    }

    public boolean getIsEndOfBonus() {
        return System.currentTimeMillis() - startActive >= BONUS_DURATION;
    }

    public int getBonusValue() {
        return BONUS_VALUE;
    }

    public int getMalusValue() {
        return MALUS_VALUE;
    }
}
