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
enum RPGClass { MAGE, RANGER, WARRIOR };

public class RPGCharacter {
    // Instance Variables
    private RPGClass myClass;
    private String Orc;
    private String Tauren;
    private String Undead;
    private String name;
    private int Level;
    private int XP;
    private int HP;
    private double Armor;
    private int Damage;
    
    // Constructor Class
    public RPGCharacter(String name, RPGClass characterClass, int Level){
        this.name = name;
        this.Level = Level;
        this.XP = Level * 10;
        this.HP = Level * 100;
        this.myClass = characterClass;
        if (myClass == RPGClass.MAGE) {
            Armor = 25;
            Damage = 100;
        } else if (myClass == RPGClass.RANGER) {
            Armor = 50;
            Damage = 66;
        } else if (myClass == RPGClass.WARRIOR) {
            Armor = 75;
            Damage = 33;
        }
    }
    
    // Getter Methods
    public String getName() {
        return name;
    }
    public RPGClass getCharacterClass() {
        return myClass;
    }
    public int getLevel() {
        return Level;
    }
    public int getXP() {
        return XP;
    }
    public int getHP() {
        return HP;
    }
    public double getArmor() {
        return Armor;
    }
    public int getDamage() {
        return Damage;
    }
    
    // Setter Methods
    public void setName(String Name) {
        name = Name;
    }
    public void setCharacterClass(RPGClass CharacterClass) {
        myClass = CharacterClass;
    }
    public void setLevel(int Lvl) {
        Level = Lvl;
    }
    public void setXP(int EXP) {
        XP = EXP;
    }
    public void setHP(int Health) {
        HP = Health;
    }
    public void setArmor(double Arm) {
        Armor = Arm;
    }
    public void setDamage(int Dmg) {
        Damage = Dmg;
    }
    
    public void incLevel() {
        double newArmor = Armor;
        this.setLevel(this.getLevel() + 1);
        this.setHP(this.getLevel() * 100);
        if (myClass.equals(RPGClass.MAGE)) {
            this.setDamage(this.getDamage() + 4);
            if (this.getArmor() < 40) {
                this.setArmor(this.getArmor() + 1);
            }
        } else if (myClass.equals(RPGClass.RANGER)) {
            this.setDamage(this.getDamage() + 2);
            if (this.getArmor() < 65) {
                this.setArmor(this.getArmor() + 1);
            }
        } else if (myClass.equals(RPGClass.WARRIOR))  {
            this.setDamage(this.getDamage() + 1);
            if (this.getArmor() < 90) {
                this.setArmor(this.getArmor() + 1);
            }
        }
    }
    public void fightToTheDeath(RPGCharacter EnemyChar) {
        while (this.isAlive() && EnemyChar.isAlive()) {
            this.attack(EnemyChar);
            EnemyChar.attack(this);
        }
        if (this.getXP() >= ((this.getLevel() * 10) + 10)) {
            this.incLevel();
        }
    }
    public int attack(RPGCharacter character) {
        double chance = Math.random();
        double hitrate = (double) (this.getXP())/(character.getXP() + this.getXP());
        int attack = 0;
        if (hitrate > chance) {
            attack = (int) ((3 * Math.random() + 1)  * this.getDamage() * (double)(character.getArmor() / 100));
            character.setHP(character.getHP() - attack);
            if (character.getHP() < 1) {
                if (character.getLevel() > this.getLevel()) {
                    this.setXP(this.getXP() + 6);
                } else if (character.getLevel() < this.getLevel()) {
                    this.setXP(this.getXP() + 2);
                } else if (character.getLevel() == this.getLevel()) {
                    this.setXP(this.getXP() + 4);
                }
            }
        }
        return attack;
    }
    public boolean isAlive() {
        // Method Checks if Character is Still Alive
        return (this.getHP() > 0);
    }   
    public void rejuvinate () {
        this.setHP(this.getLevel() * 100);
    }
    
    public String toString() {
        return name + " " + "(" + "Level " + Level + " " + myClass.toString() +")" + "\n" + 
                "\t" + "XP" + ":" + " " + XP + "\n" +
                "\t" + "HP" + ":" + " " + HP + "\n" +
                "\t" + "Armor" + ":" + " " + (int)Armor + "\n" +
                "\t" + "Damage" + ":" + " " + Damage + "\n";
        
    }
}