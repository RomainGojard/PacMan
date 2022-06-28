import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class InputControllers extends JFrame implements KeyListener {
    private static InputControllers inputControllers;

    boolean touchePressee;

    int touche;

    public InputControllers() {
        if (inputControllers != null) {
            System.err.println("Attention, création d'un second gestionnaire de touches !");
            System.exit(1);
        }
// On enregistre notre fenêtre Frame comme  'écouteur" d'évènements de touches, et on l'affiche
// L'interface KeyListener nous oblige à définir keyPressed, keyReleased et keyTyped
        addKeyListener(this);
        setVisible(true);
    }

    public static InputControllers getInputControllers() {
        if (inputControllers == null) {
            inputControllers = new InputControllers();
        }
        return inputControllers;
    }

    public void keyPressed(KeyEvent ke) {
        touchePressee = true;
        touche = ke.getKeyCode();
    }

    public void keyReleased(KeyEvent ke) {

        if (touche == ke.getKeyCode()) {
            touchePressee = false;
        }
    }

    public void keyTyped(KeyEvent ke) {
    }

    public Integer getTouche() {
        if (!touchePressee)
            return null;
        return touche;
    }

}
