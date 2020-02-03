/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MickeyMantle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * Author Henry Nguyen SBU ID: 111484010 Net ID: Henguyen CSE 114
 */
public class GamePlay extends Application {

    private static CardsandHand game = new CardsandHand();
    private static HBox player1;
    private static VBox player2;
    private static HBox player3;
    private static VBox player4;
    private static VBox centerPane;
    private static BorderPane table;
    private static BorderPane playersLabel;
    private static CardObject[] player1Card;// = game.getPlayer1Hand();
    private static CardObject[] player2Card;// = game.getPlayer2Hand();
    private static CardObject[] player3Card;// = game.getPlayer3Hand();
    private static CardObject[] player4Card;// = game.getPlayer4Hand();

    private static ArrayList<CardObject> player1CardList;
    private static ArrayList<CardObject> player2CardList;
    private static ArrayList<CardObject> player3CardList;
    private static ArrayList<CardObject> player4CardList;

    private static ArrayList<Integer> spadeDeck = new ArrayList<>();
    private static ArrayList<Integer> clubDeck = new ArrayList<>();
    private static ArrayList<Integer> heartDeck = new ArrayList<>();
    private static ArrayList<Integer> diamondDeck = new ArrayList<>();

    private static Image[] playerBackCards = game.getPlayersBackCard();

    private static Pane Spades;
    private static Pane Clubs;
    private static Pane Diamonds;
    private static Pane Hearts;

    private static Button newGameButton = new Button("New Game");
    private static Button randomCardSelection = new Button("Random Card Selection");
    private static Button exitButton = new Button("Exit Game");
    private static CheckBox showCardsCheckBox = new CheckBox("Show all AI cards");
    private static Button player1Bt = new Button();
    private static Button btArray[] = new Button[13];

    private static double win_Display = 0;
    private static double loss_Display = 0;
    private static double totalWins = 0;
    private static double totalLoss = 0;
    private static double winPercent;

    private static Label stats = new Label();
    private static String statLabel = new String();

    private static int playersTurn; // TEST FOR PLAYERS TURN
    private static int spadesY = 150;
    private static int clubsY = 150;
    private static int diamondsY = 150;
    private static int heartsY = 150;
    private static int highSpadesY = 130;
    private static int highClubsY = 130;
    private static int highHeartsY = 130;
    private static int highDiamondsY = 130;

