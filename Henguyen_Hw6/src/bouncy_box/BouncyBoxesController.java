package bouncy_box;

import javafx.scene.input.KeyCode;

/**
 * This class handles user input from the keyboard so that the gsm.getPlayer() may move
 * the green box around the screen.
 */
public class BouncyBoxesController {

    BouncyBoxesGameStateManager gsm;

    public BouncyBoxesController(BouncyBoxesGameStateManager initGsm) {
        gsm = initGsm;
    }

    /**
     * We'll start moving the gsm.getPlayer() upon the first press of a WASD or
     * directional key.
     */
    public void processKeyPressed(KeyCode keyCode) {
        if (gsm.isGameStarted()) {
            if ((keyCode == KeyCode.W) || (keyCode == KeyCode.UP)) {
                gsm.getPlayer().moveUp();
            }
            if ((keyCode == KeyCode.S) || (keyCode == KeyCode.DOWN)) {
                gsm.getPlayer().moveDown();
            }
            if ((keyCode == KeyCode.A) || (keyCode == KeyCode.LEFT)) {
                gsm.getPlayer().moveLeft();
            }
            if ((keyCode == KeyCode.D) || (keyCode == KeyCode.RIGHT)) {
                gsm.getPlayer().moveRight();
            }
        }
    }

    /**
     * Upon the release of a WASD or directional key we'll stop moving the
     * gsm.getPlayer()'s box.
     */
    public void processKeyReleased(KeyCode keyCode) {
        if (gsm.isGameStarted()) {
            if ((keyCode == KeyCode.W) || (keyCode == KeyCode.UP)) {
                gsm.getPlayer().stopInY();
            }
            if ((keyCode == KeyCode.S) || (keyCode == KeyCode.DOWN)) {
                gsm.getPlayer().stopInY();
            }
            if ((keyCode == KeyCode.A) || (keyCode == KeyCode.LEFT)) {
                gsm.getPlayer().stopInX();
            }
            if ((keyCode == KeyCode.D) || (keyCode == KeyCode.RIGHT)) {
                gsm.getPlayer().stopInX();
            }
        }
    }

    // WE WILL NOT USE THIS METHOD
    public void keyTyped(KeyCode ke) {

    }
}