import java.util.ArrayList;
import java.util.Arrays;

public class Portal extends Item {
    private static Portal lastPortalToAssign = null;
    private Portal linkedPortal;
    private Terrain terrain;
    private int x;
    private int y;
    private int[] safePlace;

    public Portal(PacMan pacMan, ArrayList<Ghost> arrayOfGhost, Terrain terrain, int x, int y) {
        super(pacMan, arrayOfGhost);
        this.terrain = terrain;
        this.x = x;
        this.y = y;
        if (lastPortalToAssign != null) {
            this.linkedPortal = lastPortalToAssign;
            this.linkedPortal.linkedPortal = this;
            lastPortalToAssign = null;
        } else {
            lastPortalToAssign = this;
        }
        this.setSafePlaceOfPortal();
    }

    public void usePortal(Monster monster) {
        terrain.leaveTuile(x, y, monster);
        int[] safePlaceOfLinkedPortal = linkedPortal.safePlace;
        terrain.getToTuile(safePlaceOfLinkedPortal[0], safePlaceOfLinkedPortal[1], monster);
        monster.setCoordinates(safePlaceOfLinkedPortal);
    }

    public void setSafePlaceOfPortal() {
        int[] lenght = terrain.getLenght();
        int[] coord = new int[]{};
        if (x + 1 < lenght[0] - 1 && y < lenght[1] - 1 && x >= 0 && y >= 0 && terrain.getIsTroughable(x + 1, y)) {
            coord = new int[]{x + 1, y};
        } else if ((x - 1 < lenght[0] - 1 && y < lenght[1] - 1 && x >= 0 && y >= 0 && terrain.getIsTroughable(x - 1, y))) {
            coord = new int[]{x - 1, y};
        } else if ((x < lenght[0] - 1 && y + 1 < lenght[1] - 1 && x >= 0 && y >= 0 && terrain.getIsTroughable(x, y + 1))) {
            coord = new int[]{x, y + 1};
        } else {
            coord = new int[]{x, y - 1};
        }
        safePlace = coord;
        System.out.println(Arrays.toString(coord));
    }
}
