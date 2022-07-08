import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        InputControllers inputControllers = new InputControllers();
        Game game = new Game();
        final long REFRESH_TIME = 5L;

        while (true) {
            game.loopGame();
            Thread.sleep(REFRESH_TIME);
        }
    }
}