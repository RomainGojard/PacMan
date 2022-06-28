import java.util.ArrayList;

public class Tuile {
    final String WALL_COLOR = Colors.ANSI_RED;
    final String COIN_COLOR = Colors.ANSI_BLUE;
    private final char form;
    private String color;

    private boolean isTroughable;

    private PacMan pacMan;
    private ArrayList<Ghosts> arrayOfGhost;
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

    public Tuile(char caractere, int i, int j) {
        arrayOfGhost = new ArrayList<>();
        switch (caractere) {
            case '.', ' ' -> {
                color = COIN_COLOR;
                isTroughable = true;
            }
            case '<' -> {
                this.isPacManHere = true;
                pacMan = new PacMan(i, j);
            }
            default -> {
                color = WALL_COLOR;
                isTroughable = false;
            }
        }
        form = caractere;

    }

    public Tuile swicthTuilePacMan(Tuile nextTuile) {
        isPacManHere = false;
        nextTuile.isPacManHere = true;
        nextTuile.pacMan = this.pacMan;
        if (nextTuile.form == '.') {
            nextTuile.pacMan.score();
            return new Tuile(' ', 0, 0);
        }
        return this;
    }

    public void switchTuileGhost(Ghosts ghost, Tuile netxTuile) {
        arrayOfGhost.remove(ghost);
        netxTuile.arrayOfGhost.add(ghost);
        netxTuile.isGhostHere = true;
        if (arrayOfGhost.isEmpty()) {
            isGhostHere = false;
        }
    }

    public boolean getIsTroughable() {
        return this.isTroughable;
    }

    public void setGhost(Ghosts ghost) {
        isGhostHere = true;
        arrayOfGhost.add(ghost);
    }

    public PacMan getPacMan() {
        return this.pacMan;
    }

    public boolean getIsPacManHere() {
        return isPacManHere;
    }

    public boolean isScorable() {
        return this.form == '.';
    }
}