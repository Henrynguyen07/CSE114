/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1;

/**
 *
 * @author Henry
 */
import java.util.*;
public class CaesarCipher {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String result = "";
        System.out.println("********* Password Encryption *********");
        System.out.print("Please enter an 8-character password to encrypt: ");
        String encrypt = input.next();
        System.out.print("Please enter your Caesar Key: ");
        int key = input.nextInt();
        if (encrypt.length() != 8) {
            System.out.println("Error - Not an invalid entry");
            System.exit(0);
        }
        if (key > 95) {
            key = key % 95;
        }
        
        for (int i = 0; i < encrypt.length(); i++) {
            if (Character.isUpperCase(encrypt.charAt(i))) {
                char ch = (char) (((int) encrypt.charAt(i)));
                int ascii = (int) ch;
                ascii += key;
                if (ascii > 126) {
                    ascii -= 95;
                }
                char Character = (char) ascii;
                result += Character;
                
            } else {
                char ch = (char) (((int) encrypt.charAt(i)));
                int ascii = (int) ch;
                ascii += key;

                if (ascii > 126) {
                    ascii -= 95;
                }
                char Character = (char) ascii;
                result += Character;
            }
        }
        System.out.println("Using the Caesar Cipher, your Decrypted Password is " + result);
        System.out.println("");
        
//     ************************************************************************
        System.out.println("********* Password Decryption *********");
        System.out.print("Please enter an 8-character password to decrypt: ");
        String decrypt = input.next();
        System.out.print("Please enter your Caesar Key: ");
        int key2 = input.nextInt();
        if (decrypt.length() != 8) {
            System.out.println("Error - Not an invalid entry");
            System.exit(0);
        }
        if (key2 > 95) {
            key2 = key2 % 95;
        }
        String result2 = "";
        for (int i = 0; i < decrypt.length(); i++) {
            if (Character.isUpperCase(decrypt.charAt(i))) {
                char ch = (char) (((int) decrypt.charAt(i)));
                int ascii = (int) ch;
                ascii -= key2;
                if (ascii < 32) {
                    ascii += 95;
                }
                char Character = (char) ascii;
                result2 += Character;

            } else {
                char ch = (char) (((int) decrypt.charAt(i)));
                int ascii = (int) ch;
                ascii -= key2;

                if (ascii < 32) {
                    ascii += 95;
                }
                char Character = (char) ascii;
                result2 += Character;
            }
        }
        System.out.println("Using the Caesar Cipher, your Decrypted Password is " + result2);

        System.out.println();
        System.out.println("Original Plaintext Password: " + encrypt);
        System.out.println("Decrypted Plaintext Password: " + result2);
        if (encrypt.equals(result2)) {
            System.out.println("These two passwords are equivalent");
        } else {
            System.out.println("These two passwords aren't equivalent");
        }
        
        
        
        
    }
}
