import java.util.ArrayList;

public class Tuile {
    final String WALL_COLOR = Color.ANSI_RED;
    final String COIN_COLOR = Color.ANSI_BLUE;
    private char form;
    private String color;

    private boolean isTroughable;

    private PacMan pacMan;
    private ArrayList<Ghost> arrayOfGhost;
    private boolean isPacManHere;
    private boolean isGhostHere;


    public String afficheTuile() {
        if (isGhostHere) {
            return (arrayOfGhost.get(0).afficherMonster());
        } else if (isPacManHere) {
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
                isPacManHere = false;
                form = caractere;
            }
            case '<' -> {
                this.isPacManHere = true;
                form = ' ';
                isTroughable = true;
                color = Color.ANSI_RESET;
            }
            case '⋐' -> {
                isPacManHere = false;
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

    public Tuile swicthTuilePacMan(Tuile nextTuile) {
        if (nextTuile.isScorable()) {
            nextTuile.form = ' ';
            nextTuile.pacMan = this.pacMan;
            nextTuile.isPacManHere = true;
            nextTuile.pacMan.score();
            return new Tuile(' ');
        } else {
            nextTuile.pacMan = this.pacMan;
            this.isPacManHere = false;
            nextTuile.isPacManHere = true;
            this.pacMan = null;
            return this;
        }
    }

    public void switchTuileGhost(Ghost ghost, Tuile netxTuile) {
        arrayOfGhost.remove(ghost);
        netxTuile.arrayOfGhost.add(ghost);
        netxTuile.isGhostHere = true;
        if (arrayOfGhost.isEmpty()) {
            isGhostHere = false;
        }
    }

    public void monsterGetOnTuile(Monster monster) {
        if (monster instanceof PacMan) {
            pacMan = (PacMan) monster;
            isPacManHere = true;
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
            isPacManHere = false;
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

    public void setGhost(Ghost ghost) {
        isGhostHere = true;
        arrayOfGhost.add(ghost);
    }

    public boolean getIsPacManHere() {
        return isPacManHere;
    }

    public boolean isScorable() {
        return this.form == '.';
    }
}