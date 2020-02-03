/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MickeyMantle;

import java.util.Arrays;
import javafx.scene.image.Image;

/**
 *
 * Author: Henry Nguyen SBU ID: 111484010 Net ID; Henguyen CSE 114
 */
public class CardsandHand {

    private static String[] SUITS = {"club", "spade", "diamond", "heart"};
    private static String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    private static int n = SUITS.length * RANKS.length;

    private static String[] deck = new String[n];
    private static Image[] deckOfImages = new Image[n];

    private static CardObject[] cardObject = new CardObject[n];
    private CardObject[] player1Hand = new CardObject[13];
    private CardObject[] player2Hand = new CardObject[13];
    private CardObject[] player3Hand = new CardObject[13];
    private CardObject[] player4Hand = new CardObject[13];
//    private Image richard = new Image("RichardMckenna.jpg");

    private static Image[] playersBackCard = new Image[13];

    final String FILE_PROTOCOL = "file:"; 
    final String IMAGES_PATH = "./Images/";
    private String ImageURL = FILE_PROTOCOL + IMAGES_PATH;
//    file:./Images/
    public void initalizeDeck() {
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                deck[SUITS.length * i + j] = "Playing_card_" + SUITS[j] + "_" + RANKS[i] + ".jpg";
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < n; i++) {
            int r = i + (int) (Math.random() * (n - i));
            CardObject temp = cardObject[r];
            cardObject[r] = cardObject[i];
            cardObject[i] = temp;
        }
    }

    public void makeImageArray() {
        for (int i = 0; i < deck.length; i++) {
            deckOfImages[i] = loadImage(deck[i]);
        }
    }

    public void makeCardObjects() {
        for (int i = 0; i < deck.length; i++) {
            CardObject obj = new CardObject (deck[i], deckOfImages[i]);
            cardObject[i] = obj;
        }
    }

    public void createPlayerHand() {
        for (int i = 0; i < 13; i++) {
            player1Hand[i] = cardObject[i];
        }

        for (int j = 13; j < 26; j++) {
            player2Hand[j - 13] = cardObject[j];
        }

        for (int k = 26; k < 39; k++) {
            player3Hand[k - 26] = cardObject[k];
        }

        for (int l = 39; l < 52; l++) {
            player4Hand[l - 39] = cardObject[l];
        }
        for (int p = 0; p < 13; p++) {
            playersBackCard[p] = loadImage("Back_Of_Card.jpg");
        }
    }

    public String deckToString() {
        return Arrays.toString(deck);
    }

    public CardObject[] getPlayer1Hand() {
        return player1Hand.clone();
    }

    public CardObject[] getPlayer2Hand() {
        return player2Hand.clone();
    }

    public CardObject[] getPlayer3Hand() {
        return player3Hand.clone();
    }

    public CardObject[] getPlayer4Hand() {
        return player4Hand.clone();
    }

    public Image[] getPlayersBackCard() {
        return this.playersBackCard;
    }
//    public Image getRichard() {
//        return this.richard;
//    }

    private Image loadImage(String imageFileURL) {
        Image image = new Image(ImageURL + imageFileURL );
        if (!image.isError()) {
            return image;
        } else {
            return null;
        }
    }
}
