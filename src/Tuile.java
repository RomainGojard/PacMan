import java.util.ArrayList;

public class Tuile {
    final String WALL_COLOR = Color.ANSI_RED;
    final String COIN_COLOR = Color.ANSI_BLUE;
    private char form;
    private String color;

    private boolean isTroughable;

    private PacMan pacMan;
    private ArrayList<Ghost> arrayOfGhost;
    private boolean isGhostHere;


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
            case '.', ' ' -> {
                color = COIN_COLOR;
                isTroughable = true;
                form = caractere;
            }
            case '<' -> {
                form = ' ';
                isTroughable = true;
                color = Color.ANSI_RESET;
            }
            case '⋐' -> {
                form = '.';
                color = COIN_COLOR;
                isTroughable = true;
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
            if (this.isScorable()) {
                form = ' ';
                pacMan.score();
            }
        } else if (monster instanceof Ghost) {
            arrayOfGhost.add((Ghost) monster);
            isGhostHere = true;
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

    public boolean isScorable() {
        return this.form == '.';
    }
}