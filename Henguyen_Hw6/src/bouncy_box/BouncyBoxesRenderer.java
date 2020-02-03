package bouncy_box;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * DO NOT CHANGE THIS CLASS, THIS MANAGES
 * THE GAME STATE FOR THE BOUNCY BOX GAME
 * 
 * @author McKillaGorilla
 */
public class BouncyBoxesRenderer {

    Canvas canvas;
    GraphicsContext gc;
    BouncyBoxesGameStateManager gsm;
    Font feedbackFont;
    Text feedbackText;

    /**
     * This default constructor sets up this canvas.
     */
    public BouncyBoxesRenderer(Canvas initCanvas, BouncyBoxesGameStateManager initGsm) {
        canvas = initCanvas;
        gc = canvas.getGraphicsContext2D();
        gsm = initGsm;
        feedbackFont = new Font("Console", 172);
        gc.setFont(feedbackFont);
        feedbackText = new Text("");
        feedbackText.setFont(feedbackFont);
    }

    /**
     * This method renders all of the bouncy boxes.
     */
    public void renderScene() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < gsm.getNumBouncyBoxes(); i++) {
            BouncyBox box = gsm.getBouncyBox(i);
            renderBouncyBox(box);
        }
        renderBouncyBox(gsm.getPlayer());

        if (gsm.playerWon()) {
            feedbackText.setText("YOU WIN!!!");
            double textWidth = feedbackText.getBoundsInLocal().getWidth();
            double textHeight = feedbackText.getBoundsInLocal().getHeight();
            gc.strokeText("You WIN!!!", (canvas.getWidth() / 2) - (textWidth / 2), (canvas.getHeight() / 2) - (textHeight / 2));
        } else if (gsm.playerLost()) {
            feedbackText.setText("YOU LOST!!!");
            double textWidth = feedbackText.getBoundsInLocal().getWidth();
            double textHeight = feedbackText.getBoundsInLocal().getHeight();
            gc.strokeText("You LOSE!!!", (canvas.getWidth() / 2) - (textWidth / 2), (canvas.getHeight() / 2) - (textHeight / 2));
        }
    }

    /**
     * This method renders the BouncyBox argument inside this canvas via the
     * Graphics argument. It will do so using a black outline and the color of
     * the box.
     */
    public void renderBouncyBox(BouncyBox box) {
        GraphicsContext ctx = canvas.getGraphicsContext2D();
        ctx.setFill(box.getColor());
        ctx.fillRect((int) Math.round(box.getX()), (int) Math.round(box.getY()), (int) Math.round(box.getLength()), (int) Math.round(box.getLength()));
        ctx.setStroke(Color.BLACK);
        ctx.strokeRect((int) Math.round(box.getX()), (int) Math.round(box.getY()), (int) Math.round(box.getLength()), (int) Math.round(box.getLength()));
    }
}