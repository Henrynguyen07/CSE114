package part1;
import java.util.*;

/**
 *
 * @author henguyen
 * SBU ID: 111484010
 */
public class InterestCalculator {
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("**************************************************************");
        System.out.println(" ***** WELCOME TO HENRY NGUYEN'S INVESTMENT CALCULATOR ******");
        System.out.println("**************************************************************");
        System.out.print("Enter the Principal Value: $");
        int Principal = sc.nextInt();
        if (Principal <= 0){
            System.out.println("Error - the Principal must be greater than 0");
            System.exit(0);
        }
        System.out.print("Enter the Interest Rate (0.0-1.0): ");
        double Interest = sc.nextDouble();
        if (Interest > 1 || Interest < 0){
            System.out.println("Error - the Interest Rate must be a number from 0.0 to 1.0");
            System.exit(0);
        }
        System.out.print("Enter the year in the future: ");
        int Year = sc.nextInt();
        if (Year - 2018 > 100){
            System.out.println("Error - The year must be a whole number in the not too distance future");
            System.exit(0);
        }
        int Year2 = Year - 2018;
        double TotalValue = Math.pow((1 + Interest), Year2);
        TotalValue =  TotalValue * Principal;
        System.out.println("Using an Interest Rate of " + Interest * 100 + "%, in " + Year + " your principal value of $" + Principal + " will have grown to be worth $" +Math.round(TotalValue));
    }
}
