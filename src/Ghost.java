public class Ghost extends Monster {

    private static int counter = 0;
    private Strategy defaultStrategy;

    private Strategy strategy;

    private boolean free;

    public Ghost(int i, int j, Terrain terrain) {
        super(i, j, terrain);
        switch (counter) {
            case 0 -> {
                color = Color.ANSI_RED;
                defaultStrategy = new Strategy("follow");
                strategy = defaultStrategy;
                free = true;
            }
            case 1 -> {
                color = Color.ANSI_PURPLE;
                defaultStrategy = new Strategy("random");
                strategy = defaultStrategy;
                free = false;
            }
            case 2 -> {
                color = Color.ANSI_GREEN;
                defaultStrategy = new Strategy("follow");
                strategy = defaultStrategy;
                free = false;
            }
            case 3 -> {
                color = Color.ANSI_CYAN;
                defaultStrategy = new Strategy("random");
                strategy = defaultStrategy;
                free = false;
            }
        }
        forms = new char[]{'⋐', '⋑', '⋒', '⋓'};
        form = forms[0];
        counter++;
    }

    public String whereToGo() {
        String direction;
        switch (strategy.name()) {
            case ("follow") -> direction = strategy.follow(terrain, this);
            case ("random") -> direction = strategy.random(terrain, this);
            case ("flee") -> direction = strategy.flee(terrain, this);
            default -> direction = null;
        }
        return direction;
    }

    public void free() {
        if (!free) {
            terrain.leaveTuile(x, y, this);
            terrain.getToTuile(x, y + 7, this);
            y = y + 7;
            free = true;
            lastMove = now;
        }
    }

    public void setNewStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public void setDefaultStrategy(){
        this.strategy = defaultStrategy;
    }

    public boolean getFree() {
        return free;
    }

}