    public void start(Stage primaryStage) throws FileNotFoundException {
        readData();
        totalWins = win_Display;
        totalLoss = loss_Display;
        if (totalWins + totalLoss == 0) {
            winPercent = ((totalWins) / 1) * 10;
        } else {
            winPercent = (totalWins) / (totalWins + totalLoss) * 10;
        }

        Math.round(winPercent * 100 / 100);

        statLabel = "Total Wins: " + totalWins + " Total Losses: " + totalLoss + " Win Percentage: " + winPercent + "%";
        stats = new Label(statLabel);
        playersTurn = 1;
        game.initalizeDeck();
        game.makeImageArray();
        game.makeCardObjects();
        game.shuffle();
        game.createPlayerHand();

        player1Card = game.getPlayer1Hand();
        player2Card = game.getPlayer2Hand();
        player3Card = game.getPlayer3Hand();
        player4Card = game.getPlayer4Hand();

        player1CardList = new ArrayList(Arrays.asList(player1Card));
        player2CardList = new ArrayList(Arrays.asList(player2Card));
        player3CardList = new ArrayList(Arrays.asList(player3Card));
        player4CardList = new ArrayList(Arrays.asList(player4Card));

        primaryStage.setTitle("The Mickey Mantle Card Game");
        VBox vbox = new VBox();
        HBox buttonContainer = new HBox(5);
        //// TOP TOOLBAR BUTTONS | CHECKBOX ////
        buttonContainer.setAlignment(Pos.BASELINE_CENTER);
        // CHECK BOX
        showCardsCheckBox.setOnAction((value) -> {
            showHands();
        });
        // EXIT BUTTION
        exitButton.setOnAction((value)
                -> System.exit(0));

        // NEW GAME BUTTON
        newGameButton.setOnAction(e -> {
            System.out.println("Restarting App!");
            game = new CardsandHand();
            newGame();
        });
        // RANDOM CARD BUTTON
        randomCardSelection.setOnAction(value -> {
            randomCardSelect();
        });
        // STATISTICS DISPLAY
        buttonContainer.getChildren().addAll(newGameButton, randomCardSelection, showCardsCheckBox, exitButton, stats);
        // PLAYERS
        vbox.getChildren().add(buttonContainer);
        table = new BorderPane();
        vbox.getChildren().add(table);

//         CENTER BOARD FOR THE SUITS
//      MAKE MCKENNA MY BACKGROUND
        BackgroundImage myBackGround = new BackgroundImage(new Image("file:image.jpg", 800, 800, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        table.setBackground(new Background(myBackGround));
//        table.setStyle("-fx-background-color: #D2691E;");

        table.setPrefHeight(1000);
        playersLabel = new BorderPane();
        table.setCenter(playersLabel);

        // PLAYER LABEL
        Label player1Text = new Label("Player 1");
        player1Text.setFont(new Font("Arial", 24));
        Label player2Text = new Label("Player 2");
        player2Text.setFont(new Font("Arial", 24));
        Label player3Text = new Label("Player 3");
        player3Text.setFont(new Font("Arial", 24));
        Label player4Text = new Label("Player 4");
        player4Text.setFont(new Font("Arial", 24));

        // PLAYER 1 LABEL
        BorderPane.setAlignment(player1Text, Pos.CENTER);
        playersLabel.setBottom(player1Text);
        player1Text.setAlignment(Pos.CENTER);
        // PLAYER 2 LABEL
        BorderPane.setAlignment(player2Text, Pos.CENTER);
        playersLabel.setLeft(player2Text);
        player2Text.setRotate(90);
        player2Text.setAlignment(Pos.CENTER);
        // PLAYER 3 LABEL
        BorderPane.setAlignment(player3Text, Pos.CENTER);
        playersLabel.setTop(player3Text);
        player3Text.setAlignment(Pos.CENTER);
        // PLAYER 4 LABEL
        BorderPane.setAlignment(player4Text, Pos.CENTER);
        playersLabel.setRight(player4Text);
        player4Text.setRotate(270);
        player4Text.setAlignment(Pos.CENTER);

        loadPaneImages();
        loadCenterImages();
        playerToStack();

//        
        Scene theScene = new Scene(vbox, 1500, 900);
        primaryStage.setScene(theScene); // MAIN STAGE DIMENSIONS
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void newGame() {

        game.initalizeDeck();
        game.makeImageArray();
        game.makeCardObjects();
        game.shuffle();
        game.createPlayerHand();

        player1Card = game.getPlayer1Hand();
        player2Card = game.getPlayer2Hand();
        player3Card = game.getPlayer3Hand();
        player4Card = game.getPlayer4Hand();

        player1CardList = new ArrayList(Arrays.asList(player1Card));
        player2CardList = new ArrayList(Arrays.asList(player2Card));
        player3CardList = new ArrayList(Arrays.asList(player3Card));
        player4CardList = new ArrayList(Arrays.asList(player4Card));

        // CLEAR THE CENTER STACKS
        Spades = new Pane();
        Clubs = new Pane();
        Diamonds = new Pane();
        Hearts = new Pane();

        spadeDeck = new ArrayList<>();
        clubDeck = new ArrayList<>();
        heartDeck = new ArrayList<>();
        diamondDeck = new ArrayList<>();

        player1 = new HBox();
        player2 = new VBox();
        player3 = new HBox();
        player4 = new VBox();

        showCardsCheckBox.setSelected(false);

        spadesY = 150;
        clubsY = 150;
        diamondsY = 150;
        heartsY = 150;
        highSpadesY = 130;
        highClubsY = 130;
        highHeartsY = 130;
        highDiamondsY = 130;

        loadPaneImages();
        loadCenterImages();
        playerToStack();
    }

    private void showHands() {
        for (int j = 0; j < player2.getChildren().size(); j++) {
            if (!showCardsCheckBox.isSelected()) {
                ImageView imageView = (ImageView) player2.getChildren().get(j);
                imageView.setImage(playerBackCards[j]);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
            } else {
                ImageView imageview = (ImageView) player2.getChildren().get(j);
                imageview.setImage(player2Card[j].getImage());
                imageview.setFitHeight(50);
                imageview.setFitWidth(50);
            }

            for (int k = 0; k < player3.getChildren().size(); k++) {
                if (!showCardsCheckBox.isSelected()) {
                    ImageView imageView = (ImageView) player3.getChildren().get(k);
                    imageView.setImage(playerBackCards[j]);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                } else {
                    ImageView imageview = (ImageView) player3.getChildren().get(k);
                    imageview.setImage(player3Card[k].getImage());
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                }

                for (int o = 0; o < player4.getChildren().size(); o++) {
                    if (!showCardsCheckBox.isSelected()) {
                        ImageView imageView = (ImageView) player4.getChildren().get(o);
                        imageView.setImage(playerBackCards[o]);
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                    } else {
                        ImageView imageview = (ImageView) player4.getChildren().get(o);
                        imageview.setImage(player4Card[o].getImage());
                        imageview.setFitHeight(50);
                        imageview.setFitWidth(50);
                    }
                }
            }
        }
    }

    private void loadPaneImages() {
        // PLAYER 1 VIEW
        player1 = new HBox();
        table.setBottom(player1);
        player1.setAlignment(Pos.CENTER);
        player1.setStyle("-fx-background-color: #D2691E;");
        for (int i = 0, p = 0; i < player1CardList.size() && p < player1CardList.size(); p++, i++) {
            ImageView imageView = new ImageView(player1CardList.get(i).getImage());
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            btArray[p] = new Button();
            btArray[p].setGraphic(imageView);
            btArray[p].setId(player1CardList.get(i).getSuit() + "_" + player1CardList.get(i).getRank());
            player1.getChildren().add(btArray[p]);

        }
        // PLAYER 2 VIEW
        player2 = new VBox();
//        player2.setStyle("-fx-background-color: #FF0000");
        table.setLeft(player2);
        player2.setAlignment(Pos.CENTER);
        for (Image i : game.getPlayersBackCard()) {
            ImageView imageView = new ImageView(i);
            imageView.setRotate(90);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            player2.getChildren().add(imageView);
        }
        // PLAYER 3 VIEW
        player3 = new HBox();
//        player3.setStyle("-fx-background-color: #FFFFFF");
        table.setTop(player3);
        player3.setAlignment(Pos.CENTER);
        for (Image i : game.getPlayersBackCard()) {
            ImageView imageView = new ImageView(i);
            imageView.setRotate(180);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            player3.getChildren().add(imageView);
        }
        // PLAYER 4 VIEW
        player4 = new VBox();
//        player4.setStyle("-fx-background-color: #FF00FF");
        table.setRight(player4);
        player4.setAlignment(Pos.CENTER);
        for (Image i : game.getPlayersBackCard()) {
            ImageView imageView = new ImageView(i);
            imageView.setRotate(270);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            player4.getChildren().add(imageView);
        }

    }

    private void loadCenterImages() {
//        Pane label = new Pane();
        centerPane = new VBox();
        Label player1Turn = new Label("Player 1 Turn");
        player1Turn.setFont(new Font("Arial", 40));
        player1Turn.setTextFill(Color.web("#000000"));

        Label player2Turn = new Label("Player 2 Turn");
        player2Turn.setFont(new Font("Arial", 40));
        player2Turn.setTextFill(Color.web("#000000"));

        Label player3Turn = new Label("Player 3 Turn");
        player3Turn.setFont(new Font("Arial", 40));
        player3Turn.setTextFill(Color.web("#000000"));

        Label player4Turn = new Label("Player 4 Turn");
        player4Turn.setFont(new Font("Arial", 40));
        player4Turn.setTextFill(Color.web("#000000"));

        if (playersTurn == 1) {
            centerPane.getChildren().add(0, player1Turn);
        } else if (playersTurn == 2) {
            centerPane.getChildren().add(0, player2Turn);
        } else if (playersTurn == 3) {
            centerPane.getChildren().add(0, player3Turn);
        } else if (playersTurn == 4) {
            centerPane.getChildren().add(0, player1Turn);
            playersTurn = 0;
        }
//        
        HBox centerStacksStage = new HBox();

        Spades = new Pane();
        Clubs = new Pane();
        Diamonds = new Pane();
        Hearts = new Pane();

        Spades.setPadding(new Insets(50));
        Clubs.setPadding(new Insets(50));
        Diamonds.setPadding(new Insets(50));
        Hearts.setPadding(new Insets(50));

        centerStacksStage.setSpacing(50);
        // BUTTON ARRAY FOR PLAYER 

        centerPane.setAlignment(Pos.TOP_CENTER);

        playersLabel.setCenter(centerPane);
        centerStacksStage.setAlignment(Pos.CENTER);
        centerPane.getChildren().add(centerStacksStage);

        Clubs.setPrefHeight(200);
        Spades.setPrefHeight(200);
        Hearts.setPrefHeight(200);
        Diamonds.setPrefHeight(200);

        centerStacksStage.getChildren().addAll(Spades, Clubs, Diamonds, Hearts);
//        centerStacksStage.setStyle("-fx-background-color:#F0FF00"); // YELLOW

    }

    private boolean checkWinner() {
        if (player1.getChildren().isEmpty()) {
            Label winnerResult = new Label("Player 1 is the Winner!!!");
            winnerResult.setFont(new Font("Arial", 40));
            winnerResult.setTextFill(Color.web("#000000"));
            centerPane.getChildren().clear();
            centerPane.getChildren().add(winnerResult);
            totalWins++;
            recordDisplay();
            return true;
        } else if (player2.getChildren().isEmpty()) {
            Label winnerResult = new Label("Player 2 is the Winner!!!");
            winnerResult.setFont(new Font("Arial", 40));
            winnerResult.setTextFill(Color.web("#000000"));
            centerPane.getChildren().clear();
            centerPane.getChildren().add(winnerResult);
            player2.getChildren().clear();
            totalLoss++;
            recordDisplay();
            return true;
        } else if (player3.getChildren().isEmpty()) {
            Label winnerResult = new Label("Player 3 is the Winner!!!");
            winnerResult.setFont(new Font("Arial", 40));
            winnerResult.setTextFill(Color.web("#000000"));
            centerPane.getChildren().clear();
            centerPane.getChildren().add(winnerResult);
            player3.getChildren().clear();
            totalLoss++;
            recordDisplay();
            return true;
        } else if (player4.getChildren().isEmpty()) {
            Label winnerResult = new Label("Player 4 is the Winner!!!");
            winnerResult.setFont(new Font("Arial", 40));
            winnerResult.setTextFill(Color.web("#000000"));
            centerPane.getChildren().clear();
            centerPane.getChildren().add(winnerResult);
            player4.getChildren().clear();
            totalLoss++;
            recordDisplay();
            return true;
        }
        return false;
    }

    private void playerToStack() {
        if (checkWinner() == false) {
            ArrayList<CardObject> playableCards = new ArrayList<>();
            ArrayList<Integer> playableCardIndex = new ArrayList<>();

            for (int i = 0; i < player1CardList.size(); i++) {
                if (checkPlayableCard(player1CardList.get(i))) {
                    playableCards.add(player1CardList.get(i));
                    playableCardIndex.add(i);
                }
            }
            if (playableCards.size() > 0) {
                for (int i = 0; i < player1CardList.size(); i++) {
                    ImageView image = new ImageView(player1CardList.get(i).getImage());
                    CardObject card = player1CardList.get(i);
                    Button bt = btArray[i];
                    int removed = 0;
                    for (int k = 0; k < player1CardList.size(); k++) {
                        for (int p = 0; p < playableCards.size(); p++) {
                            if (player1CardList.get(k).equals(playableCards.get(p))) {
                                removed = player1CardList.indexOf(playableCards.get(p));
                            }
                        }
                    }
                    int remove = removed;
                    bt.setOnAction(value -> {
                        boolean spadeCheck = bt.getId().contains("spade");
                        boolean clubCheck = bt.getId().contains("club");
                        boolean heartCheck = bt.getId().contains("heart");
                        boolean diamondCheck = bt.getId().contains("diamond");
                        if (spadeCheck == true) {
                            if (checkPlayableCard(card)) {
                                if (card.getRank() == 14 && spadeDeck.contains(2)) {
                                    image.setLayoutY(spadesY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    spadeDeck.add(card.getRank());
                                    Spades.getChildren().addAll(image);
                                    spadesY += 20;
                                    player1.getChildren().remove(bt);
                                } else if (card.getRank() > 7) {
                                    image.setLayoutY(highSpadesY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    spadeDeck.add(card.getRank());
                                    Spades.getChildren().addAll(image);
                                    image.toBack();
                                    highSpadesY -= 20;
                                    player1.getChildren().remove(bt);
                                } else {
                                    image.setLayoutY(spadesY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    spadeDeck.add(card.getRank());
                                    Spades.getChildren().addAll(image);
                                    spadesY += 20;
                                    player1.getChildren().remove(bt);
                                }
//                            createDelay();
//                            playerLabelTurn(2);
                                aiPlayer2();
                            }
                        } else if (clubCheck == true) {
                            if (checkPlayableCard(card)) {
                                if (card.getRank() == 14 && clubDeck.contains(2)) {
                                    image.setLayoutY(clubsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    clubDeck.add(card.getRank());
                                    Clubs.getChildren().addAll(image);
                                    clubsY += 20;
                                    player1.getChildren().remove(bt);
                                } else if (card.getRank() > 7) {

                                    image.setLayoutY(highClubsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    clubDeck.add(card.getRank());
                                    Clubs.getChildren().addAll(image);
                                    image.toBack();
                                    highClubsY -= 20;
                                    player1.getChildren().remove(bt);

                                } else {
                                    image.setLayoutY(clubsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    clubDeck.add(card.getRank());
                                    Clubs.getChildren().addAll(image);
                                    clubsY += 20;
                                    player1.getChildren().remove(bt);
                                }

//                            createDelay();
//                            playerLabelTurn(2);
                                aiPlayer2();
                            }
                        } else if (heartCheck == true) {
                            if (checkPlayableCard(card)) {
                                if (card.getRank() == 14 && heartDeck.contains(2)) {
                                    image.setLayoutY(heartsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    heartDeck.add(card.getRank());
                                    Hearts.getChildren().addAll(image);
                                    heartsY += 20;
                                    player1.getChildren().remove(bt);
                                } else if (card.getRank() > 7) {

                                    image.setLayoutY(highHeartsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    heartDeck.add(card.getRank());
                                    Hearts.getChildren().addAll(image);
                                    image.toBack();
                                    highHeartsY -= 20;
                                    player1.getChildren().remove(bt);
                                } else {
                                    image.setLayoutY(heartsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    heartDeck.add(card.getRank());
                                    Hearts.getChildren().addAll(image);
                                    heartsY += 20;
                                    player1.getChildren().remove(bt);
                                }
//                            createDelay();
//                            playerLabelTurn(2);
                                aiPlayer2();
                            }
                        } else if (diamondCheck == true) {
                            if (checkPlayableCard(card)) {
                                if (card.getRank() == 14 && diamondDeck.contains(2)) {
                                    image.setLayoutY(diamondsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    diamondDeck.add(card.getRank());
                                    Diamonds.getChildren().addAll(image);
                                    diamondsY += 20;
                                    player1.getChildren().remove(bt);
                                } else if (card.getRank() > 7) {

                                    image.setLayoutY(highDiamondsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    diamondDeck.add(card.getRank());
                                    Diamonds.getChildren().addAll(image);
                                    image.toBack();
                                    highDiamondsY -= 20;
                                    player1.getChildren().remove(bt);

                                } else {
                                    image.setLayoutY(diamondsY);
                                    image.setFitHeight(100);
                                    image.setFitWidth(100);
                                    diamondDeck.add(card.getRank());
                                    Diamonds.getChildren().addAll(image);
                                    diamondsY += 20;
                                    player1.getChildren().remove(bt);
                                }
//                            createDelay();
//                            playerLabelTurn(2);
                                aiPlayer2();
                            }
                        }
                    });
                }
            } else if (playableCards.isEmpty()) {
//            createDelay();
//            playerLabelTurn(2);
                aiPlayer2();
            }
        }
    }

    private void nextPlayer() {
        playersTurn++;
    }

    private void aiPlayer2() {
        int rankTop;
        int rankBot;
        if (checkWinner() == false) {
            ArrayList<CardObject> playableCards = new ArrayList<>();
            ArrayList<Integer> playableCardIndex = new ArrayList<>();

            for (int i = 0; i < player2CardList.size(); i++) {
                if (checkPlayableCard(player2CardList.get(i))) {
                    playableCards.add(player2CardList.get(i));
                    playableCardIndex.add(i);
                }
            }
            if (playableCards.size() > 0) {
                int random = (int) (Math.random() * playableCards.size());
                CardObject playingCard = playableCards.get(random);
                ImageView image = new ImageView(playingCard.getImage());
                int remove = playableCardIndex.get(random);
                if (playingCard.getSuit().equals("spade")) {
                    if (playingCard.getRank() == 14 && spadeDeck.contains(2)) {
                        image.setLayoutY(spadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        spadesY += 20;
                        player2.getChildren().remove(remove);
                        player2CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {
                        image.setLayoutY(highSpadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        image.toBack();
                        highSpadesY -= 20;
                        player2.getChildren().remove(remove);
                        player2CardList.remove(remove);

                    } else {
                        image.setLayoutY(spadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        spadesY += 20;
                        player2.getChildren().remove(remove);
                        player2CardList.remove(remove);
                    }

//                createDelay();
//                playerLabelTurn(3);
                    aiPlayer3();
                } else if (playingCard.getSuit().equals("club")) {
                    if (playingCard.getRank() == 14 && clubDeck.contains(2)) {
                        image.setLayoutY(clubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        clubsY += 20;
                        player2.getChildren().remove(remove);

                        player2CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highClubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        image.toBack();
                        highClubsY -= 20;
                        player2.getChildren().remove(remove);

                        player2CardList.remove(remove);

                    } else {
                        image.setLayoutY(clubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        clubsY += 20;
                        player2.getChildren().remove(remove);

                        player2CardList.remove(remove);
                    }
//                checkWinner();
//                createDelay();
//                playerLabelTurn(3);
                    aiPlayer3();

                } else if (playingCard.getSuit().equals("heart")) {
                    if (playingCard.getRank() == 14 && heartDeck.contains(2)) {
                        image.setLayoutY(heartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        heartsY += 20;
                        player2.getChildren().remove(remove);

                        player2CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highHeartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        image.toBack();
                        highHeartsY -= 20;
                        player2.getChildren().remove(remove);

                        player2CardList.remove(remove);

                    } else {
                        image.setLayoutY(heartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        heartsY += 20;
                        player2.getChildren().remove(remove);

                        player2CardList.remove(remove);
                    }
//                checkWinner();
//                createDelay();
//                playerLabelTurn(3);
                    aiPlayer3();

                } else if (playingCard.getSuit().equals("diamond")) {
                    if (playingCard.getRank() > 7) {
                        if (playingCard.getRank() == 14 && heartDeck.contains(2)) {
                            image.setLayoutY(diamondsY);
                            image.setFitHeight(100);
                            image.setFitWidth(100);
                            diamondDeck.add(playingCard.getRank());
                            Diamonds.getChildren().addAll(image);
                            diamondsY += 20;
                            player2.getChildren().remove(remove);

                            player2CardList.remove(remove);
                        }
                        image.setLayoutY(highDiamondsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        diamondDeck.add(playingCard.getRank());
                        Diamonds.getChildren().addAll(image);
                        image.toBack();
                        highDiamondsY -= 20;
                        player2.getChildren().remove(remove);

                        player2CardList.remove(remove);

                    } else {
                        image.setLayoutY(diamondsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        diamondDeck.add(playingCard.getRank());
                        Diamonds.getChildren().addAll(image);
                        diamondsY += 20;
                        player2.getChildren().remove(remove);

                        player2CardList.remove(remove);
                    }
//                checkWinner();
//                createDelay();
//                playerLabelTurn(3);
                    aiPlayer3();

                }
            } else if (playableCards.isEmpty()) {
//            checkWinner();
//            createDelay();
//            playerLabelTurn(3);
                aiPlayer3();
            }
        }
    }

    private void aiPlayer3() {
        int rankTop;
        int rankBot;
        if (checkWinner() == false) {

            ArrayList<CardObject> playableCards = new ArrayList<>();
            ArrayList<Integer> playableCardIndex = new ArrayList<>();

            for (int i = 0; i < player3CardList.size(); i++) {
                if (checkPlayableCard(player3CardList.get(i))) {
                    playableCards.add(player3CardList.get(i));
                    playableCardIndex.add(i);
                }
            }
            if (playableCards.size() > 0) {
                int random = (int) (Math.random() * playableCards.size());
                CardObject playingCard = playableCards.get(random);
                ImageView image = new ImageView(playingCard.getImage());
                int remove = playableCardIndex.get(random);
                if (playingCard.getSuit().equals("spade")) {
                    if (playingCard.getRank() == 14 && spadeDeck.contains(2)) {
                        image.setLayoutY(spadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        spadesY += 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highSpadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        image.toBack();
                        highSpadesY -= 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);

                    } else {
                        image.setLayoutY(spadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        spadesY += 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);
                    }
//                checkWinner();
//                createDelay();
//                playerLabelTurn(4);
                    aiPlayer4();
                } else if (playingCard.getSuit().equals("club")) {
                    if (playingCard.getRank() == 14 && clubDeck.contains(2)) {
                        image.setLayoutY(clubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        clubsY += 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highClubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        image.toBack();
                        highClubsY -= 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);

                    } else {
                        image.setLayoutY(clubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        clubsY += 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);
                    }
//                checkWinner();
//                createDelay();
//                playerLabelTurn(4);
                    aiPlayer4();

                } else if (playingCard.getSuit().equals("heart")) {
                    if (playingCard.getRank() == 14 && heartDeck.contains(2)) {
                        image.setLayoutY(heartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        heartsY += 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highHeartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        image.toBack();
                        highHeartsY -= 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);

                    } else {
                        image.setLayoutY(heartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        heartsY += 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);
                    }
//                checkWinner();
//                createDelay();
//                playerLabelTurn(4);
                    aiPlayer4();
                } else if (playingCard.getSuit().equals("diamond")) {
                    if (playingCard.getRank() == 14 && heartDeck.contains(2)) {
                        image.setLayoutY(diamondsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        diamondDeck.add(playingCard.getRank());
                        Diamonds.getChildren().addAll(image);
                        diamondsY += 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highDiamondsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        diamondDeck.add(playingCard.getRank());
                        Diamonds.getChildren().addAll(image);
                        image.toBack();
                        highDiamondsY -= 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);

                    } else {
                        image.setLayoutY(diamondsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        diamondDeck.add(playingCard.getRank());
                        Diamonds.getChildren().addAll(image);
                        diamondsY += 20;
                        player3.getChildren().remove(remove);

                        player3CardList.remove(remove);
                    }
//                checkWinner();
//                createDelay();
//                playerLabelTurn(4);
                    aiPlayer4();
                }
            } else if (playableCards.isEmpty()) {
                checkWinner();
//            createDelay();
//            playerLabelTurn(4);
                aiPlayer4();
            }
        }
    }

    private void aiPlayer4() {
        int rankTop;
        int rankBot;
        if (checkWinner() == false) {

            ArrayList<CardObject> playableCards = new ArrayList<>();
            ArrayList<Integer> playableCardIndex = new ArrayList<>();

            for (int i = 0; i < player4CardList.size(); i++) {
                if (checkPlayableCard(player4CardList.get(i))) {
                    playableCards.add(player4CardList.get(i));
                    playableCardIndex.add(i);
                }
            }
            if (playableCards.size() > 0) {
                int random = (int) (Math.random() * playableCards.size());
                CardObject playingCard = playableCards.get(random);
                ImageView image = new ImageView(playingCard.getImage());
                int remove = playableCardIndex.get(random);
                if (playingCard.getSuit().equals("spade")) {
                    if (playingCard.getRank() == 14 && spadeDeck.contains(2)) {
                        image.setLayoutY(spadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        spadesY += 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {
                        image.setLayoutY(highSpadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        image.toBack();
                        highSpadesY -= 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);
                    } else {
                        image.setLayoutY(spadesY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        spadeDeck.add(playingCard.getRank());
                        Spades.getChildren().addAll(image);
                        spadesY += 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);
                    }
                    playerToStack();
                } else if (playingCard.getSuit().equals("club")) {
                    if (playingCard.getRank() == 14 && clubDeck.contains(2)) {
                        image.setLayoutY(clubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        clubsY += 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highClubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        image.toBack();
                        highClubsY -= 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);

                    } else {
                        image.setLayoutY(clubsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        clubDeck.add(playingCard.getRank());
                        Clubs.getChildren().addAll(image);
                        clubsY += 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);
                    }
                    playerToStack();
                } else if (playingCard.getSuit().equals("heart")) {
                    if (playingCard.getRank() == 14 && heartDeck.contains(2)) {
                        image.setLayoutY(heartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        heartsY += 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highHeartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        image.toBack();
                        highHeartsY -= 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);

                    } else {
                        image.setLayoutY(heartsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        heartDeck.add(playingCard.getRank());
                        Hearts.getChildren().addAll(image);
                        heartsY += 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);
                    }
                    playerToStack();

                } else if (playingCard.getSuit().equals("diamond")) {
                    if (playingCard.getRank() == 14 && diamondDeck.contains(2)) {
                        image.setLayoutY(diamondsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        diamondDeck.add(playingCard.getRank());
                        Diamonds.getChildren().addAll(image);
                        diamondsY += 20;
                        player4.getChildren().remove(remove);

                        player4CardList.remove(remove);
                    } else if (playingCard.getRank() > 7) {

                        image.setLayoutY(highDiamondsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        diamondDeck.add(playingCard.getRank());
                        Diamonds.getChildren().addAll(image);
                        image.toBack();
                        highDiamondsY -= 20;
                        player4.getChildren().remove(remove);
                        player4CardList.remove(remove);

                    } else {
                        image.setLayoutY(diamondsY);
                        image.setFitHeight(100);
                        image.setFitWidth(100);
                        diamondDeck.add(playingCard.getRank());
                        Diamonds.getChildren().addAll(image);
                        diamondsY += 20;
                        player4.getChildren().remove(remove);
                        player4CardList.remove(remove);
                    }
                    playerToStack();
                } else if (playableCards.isEmpty()) {
                    playerToStack();
                }
            }
        }
    }

    public boolean checkPlayableCard(CardObject card) {
        if (card.getSuit().equals("spade")) {
            if (Spades.getChildren().isEmpty()) {
                if (card.getRank() == 7) {
                    return true;
                }
            } else {
                int rankTop = spadeDeck.get(0);
                int rankBot = spadeDeck.get(0);
                for (int i = 1; i < spadeDeck.size(); i++) {
                    if (spadeDeck.get(i) > rankTop) {
                        rankTop = spadeDeck.get(i);
                    }
                }
                for (int i = 1; i < spadeDeck.size(); i++) {
                    if (spadeDeck.get(i) < rankBot) {
                        rankBot = spadeDeck.get(i);
                    }
                }
                if ((card.getRank()) == (rankBot - 1)) {
                    return true;
                } else if ((card.getRank()) == (rankTop + 1)) {
                    return true;
                } else if ((card.getRank() - 13) == (rankBot - 1)) {
                    return true;
                }
            }
        } else if (card.getSuit().equals("club")) {
            if (Clubs.getChildren().isEmpty()) {
                if (card.getRank() == 7) {
                    return true;
                }
            } else {
                int rankTop = clubDeck.get(0);
                int rankBot = clubDeck.get(0);
                for (int i = 1; i < clubDeck.size(); i++) {
                    if (clubDeck.get(i) > rankTop) {
                        rankTop = clubDeck.get(i);
                    }
                }
                for (int i = 1; i < clubDeck.size(); i++) {
                    if (clubDeck.get(i) < rankBot) {
                        rankBot = clubDeck.get(i);
                    }
                }
                if ((card.getRank()) == (rankBot - 1)) {
                    return true;
                } else if ((card.getRank()) == (rankTop + 1)) {
                    return true;
                } else if ((card.getRank() - 13) == (rankBot - 1)) {
                    return true;
                }
            }
        } else if (card.getSuit().equals("heart")) {
            if (Hearts.getChildren().isEmpty()) {
                if (card.getRank() == 7) {
                    return true;
                }
            } else {
                int rankTop = heartDeck.get(0);
                int rankBot = heartDeck.get(0);
                for (int i = 1; i < heartDeck.size(); i++) {
                    if (heartDeck.get(i) > rankTop) {
                        rankTop = heartDeck.get(i);
                    }
                }
                for (int i = 1; i < heartDeck.size(); i++) {
                    if (heartDeck.get(i) < rankBot) {
                        rankBot = heartDeck.get(i);
                    }
                }
                if ((card.getRank()) == (rankBot - 1)) {
                    return true;
                } else if ((card.getRank()) == (rankTop + 1)) {
                    return true;
                } else if ((card.getRank() - 13) == (rankBot - 1)) {
                    return true;
                }
            }
        } else if (card.getSuit().equals("diamond")) {
            if (Diamonds.getChildren().isEmpty()) {
                if (card.getRank() == 7) {
                    return true;
                }
            } else {
                int rankTop = diamondDeck.get(0);
                int rankBot = diamondDeck.get(0);
                for (int i = 1; i < diamondDeck.size(); i++) {
                    if (diamondDeck.get(i) > rankTop) {
                        rankTop = diamondDeck.get(i);
                    }
                }
                for (int i = 1; i < diamondDeck.size(); i++) {
                    if (diamondDeck.get(i) < rankBot) {
                        rankBot = diamondDeck.get(i);
                    }
                }
                if ((card.getRank()) == (rankBot - 1)) {
                    return true;
                } else if ((card.getRank()) == (rankTop + 1)) {
                    return true;
                } else if ((card.getRank() - 13) == (rankBot - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void createDelay() {
        long stop = 1000000000;
        for (int delay = 0; delay < stop; delay++) {
            for (int j = 0; j < 100; j++) {

            }
        }
    }

    private void randomCardSelect() {
        ArrayList<CardObject> playableCards = new ArrayList<>();
        ArrayList<Integer> Indexes = new ArrayList<>();
        CardObject card = new CardObject();
        ImageView image = new ImageView();
        int remover = 0;
        int random = 0;
        String suit;
        for (int i = 0; i < player1CardList.size(); i++) {
            if (checkPlayableCard(player1CardList.get(i))) {
                playableCards.add(player1CardList.get(i));
                Indexes.add(i);
            }
        }
        if (playableCards.size() > 0) {
            random = (int) (Math.random() * playableCards.size());
            remover = Indexes.get(random);
            card = playableCards.get(random);
            suit = card.getSuit();
            image = new ImageView(card.getImage());
            image.setFitHeight(100);
            image.setFitWidth(100);
            player1.getChildren().remove(remover);
            player1CardList.remove(remover);
            if (suit.equals("diamond")) {
                if (card.getRank() == 14 && diamondDeck.contains(2)) {
                    image.setLayoutY(diamondsY);
                    Diamonds.getChildren().addAll(image);
                    diamondsY += 20;
                } else if (card.getRank() > 7) {
                    image.setLayoutY(highDiamondsY);
                    Diamonds.getChildren().addAll(image);
                    image.toBack();
                    highDiamondsY -= 20;
                } else {
                    image.setLayoutY(diamondsY);
                    Diamonds.getChildren().addAll(image);
                    diamondsY += 20;
                }
                diamondDeck.add(card.getRank());
                aiPlayer2();
            } else if (suit.equals("spade")) {
                if (card.getRank() == 14 && spadeDeck.contains(2)) {
                    image.setLayoutY(spadesY);
                    Spades.getChildren().addAll(image);
                    spadesY += 20;
                } else if (card.getRank() > 7) {
                    image.setLayoutY(highSpadesY);
                    Spades.getChildren().addAll(image);
                    image.toBack();
                    highSpadesY -= 20;

                } else {
                    image.setLayoutY(spadesY);
                    Spades.getChildren().addAll(image);
                    spadesY += 20;
                }
                spadeDeck.add(card.getRank());
                aiPlayer2();

            } else if (suit.equals("heart")) {
                if (card.getRank() == 14 && heartDeck.contains(2)) {
                    image.setLayoutY(heartsY);
                    Hearts.getChildren().addAll(image);
                    heartsY += 20;
                } else if (card.getRank() > 7) {
                    image.setLayoutY(highHeartsY);
                    Hearts.getChildren().addAll(image);
                    image.toBack();
                    highHeartsY -= 20;
                } else {
                    image.setLayoutY(heartsY);
                    Hearts.getChildren().addAll(image);
                    heartsY += 20;
                }
                heartDeck.add(card.getRank());
                aiPlayer2();

            } else if (suit.equals("club")) {
                if (card.getRank() == 14 && clubDeck.contains(2)) {
                    image.setLayoutY(clubsY);
                    Clubs.getChildren().addAll(image);

                    clubsY += 20;
                } else if (card.getRank() > 7) {
                    image.setLayoutY(highClubsY);
                    Clubs.getChildren().addAll(image);

                    image.toBack();
                    highClubsY -= 20;
                } else {
                    image.setLayoutY(clubsY);
                    Clubs.getChildren().addAll(image);

                    clubsY += 20;
                }
                clubDeck.add(card.getRank());
                aiPlayer2();
            }
        } else if (playableCards.isEmpty()) {
            aiPlayer2();
        }
    }

    private void recordDisplay() {
        try {
            FileWriter write = new FileWriter("Data.txt");
            PrintWriter print = new PrintWriter(write);
            print.println(totalWins);
            print.println(totalLoss);
            print.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    private void readData() throws FileNotFoundException {
        FileReader read = new FileReader("Data.txt");

        Scanner input = new Scanner(read);
        while (input.hasNextLine()) {
            win_Display = Double.parseDouble(input.nextLine());
            loss_Display = Double.parseDouble(input.nextLine());
        }
    }
}
