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
import java.util.Scanner;

/**
 * This is a rather insipid RPG with a player
 * and three monsters battling it out in a
 * text-based interface. Fun!
 * 
 * @author McKilla Gorilla
 * @author ?
 */
public class WorldOfWarcrap 
{
	// FOR GETTING INPUT FROM THE USER
	static Scanner keyboard;
	
	// AND A SHORTCUT FOR PRINTING
	static PrintStream out;
	
	// FLAG FOR LETTING THE PROGRAM CONTINUE
	static boolean keepAppRunning;
	
	// FLAG FOR LETTING THE GAME CONTINUE
	static boolean keepPlaying;
	
	// HERE IS THE PLAYER
	static RPGCharacter player;
	
	// AND HERE ARE THE ENEMY NPCs WHICH 
	// WE'LL RESPAWN AS SOON AS THEY DIE
	// NPC MEANS NON-PLAYER CHARACTER
	static RPGCharacter npc1;
	static RPGCharacter npc2;
	static RPGCharacter npc3;
	
	/**
	 * Here's where our program starts.
	 */
	public static void main(String[] args)
	{
		// INIT KEYBOARD INPUT STUFF
		keyboard = new Scanner(System.in);
		
		// AND THIS IS FOR DISPLAYING STUFF
		out = System.out;
		
		// WE'LL ASSUME THE USER WANTS TO PLAY NOW
		// BUT WILL CHANGE THIS LATER UPON REQUEST
		keepPlaying = true;
		keepAppRunning = true;

		// WELCOME THE USER
		printWelcomeMessage();
		
		// KEEP THE APP OPEN AS LONG AS THE USER
		// WANTS TO KEEP PLAYING
		while (keepAppRunning)
		{
			// DISPLAY THE MAIN MENU
			displayMainMenu();
			
			// GET INPUT FROM THE USER AND USE IT
			processMainMenuInput();
		}
		
		// SAY GOODBYE TO USER
		printGoodbyeMessage();
	}

	/**
	 * This method should make and returns a new,
	 * randomly selected NPC.
	 */
	public static RPGCharacter respawnNPC(int level)
	{
		// YOU MUST DEFINE THIS METHOD]
            if (level < 1) {
                level = 1;
            }
            String EnemyName = null;
            RPGClass myClass = null;
            int select = (int) (Math.random() * 3);
            switch (select) {
                case 0:
                    EnemyName = "Orc";
                    break;
                case 1:
                    EnemyName = "Tauren";
                    break;
                case 2:
                    EnemyName = "Undead";
                    break;
            }
            select = (int) (Math.random() * 3);
            switch (select) {
                case 0:
                    myClass = RPGClass.MAGE;
                    break;
                case 1:
                    myClass = RPGClass.RANGER;
                    break;
                case 2:
                    myClass = RPGClass.WARRIOR;
                    break;
            }
            
            return new RPGCharacter(EnemyName, myClass, level);
	}

	/**
	 * This helper method simply prints
	 * a welcome message to the user.
	 */
	public static void printWelcomeMessage()
	{
		out.println("*** WELCOME TO THE WORLD OF WARCRAP ***");
	}

	/**
	 * This helper method simply prints 
	 * a goodbye message to the user.
	 */
	public static void printGoodbyeMessage()
	{
		out.println("Thanks for playing the World of Warcrap,"
				+ "remember to pay your subscription bill on time");
	}

	/**
	 * This method uses some randomization to
	 * construct all three NPCs.
	 */
	public static void initializeNPCs()
	{
		npc1 = respawnNPC((int)(Math.random() * (player.getLevel() + 2)));
		npc2 = respawnNPC((int)(Math.random() * (player.getLevel() + 2)));
		npc3 = respawnNPC((int)(Math.random() * (player.getLevel() + 2)));
	}

	/**
	 * This method prints the main menu for the user.
	 */
	public static void displayMainMenu()
	{
		out.println("MAIN MENU");
		out.println("M) Play new game as Mage");
		out.println("R) Play new game as Ranger");
		out.println("W) Play new game as Warrior");
		out.println("X) Exit the World of Warcrap");
		out.print("Selection: ");
	}

