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
 * Author: Henry Nguyen
 * SBU ID: 111484010
 * Net ID; Henguyen
 * CSE 114
 */
public class CardObject {
    private String imageName = "";
    private Image img;
    
    public CardObject() {

    }
    public CardObject (String imageName, Image img) {
        this.imageName = imageName;
        this.img = img;
    }
    
    public Image getImage() {
        return this.img;
    }
    
    public String getSuit() {
        int a = imageName.indexOf("_",10);
        int b = imageName.lastIndexOf(".");
        String temp = this.imageName.substring(a + 1,b);
        String[] cardSuitRank = temp.split("_");
        return cardSuitRank[0];
    }
    
    public int getRank() {
        int a = imageName.indexOf("_", 10);
        int b = imageName.lastIndexOf(".");
        String temp = this.imageName.substring(a + 1, b);
        String[] cardSuitRank = temp.split("_");
        return Integer.parseInt(cardSuitRank[1]);
    }
    public String toString() {
        int a = imageName.indexOf("_", 10);
        int b = imageName.lastIndexOf(".");
        String temp = this.imageName.substring(a + 1, b);
        return temp;
    }
}
