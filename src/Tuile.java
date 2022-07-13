import java.util.ArrayList;

public class Tuile {
    final String WALL_COLOR = Color.ANSI_RED;
    final String COIN_COLOR = Color.ANSI_BLUE;

    final String BONUS_COLOR = Color.ANSI_YELLOW;
    private char form;
    private String color;

    private boolean isTroughable;

    private PacMan pacMan;
    private ArrayList<Ghost> arrayOfGhost;
    private boolean isGhostHere;
    private Item item;
    private boolean isItemHere = false;


    public String afficheTuile() {
        if (isGhostHere) {
            return (arrayOfGhost.get(0).afficherMonster());
        } else if (pacMan != null) {
            return pacMan.afficherMonster();
        } else {
            return (color + form);
        }
    }

    public Tuile(char caractere) {
        arrayOfGhost = new ArrayList<>();
        switch (caractere) {
            case ' ' -> {
                color = COIN_COLOR;
                isTroughable = true;
                form = caractere;
            }
            case '.' -> {
                color = COIN_COLOR;
                isTroughable = true;
                form = caractere;
                isItemHere = true;
            }
            case 'o' -> {
                color = BONUS_COLOR;
                isTroughable = true;
                form = caractere;
                isItemHere = true;
            }
            case '<' -> {
                form = ' ';
                isTroughable = true;
                color = Color.ANSI_RESET;
            }
            case '⋐' -> {
                form = ' ';
                color = COIN_COLOR;
                isTroughable = true;
            }
            case '҉' -> {
                form = caractere;
                color = Color.ANSI_GREEN;
                isTroughable = true;
                isItemHere = true;
            }
            default -> {
                color = WALL_COLOR;
                isTroughable = false;
                form = caractere;
            }
        }
    }

    public void monsterGetOnTuile(Monster monster) {
        if (monster instanceof PacMan) {
            pacMan = (PacMan) monster;
            if (isItemHere) {
                useItem(monster);
            }
        } else if (monster instanceof Ghost) {
            arrayOfGhost.add((Ghost) monster);
            isGhostHere = true;
            if (isItemHere && item instanceof Portal) {
                useItem(monster);
            }
        }
    }

    public void monsterLeaveTuile(Monster monster) {
        if (monster instanceof PacMan) {
            pacMan = null;
        } else if (monster instanceof Ghost) {
            arrayOfGhost.remove((Ghost) monster);
            if (arrayOfGhost.isEmpty()) {
                isGhostHere = false;
            }
        }
    }

    public boolean getIsTroughable() {
        return this.isTroughable;
    }

    public boolean isItemHere() {
        return isItemHere;
    }

    public void useItem(Monster monster) {
        if (item instanceof Coin) {
            ((Coin) item).score();
        } else if (item instanceof Bonus && pacMan.activeBonus == null) {
            ((Bonus) item).useBonus();
        } else if (item instanceof Portal) {
            ((Portal) item).usePortal(monster);
            return;
        } else {
            return;
        }
        item = null;
        form = ' ';
        color = Color.ANSI_RESET;
        isItemHere = false;
    }

    public boolean getScorable() {
        return item instanceof Coin;
    }

    public char getForm() {
        return form;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}