package part2;

/**
 *
 * Author: Henry Nguyen SBU ID: 111484010 Net ID; Henguyen CSE 114
 */
import java.util.*;
import java.util.Random;

public class Password_Tester_Generator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean execute = true;
        while (execute == true) {
            System.out.println("***** WELCOME TO HENRY NGUYEN'S PASSWORD TESTER & GENERATOR *****");
            System.out.println("PLEASE SELECT FROM ONE OF THE MENU OPTIONS BELOW");
            System.out.println("1) Test one of your passwords");
            System.out.println("2) Generate a random password");
            System.out.println("Q) Quit");
            System.out.println();
            System.out.print("Selection: ");
            String selection = input.next();
            System.out.println("");
            if (selection.equalsIgnoreCase("Q")) {
                System.out.println("Qutting... Good-Bye");
                execute = false;
            }
            switch (selection) {
                case "1":
                    Scanner input1 = new Scanner(System.in);
                    boolean UpperFlag = false;
                    boolean LowerFlag = false;
                    boolean DigitFlag = false;
                    boolean SpecialFlag = false;
                    boolean LengthFlag = false;
                    boolean SpaceFlag = false;
                    System.out.print("Enter the password to test: ");
                    String password = input1.nextLine();
                    for (int i = 0; i < password.length(); i++) {
                        char ch = password.charAt(i);
                        if (password.length() >= 8) {
                            LengthFlag = true;
                        }
                        if (Character.isUpperCase(ch)) {
                            UpperFlag = true;
                        } else if (Character.isLowerCase(ch)) {
                            LowerFlag = true;
                        } else if (Character.isDigit(ch)) {
                            DigitFlag = true;
                        } else if (Character.isWhitespace(ch)) {
                            SpaceFlag = true;
                        } else if (!Character.isLetterOrDigit(ch)) {
                            SpecialFlag = true;
                        }
                    }
                    if (LengthFlag == false) {
                        System.out.println("NOT A GOOD PASSWORD - Must be at least 8 characters long");
                    }
                    if (UpperFlag == false) {
                        System.out.println("NOT A GOOD PASSWORD - No Upper-Case Characters");
                    }
                    if (LowerFlag == false) {
                        System.out.println("NOT A GOOD PASSWORD - No Lower-Case Characters");
                    }
                    if (DigitFlag == false) {
                        System.out.println("NOT A GOOD PASSWORD - No Digit Characters");
                    }
                    if (SpecialFlag == false) {
                        System.out.println("NOT A GOOD PASSWORD - No Symbol Characters");
                    }
                    if (SpaceFlag == true) {
                        System.out.println("NOT A GOOD PASSWORD - No Space Allowed");
                    }
                    if ((UpperFlag && LowerFlag && DigitFlag && SpecialFlag && LengthFlag) == true && SpaceFlag == false) {
                        System.out.println(password + " is a good password, you should use it");
                    }
                    System.out.println();
                    break;
                case "2":
                    Random rand = new Random();
                    int max = 126;
                    int min = 32;
                    String generated = "";
                    boolean CheckFlag = true;
                    while (CheckFlag == true) {
                        while (generated.length() < 8) {
                            char c = (char) (rand.nextInt((max - min) + 1) + min);
                            Character.toString(c);
                            generated += c;
                        }
                        boolean UpperFlag2 = false;
                        boolean LowerFlag2 = false;
                        boolean DigitFlag2 = false;
                        boolean SpecialFlag2 = false;
                        boolean LengthFlag2 = false;
                        boolean SpaceFlag2 = false;
                        for (int i = 0; i < generated.length(); i++) {
                            char ch = generated.charAt(i);
                            if (Character.isUpperCase(ch)) {
                                UpperFlag2 = true;
                            } else if (Character.isLowerCase(ch)) {
                                LowerFlag2 = true;
                            } else if (Character.isDigit(ch)) {
                                DigitFlag2 = true;
                            } else if (!Character.isLetterOrDigit(ch)) {
                                SpecialFlag2 = true;
                            }
                        }
                        if ((UpperFlag2 && LowerFlag2 && DigitFlag2 && SpecialFlag2) == true) {
                            CheckFlag = false;
                        } else {
                            generated = "";
                        }
                    }
                    System.out.println("Password Generated: " + generated);
                    System.out.println();
                    break;
            }
        }
    }
}
