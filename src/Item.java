import java.util.ArrayList;

public class Item {
    protected PacMan pacMan;
    protected ArrayList<Ghost> arrayOfGhost;

    public Item(PacMan pacMan, ArrayList<Ghost> arrayOfGhost){
        this.pacMan = pacMan;
        this.arrayOfGhost = arrayOfGhost;
    }
}
