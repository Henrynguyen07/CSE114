/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RPG;

/**
 *
 * Author: Henry Nguyen
 * SBU ID: 111484010
 * Net ID; Henguyen
 * CSE 114
 */
import java.io.PrintStream;

/**
 * This class is for testing the RPGCharacter class.
 * You may make changes as you see fit to change existing
 * tests or add new ones.
 * 
 * @author Richard Mckenna
 * @author ?
 */

public class RPGCharacterDriver 
{
	// THIS IS A SHORTCUT FOR PRINTING TO THE CONSOLE
	static PrintStream out = System.out;

	// HERE ARE OUR CHARACTERS
	static RPGCharacter gimli;
	static RPGCharacter legolas;

	/**
	 * Test program starts here
	 */
	public static void main(String[] args)
	{
		// INIT BOTH CHARACTERS
		initAllRPGCharacters();
		
		// PRINT START MESSAGE
		out.println("TESTING SINGLE ATTACK BATTLE SYSTEM\n");
		
		// DISPLAY INITIAL STATES OF ALL CHARACTERS
		// IS THE DATA PROPERLY FORMATTED?
		out.println("Initial Character States:");
		printAllRPGCharacters();
	
		// LET'S HAVE GIMLI ATTACK LEGOLAS UNTIL
		// HE INFLICTS DAMAGE
		int damage = 0;
		while (damage == 0)
		{
			out.println("Gimli attacks Legolas");
			damage = gimli.attack(legolas);
		}
		out.println("Post Battle Stats:");
		printAllRPGCharacters();
		out.println("\nDamage inflicted: " 
				+ damage + " hps\n");
		
		// NOW LET'S TEST REJUVINATE
		out.println("\nTESTING REJUVINATE FOR LEGOLAS");
		legolas.rejuvinate();
		out.println("\nAfter Rejuvinate:");
		out.println(legolas);
		
		// NOW LET'S HAVE A FIGHT TO THE DEATH
		initAllRPGCharacters();
		out.println("\nTESTING BATTLE TO DEATH");
		gimli.fightToTheDeath(legolas);
		
		// WHO WON?
		if (gimli.isAlive())
			System.out.println("Gimli killed Legolas");
		else
			System.out.println("Legolas killed Gimli");
		
		// POST FIGHT STATS
		System.out.println("POST BATTLE STATS:");
		printAllRPGCharacters();
		
		// NOW LET'S PLAY WITH SOME METHODS
		initAllRPGCharacters();
		out.println("\nTESTING incLevel");
		out.println("Initial Stats for Legolas");
		out.println(legolas);
		
		// LET'S INC A LEVEL
		legolas.incLevel();
		
		// POST incLevel STATS
		out.println("Post incLevel");
		out.println(legolas);
		
		// LET'S TRY OUT ALL THE ACCESSOR
		// METHODS INDIVIDUALLY
		System.out.println("\nTESTING ACCESSOR METHODS");
		out.println("Name: " + legolas.getName());
		out.println("Class: " + legolas.getCharacterClass());
		out.println("Level: " + legolas.getLevel());
		out.println("HP: " + legolas.getHP());
		out.println("XP: " + legolas.getXP());
		out.println("Armor: " + legolas.getArmor());
		out.println("Damage: " + legolas.getDamage());
	}

	/**
	 * This method initializes gimli and legolas.
	 */
	public static void initAllRPGCharacters()
	{
		// ONLY ONE CONSTRUCTOR WE CAN USE
		gimli = new RPGCharacter("Gimli", RPGClass.WARRIOR, 1);
		legolas = new RPGCharacter("Legolas", RPGClass.RANGER, 2);		
	}

	/**
	 * This method prints the stats for the
	 * gimli and legolas.
	 */
	public static void printAllRPGCharacters()
	{
		// LET'S DISPLAY THEIR INITIAL STATS
		// NOTE, println CALLS toString
		out.println(gimli);
		out.println(legolas);	
	}
}