package bouncy_box;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * DO NOT CHANGE THIS CLASS, THIS RUNS THE
 * BOUNCY BOX GAME, PROVIDING THE UI AND
 * TYING ALL THE COMPONENTS TOEGETHER
 * 
 * @author McKillaGorilla
 */
public class BouncyBoxesGame extends Application {

    // UI STUFF
    Stage window;
    Scene scene;
    BorderPane appPane = new BorderPane();
    HBox toolbar = new HBox();
    Button startGameButton = new Button("Start New Game");
    Button exitAppButton = new Button("Quit Application");
    Canvas canvas = new Canvas();

    // THE GAME STATE MANAGER
    BouncyBoxesGameStateManager gsm;
    BouncyBoxesRenderer renderer;
    BouncyBoxesController controller;
    
    // THIS WILL BE INITIALIZED WHEN THE GAME IS STARTED
    Timeline timeline;

    @Override
    public void start(Stage initWindow) throws Exception {
        gsm = new BouncyBoxesGameStateManager();
        initUI(initWindow);
        renderer = new BouncyBoxesRenderer(canvas, gsm);
    }

    private void initUI(Stage initWindow) {
        // LAYOUT THE GUI
        window = initWindow;
        appPane.setTop(toolbar);
        toolbar.setAlignment(Pos.CENTER);
        toolbar.getChildren().add(startGameButton);
        toolbar.getChildren().add(exitAppButton);
        appPane.setCenter(canvas);
        appPane.widthProperty().addListener(e->{
            canvas.setWidth(appPane.getWidth());            
        });
        appPane.heightProperty().addListener(e->{
            canvas.setHeight(appPane.getHeight() - toolbar.getHeight());
        });
        
        // SETUP BUTTON HANDLERS
        startGameButton.setOnAction(e->{
            BouncyBoxesGame.this.startNewGame();
        });
        exitAppButton.setOnAction(e->{
            BouncyBoxesGame.this.endPlaying();
            System.exit(0);
        });        

        // REGISTER THE KEY HANDLER
        controller = new BouncyBoxesController(gsm);
        registerKeyHandler(startGameButton);
        registerKeyHandler(exitAppButton);
        registerKeyHandler(canvas);
        
        // OPEN THE WINDOW
        Scene scene = new Scene(appPane);
        window.setScene(scene);
        window.setMaximized(true);
        window.show();
    }
    
    private void registerKeyHandler(Node node) {
        node.setFocusTraversable(true);
        node.setOnKeyPressed(e->{
            BouncyBoxesGame.this.controller.processKeyPressed(e.getCode());
        });
        node.setOnKeyReleased(e->{
            BouncyBoxesGame.this.controller.processKeyReleased(e.getCode());
        });
    }

    /**
     * This method is called when a game is to be started. It resents the
     * canvas' game state and then creates and starts a new thread for running
     * the timed game loop.
     */
    public void startNewGame() {
        // RESET THE GAME STATE
        System.out.println("canvas dimensions: (" + canvas.getWidth() + ", " + canvas.getHeight() + ")");
        gsm.resetGame(canvas.getWidth(), canvas.getHeight());

        // START THE TIMED GAME LOOP
        timeline = new Timeline(new KeyFrame(Duration.millis(33), e -> {
            if (gsm.isGameStarted()) {
                gsm.updateBouncyBoxes(canvas.getWidth(), canvas.getHeight());
            }
            renderer.renderScene();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * This method should be called when the game is over to stop the rendering
     * and kill the game loop thread.
     */
    public void endPlaying() {
        // STOP THE GAME
        gsm.endGame();

        // AND KILL THE GAME LOOP THREAD
        timeline.stop();
    }
    
    /**
     * This launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}