	/**
	 * This prints the in game menu, which presents
	 * options to the user while the game is running.
	 */
	public static void displayGameMenu()
	{
		out.println("\nWhat does "
				+ player.getName()
				+ " want to do?");
		out.println("1) Fight a Level " + npc1.getLevel() + " "
				+ npc1.getName() + " " + npc1.getCharacterClass());
		out.println("2) Fight a Level " + npc2.getLevel() + " "
				+ npc2.getName() + " " + npc2.getCharacterClass());
		out.println("3) Fight a Level " + npc3.getLevel() + " "
				+ npc3.getName() + " " + npc3.getCharacterClass());
		out.println("X) Return to the main menu");
		out.print("Selection: ");		
	}

	/**
	 * This gets main menu input from the user,
	 * tests to see what it is, and does work
	 * based on that input.
	 */
	public static void processMainMenuInput()
	{
		// GET USER INPUT
		String input = keyboard.nextLine();
		input = input.toUpperCase().trim();
		RPGClass characterClass;

		// DOES THE USER WANT TO BE A MAGE?
		if (input.equals("M"))
		{
			characterClass = RPGClass.MAGE;
		}
		// OR A RANGER?
		else if (input.equals("R"))
		{
			characterClass = RPGClass.RANGER;
		}
		// OR A WARRIOR?
		else if (input.equals("W"))
		{
			characterClass = RPGClass.WARRIOR;
		}
		// OR QUIT?
		else if (input.equals("X"))
		{
			keepAppRunning = false;
			return;
		}
		else
		{
			// THE USER SCREWED UP
			giveFeedbackForIllegalInput();
			return;
		}
		
		// ASK THE USER FOR THE CHARACTER NAME
		out.print("\nWhat is your character's name? ");
		String name = keyboard.nextLine();

		// AND INITIALIZE THE PLAYER
		player = new RPGCharacter(name, characterClass, 1);
		
		// AND NOW RUN THE GAME
		runGame();
	}

	/**
	 * This gets user input for the game menu
	 * and tests to see what the user wants
	 * to do and then does it.
	 */
	public static void processGameInput()
	{
		// GET USER INPUT
		String input = keyboard.nextLine();
		input = input.toUpperCase().trim();
		
		// FIGHT MONSTER # 1?
		if (input.equals("1"))
		{
			player.fightToTheDeath(npc1);
			displayFightResults(npc1);			
			if (!npc1.isAlive())
				npc1 = respawnNPC((int)(Math.random() * (player.getLevel() + 2)));
		}
		// FIGHT MONSTER # 2?
		else if (input.equals("2"))
		{
			player.fightToTheDeath(npc2);
			displayFightResults(npc2);
			if (!npc2.isAlive())
				npc2 = respawnNPC((int)(Math.random() * (player.getLevel() + 2)));
		}
		// OR FIGHT MONSTER # 3?
		else if (input.equals("3"))
		{
			player.fightToTheDeath(npc3);
			displayFightResults(npc3);			
			if (!npc3.isAlive())
				npc3 = respawnNPC((int)(Math.random() * (player.getLevel() + 2)));
		}
		// OR GO BACK TO THE MAIN MENU?
		else if (input.equals("X"))
		{
			keepPlaying = false;
			return;
		}
		else
		{
			// INVALID INPUT
			giveFeedbackForIllegalInput();
			return;
		}
	}

	/**
	 * This displays a summary of the results
	 * of the fight.
	 */
	public static void displayFightResults(RPGCharacter npc)
	{
		if (player.isAlive())
		{
			out.println("\nYOU WON THE BATTLE!\n");
			out.println("\n" + player + "\n");
			player.rejuvinate();
			out.println("Time to Rest and Rejuvinate\n");
			out.println("Stats after Rejuvination:");
			out.println("\n" + player + "\n");
		}
		else
		{
			out.println("\nYOU ARE VERY DEAD!\n");
			out.println("\nWinner:\n");
			out.println(npc);
			keepPlaying = false;
		}
	}

	/**
	 * This method contains the game loop for
	 * running an individual game.
	 */
	public static void runGame()
	{
		// MAKE THE ENEMIES
		initializeNPCs();

		// START THE GAME
		keepPlaying = true;
		
		// HERE'S OUR GAME LOOP
		while (keepPlaying)
		{
			// PRESENT THE OPTIONS TO THE PLAYER
			displayGameMenu();

			// PROCESS THE USER INPUT
			processGameInput();
		}
	}

	/**
	 * Tell the user they screwed up.
	 */
	public static void giveFeedbackForIllegalInput()
	{
		out.println("\nYou have provided illegal input\n");
	}
}