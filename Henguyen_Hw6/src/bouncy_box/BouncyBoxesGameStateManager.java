package bouncy_box;
/**
 * DO NOT CHANGE THIS CLASS, THIS MANAGES
 * THE GAME STATE FOR THE BOUNCY BOX GAME
 * 
 * @author McKillaGorilla
 */
public class BouncyBoxesGameStateManager {

    // THERE ARE 30 BOXES IN ALL
    public static final int NUM_BOUNCY_BOXES = 30;

    // THERE ARE 20 GROWING BOXES
    public static final int NUM_GROWING_BOXES = 20;

    // THERE ARE 10 SHRINKING BOXES
    public static final int NUM_SHRINKING_BOXES = 10;
    
    // THIS IS HOW MUCH WE'LL REDUCE OPACITY WITH EACH COLLISION
    public static final double OPACITY_INC = 0.1;

    // HERE IS THE PLAYER BOX
    private PlayerBouncyBox player;

    // AND HERE ARE THE GROWING AND SHRINKING BOXES
    private BouncyBox[] bouncyBoxes;

    // WE ONLY RENDER THE BOXIS IF gameStarted IS TRUE
    private boolean gameStarted;
    private boolean gameWon;
    private boolean gameLost;

    public BouncyBoxesGameStateManager() {
        // THE GAME HAS NOT STARTED YET
        gameStarted = false;
        gameWon = false;
        gameLost = false;
    }

    // ACCESSOR METHOD
    public boolean isGameStarted() {
        return gameStarted;
    }
    
    public boolean isGameWon() {
        return gameWon;
    }
    
    public boolean isGameLost() {
        return gameLost;
    }

    // MUTATOR METHOD
    public void endGame() {
        gameStarted = false;
    }
    
    public PlayerBouncyBox getPlayer() {
        return player;
    }
    
    public BouncyBox getBouncyBox(int index) {
        return bouncyBoxes[index];
    }
    
    public int getNumBouncyBoxes() {
        return bouncyBoxes.length;
    }

    /**
     * This method resets the game, initializing all the game's bouncy boxes.
     */
    public void resetGame(double worldWidth, double worldHeight) {
        // CONSTRUCT ALL OF THE BOXES
        int xInc = (int) (worldWidth / 10);
        int yInc = (int) (worldHeight / 10);
        int length = 30;
        int v = 5;
        player = new PlayerBouncyBox((int) (worldWidth / 2), (int) (worldHeight / 2), 0, 0, length);
        bouncyBoxes = new BouncyBox[NUM_BOUNCY_BOXES];
        int i;
        for (i = 0; i < 5; i++) {
            bouncyBoxes[i] = new GrowingBouncyBox(((i % 5) * xInc) + (2 * xInc), yInc, v, v, length);
        }
        for (i = 5; i < 10; i++) {
            bouncyBoxes[i] = new ShrinkingBouncyBox(xInc, ((i % 5) * yInc) + yInc, v, v, length);
        }
        for (i = 10; i < 15; i++) {
            bouncyBoxes[i] = new GrowingBouncyBox(worldWidth - ((i % 5) * xInc) - (2 * xInc), worldHeight - yInc, -v, -v, length);
        }
        for (i = 15; i < 20; i++) {
            bouncyBoxes[i] = new ShrinkingBouncyBox(worldWidth - xInc, worldHeight - ((i % 5) * yInc) - yInc, -v, -v, length);
        }
        for (i = 20; i < 25; i++) {
            bouncyBoxes[i] = new GrowingBouncyBox(i * 40, 2, v, v, length);
        }
        for (i = 25; i < 30; i++) {
            bouncyBoxes[i] = new GrowingBouncyBox(worldWidth - (i * 100), 2, -v, -v, length);
        }

        // NOW RANDOMIZE THE INITIAL DIRECTION
        for (i = 0; i < this.NUM_BOUNCY_BOXES; i++) {
            BouncyBox box = bouncyBoxes[i];
            int randNum = (int) (Math.random() * 4);
            if (randNum == 0) {
                box.setXVelocity(v);
                box.setYVelocity(v);
            } else if (randNum == 1) {
                box.setXVelocity(v);
                box.setYVelocity(-v);
            } else if (randNum == 2) {
                box.setXVelocity(-v);
                box.setYVelocity(v);
            } else {
                box.setXVelocity(-v);
                box.setYVelocity(-v);
            }
        }

        // MAKE SURE THEY GET RENDERED
        gameStarted = true;
        gameWon = false;
        gameLost = false;
    }

    /**
     * This method counts and returns the number of shrinking boxes that have
     * disappeared.
     */
    public int countShrunkBouncyBoxes() {
        int count = 0;
        for (BouncyBox box : bouncyBoxes) {
            if (box.getLength() <= 0)
                count++;
        }
        return count;
    }

    public boolean playerLost() {
        return player.getOpacity() == 0;
    }

    public boolean playerWon() {
        System.out.println("Shrunk Bouncy Boxes: " + this.countShrunkBouncyBoxes());
        return this.countShrunkBouncyBoxes() == NUM_SHRINKING_BOXES;
    }

    /**
     * Called each frame, this method makes sure all the boxes are updated each
     * frame and then checks to see if the game is over, displaying a You Win or
     * You Lose in such cases.
     */
    public void updateBouncyBoxes(double worldWidth, double worldHeight) {
        if (gameStarted) {
            CollisionManager.performCollisionTests(player, bouncyBoxes, (int) worldWidth, (int) worldHeight);
            if (playerWon()) {
                gameWon = true;
            }
            else if (playerLost()) {
                gameLost = true;
            }
            if (playerLost() || playerWon()) {
                gameStarted = false;
            }
        }
    }
}