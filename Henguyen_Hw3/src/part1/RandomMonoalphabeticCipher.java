package part1;

/**
 *
 * Author: Henry Nguyen 
 * SBU ID: 111484010 
 * Net ID; Henguyen 
 * CSE 114
 */
import java.util.*;
import java.util.Arrays;

public class RandomMonoalphabeticCipher {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean KeyFlag = true;
        char[] key = new char[95];
        char[] plain = new char[95];
            // Creates the Plain Ascii array from char #32 - #126 (Inclusive)
            int count = 32;
            for (int i = 0; i < 95; i++) {
                char b = (char) (count);
                key[i] = (b);
                plain[i] = (b);
                count++;
            }
            String randomkey = randomize(key);
            while (KeyFlag == true) {
                System.out.println("*** Monoalphabetic Cipher ***");
                System.out.print("Monoalphabetic Cipher Key: " + randomkey);
                System.out.println();
                System.out.println("1) Generate a New Key");
                System.out.println("2) Enter text to Encrypt");
                System.out.println("3) Enter text to Decrypt");
                System.out.println("q) Quit");
                System.out.print("Enter: ");
                String select = input.nextLine();
                if (select.equals("1")) {
                    randomkey = randomize(key);
                    System.out.println();
                } else if (select.equals("2")) {
                    System.out.print("Text to Encrypt: ");
                    String encrypt = input.nextLine();
                    String result = cipher(encrypt, plain, key);
                    System.out.println("Cipher text: " + result);
                    System.out.println();
                } else if (select.equals("3")) {
                    System.out.print("Text to Decrypt: ");
                    String decrypt = input.nextLine();
                    String result = cipher(decrypt, key, plain);
                    System.out.println("Plaintext: " + result);
                    System.out.println();
                } else if (select.equalsIgnoreCase("Q")) {
                    KeyFlag = false;
                }
            }
        }
    
    
    //Randomize the plain array to make the key
    public static String randomize(char[] list) {
        for (int j = 0; j < list.length; j++) {
            int index = (int) (Math.random() * list.length);
            char temp = list[j];
            list[j] = list[index];
            list[index] = temp;
        }
        String randomKey = "";
        for (int x = 0; x < list.length; x++) {
            randomKey += (list[x]);
        }  
        return randomKey;
    }
    
    public static String cipher(String text, char[] oldArray, char[] newArray) {
        int index = 0;
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            for (int z = 0; z < oldArray.length; z++) {
               if (oldArray[z] == text.charAt(i)) {
                   index = z;
                   result += newArray[index];
               } 
            }
        }
        return result;
    }
}
