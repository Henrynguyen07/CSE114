/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package part2;
import java.util.*;
/**
 *
 * Author: Henry Nguyen
 * SBU ID: 111484010
 * Net ID; Henguyen
 * CSE 114
 */
public class LetterCounter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean startFlag = true;
        char[] plain = new char[26];
        int[] occurence = new int[26];
        // Creates the Plain Ascii array from char #65 - #90 (Inclusive)
        int count = 65;
        for (int i = 0; i < 26; i++) {
            char b = (char) (count);
            plain[i] = (b);
            count ++;
        }
        while (startFlag == true) {
            System.out.println("*** The Letter Counter ***");
            System.out.println("1) Enter new Text");
            System.out.println("q) Quit");
            System.out.print("Enter: ");
            String select = input.nextLine();
            System.out.println();
            if (select.equals("1")) {
                System.out.print("Enter a text to analyze: ");
                String text = input.nextLine();
                LetterCount(text, plain, occurence);
                System.out.println();
            } else if (select.equalsIgnoreCase("q")) {
                System.out.println();
                System.out.println("GoodBye!");
                startFlag = false;
            }
        }
    }
    
    
    public static void LetterCount(String text, char[] letter, int[] occurence) {
        text = text.toUpperCase();
        int counter = 0;
        for (int j = 0; j < letter.length; j++) {
            for (int k = 0; k < text.length(); k++) {
                if (text.charAt(k) == letter[j]) {
                    counter++;
                }
            }
            occurence[j] = counter;
            counter = 0;
        }
        for (int a = 0; a < letter.length; a ++) {

            System.out.println(letter[a] + ": " + occurence[a]);
        }      
        int temp = 0;
        for (int x = 0; x < occurence.length; x ++) {
            if (occurence[x] > temp) {
                temp = occurence[x];
            }
        }
        System.out.print("The following letters occured " + temp + " times: ");
        for (int y = 0; y < occurence.length; y++) {
            if (occurence[y] == temp) {
                System.out.print("'" + letter[y] + "' ");
            }
        }
    }
}
