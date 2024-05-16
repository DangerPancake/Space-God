package MainCode;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.jar.Attributes.Name;

import Dungeons.FirstSpace;
import Dungeons.DungeonTwo;
import Dungeons.DungeonOne;
import PlayerInfo.PlayerStats;

public class MainGame {

    //-------------------------------------------------------------------------------------------------------------------
    //Unlocks
    public static boolean godOfSpaceUnlocked = false; //Unlock Shop
    public static boolean WellspringUnlocked = false; //Unlock Heal
    private int Price1 = 0; 
    private int Price2 = 0;  
    private int AmountBoughtHp = 0;
    private int AmountBoughtMp = 0;

    //Spell Unlocks 
    private int LesserSpellsUnlocked = 0; //make this max 3 so you can only get three, maybe have second set for lesser offensive lesser buff idk
    public static boolean StunboltUnlock = false; 


    //-------------------------------------------------------------------------------------------------------------------

    public static final Random random = new Random();

    // Main method
    public static void main(String[] args) {
        MainGame e = new MainGame();
        e.startGame();
    }

    // Start the game
    public void startGame() {
        Combat.coolDown.add(0);
        Combat.coolDown.add(0);
        Combat.coolDown.add(0);
        Combat.Spells.add("Firebolt");
        Combat.Spells.add("we");
        Combat.Spells.add("pe");
        Combat.spellCosts.add(3);
        Combat.spellCosts.add(5);
        Combat.spellCosts.add(0);
        while (true) {
            Output.clearScreen();
            // Main menu
            Output.slowPrint("Main Menu:\n");
            Output.slowPrint("1. New Game\n");
            Output.slowPrint("2. Load Game\n");
            Output.slowPrint("3. Save Game\n");
            Output.slowPrint("4. Exit\n");
            int choice = Output.getUserChoice(1, 5);
            switch (choice) {
                case 1:
                    startNewGame();
                    break;
                case 2:
                    loadGame();
                    break;
                case 3:
                    saveGame();
                    break;
                case 4:
                    endGame();
                    break;
                case 5:
                    Output.setFast();
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Start a new game
    public void startNewGame() {
        // Reset stats for new game
        PlayerStats.hp = 10;
        PlayerStats.maxHp = 10;
        PlayerStats.mana = 10;
        PlayerStats.maxMana = 10;
        PlayerStats.lifeEssence = 0; //Money kinda
        Output.clearScreen();
        Output.slowPrint("New game started!\n");
        Output.wait(1000);
        Output.slowPrint("Enter your name: ");
        PlayerStats.playerName = Output.scanner.nextLine();
        createCharacter();
        showInGameMenu();
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Save the current game progress
    public void saveGame() {
        Output.clearScreen();
        Output.slowPrint("Select a save slot (1-3): ");
        int slot = Output.getUserChoice(1, 3);
        String fileName = "save" + slot + ".txt";
        File file = new File(fileName);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            // Write character data to file
            writer.println(PlayerStats.playerName);
            writer.println(PlayerStats.hp);
            writer.println(PlayerStats.maxHp);
            writer.println(PlayerStats.mana);
            writer.println(PlayerStats.maxMana);
            writer.println(PlayerStats.lifeEssence);
            writer.println(AmountBoughtHp);
            writer.println(AmountBoughtMp);
            if (godOfSpaceUnlocked)
                writer.println(1);
            else
                writer.println(0);
            if (WellspringUnlocked)
                writer.println(2);
            else
                writer.println(0);
            for (String spell : Combat.Spells) {
                writer.println(spell);
            }
            Output.slowPrint("Game saved to slot " + slot + ".\n");
        } catch (IOException e) {
            Output.slowPrint("Error saving game.\n");
        }
        Output.wait(1000);
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Load a saved game
    public void loadGame() {
        Output.clearScreen();
        Output.slowPrint("Available save slots:\n");
        for (int i = 1; i <= 3; i++) {
            File file = new File("save" + i + ".txt");
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    // Read character data from file
                    Output.slowPrint(i + ". " + reader.readLine() + "'s save slot\n");
                } catch (IOException | NumberFormatException e) {
                    Output.slowPrint("Error loading save slot " + i + ".\n");
                }
            } else {
                Output.slowPrint(i + ". Empty save slot\n");
            }
        }
        int slot = Output.getUserChoice(1, 3);
        String fileName = "save" + slot + ".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Read character data from file
                PlayerStats.playerName = reader.readLine();
                PlayerStats.hp = Integer.parseInt(reader.readLine());
                PlayerStats.maxHp = Integer.parseInt(reader.readLine());
                PlayerStats.mana = Integer.parseInt(reader.readLine());
                PlayerStats.maxMana = Integer.parseInt(reader.readLine());
                PlayerStats.lifeEssence = Integer.parseInt(reader.readLine());
                AmountBoughtHp = Integer.parseInt(reader.readLine());
                AmountBoughtMp = Integer.parseInt(reader.readLine());
                int temp = Integer.parseInt(reader.readLine());// God Unlock
                if (temp == 1) 
                    godOfSpaceUnlocked = true;
                else
                    godOfSpaceUnlocked = false;
                int temp2 = Integer.parseInt(reader.readLine());//wellspring unlock
                if (temp2 == 2) 
                    WellspringUnlocked = true;
                else
                    WellspringUnlocked = false;
                for (int j = 0; j < Combat.Spells.size(); j++) {
                    Combat.Spells.add(reader.readLine());
                }
            } catch (IOException | NumberFormatException e) {
                Output.slowPrint("Error loading save slot.\n");
            }
            Output.slowPrint("Game loaded from " + PlayerStats.playerName + "'s save slot.\n");
            Output.wait(1000);
            showInGameMenu();
        } else {
            Output.slowPrint("Slot " + slot + " is empty. No game loaded.\n");
            Output.wait(1000);
        }
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Display in-game menu
    public void showInGameMenu() {
        while (true) {
            Output.clearScreen();
            // In-game menu
            Output.slowPrint("In-Game Menu:\n");
            Output.slowPrint("1. Explore\n");
            Output.slowPrint("2. Stats\n");
            if (godOfSpaceUnlocked) {
                Output.slowPrint("3. Sanctum Of Reality\n");//Shop pretty much
            } else {
                Output.slowPrint("3. Locked\n");
            }
            if (WellspringUnlocked) {
                Output.slowPrint("4. Aether Well\n");//heal, restore mana & resetcooldowns, maybe change later good for now
            } else {
                Output.slowPrint("4. Locked\n");
            }
            Output.slowPrint("5. Exit to Main Menu\n");
            int choice = Output.getUserChoice(1, 5);
            switch (choice) {
                case 1:
                    exploreDungeon();
                    break;
                case 2:
                    showStats();
                    break;
                case 3:
                    if (godOfSpaceUnlocked) {
                        SanctumOfReality();
                    } else {
                        Output.slowPrint("You don't dare go near such a dangerous place\n"); 
                    }
                    Output.wait(1000);
                    break;
                case 4:
                if (WellspringUnlocked) {
                    Wellspring();
                } else {
                    Output.slowPrint("You don't dare go near such a dangerous place\n");
                }
                    Output.wait(1000);
                    break;
                case 5:
                    return; // Return to main menu
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Show player stats
    public void showStats() {
        Output.clearScreen();
        // Player stats
        Output.slowPrint("Player Stats:\n");
        Output.slowPrint("Name: " + PlayerStats.playerName + "\n");
        Output.slowPrint("HP: "+ Color.ANSI_RED + PlayerStats.hp + Color.ANSI_RESET + "/" + Color.ANSI_RED + PlayerStats.maxHp + Color.ANSI_RESET + "\n");
        Output.slowPrint("MP: " + Color.ANSI_BLUE + PlayerStats.mana + Color.ANSI_RESET + "/" + Color.ANSI_BLUE + PlayerStats.maxMana + Color.ANSI_RESET + "\n");
        Output.slowPrint("Life Essence: " + Color.ANSI_GREEN + PlayerStats.lifeEssence + Color.ANSI_RESET + "\n");
        Output.slowPrint("Spells:\n");
        for (int i = 0; i < Combat.Spells.size(); i++) {
            Output.slowPrint(Combat.Spells.get(i) + " (Mana Cost: " + Combat.spellCosts.get(i) + ")\n");
        }
        Output.slowPrint("Press any key to continue...\n");
        Output.scanner.nextLine(); // Output.wait for any key press
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Explore dungeons
    public void exploreDungeon() {
        Output.clearScreen();
        // Available dungeons sorted by difficulty
        Output.slowPrint("Available Dungeons:\n");
        Output.slowPrint("1. Crumbling Ruins\n"); // maybe improve lore/name
        Output.slowPrint("2. DungeonTwo\n"); 
        Output.slowPrint("3. The First Trial\n");

        int choice = Output.getUserChoice(1, 3);
        switch (choice) {
            case 1:
                new DungeonOne().explore();
                break;
            case 2:
                new DungeonTwo().explore(); // was extra ; if that somehow changes anything
                break;
            case 3:
                new FirstSpace().explore(); 
                break;
        }
    }

    //-------------------------------------------------------------------------------------------------------------------

    // End the game
    public void endGame() {
        Output.slowPrint("\033[H\033[2J"); // Clear the screen
        Output.slowPrint("Thank you for playing!\n");
        System.exit(0);
    }

    // Character creation process
    public void createCharacter() {
        Output.slowPrint("Character creation...\n");
        Output.wait(2000);
        Output.slowPrint("Character created!\n");
        Output.wait(1000);
        Output.clearScreen();
        IntroLore();
    
    }

    public void IntroLore(){//intro lore improve perhaps. 
        Output.slowPrint("You, " + PlayerStats.playerName + ", are a warlock. Well, you aspire to be a warlock, at least.");
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("However, you can't seem to find a higher being that's willing to form a pact with you."); 
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("Even lesser demons don't dare to waste their time on you."); 
        Output.wait(3000);
        Output.clearScreen();
        Output.slowPrint("Who can blame them? When even the average child surpasses you in magic skill, you seem quite unappealing."); 
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("One day, with nothing better to do, you set out to explore the dungeons of the world."); 
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("Perhaps one day you'll find some kind of " + Color.ANSI_PURPLE + "power " + Color.ANSI_RESET + "to call your own."); 
        Output.wait(5500);
    }

    //-------------------------------------------------------------------------------------------------------------------
    public void Wellspring() {
        Output.clearScreen();
        Output.slowPrint(Color.ANSI_BLUE + "You bathe in the ather pool\n" + Color.ANSI_RESET);
        PlayerStats.hp = PlayerStats.maxHp;
        PlayerStats.mana = PlayerStats.maxMana;
        for (int cool = 0; cool < Combat.Spells.size(); cool++ ) {
            Combat.coolDown.set(cool,0);
        }
        

        Output.wait(1000);
    }

    //-------------------------------------------------------------------------------------------------------------------

    // God Shop
    public void SanctumOfReality() {

        Price1 = AmountBoughtHp + 5;
        Price2 = AmountBoughtMp + 5;

        Output.clearScreen();
        Output.slowPrint(Color.ANSI_PURPLE + "Hello Miniscule Creature!\n" + Color.ANSI_RESET);
        Output.slowPrint(Color.ANSI_PURPLE + "Do you wish to sacrifice the energy of any souls?\n" + Color.ANSI_RESET);
        Output.slowPrint("Current LifeEssence: " + Color.ANSI_GREEN + PlayerStats.lifeEssence + Color.ANSI_RESET + "\n");
        Output.slowPrint("\n");
        Output.slowPrint("1. Life Essence Absorbtion " + Color.ANSI_GREEN + Price1 + Color.ANSI_RESET + "\n");
        Output.slowPrint("2. Life Essence Conversion " + Color.ANSI_GREEN + Price2 + Color.ANSI_RESET + "\n");
        Output.slowPrint("3. Random Lesser Spell\n");//rename
        Output.slowPrint("4. Exit The Sactum\n");

        int choice = Output.getUserChoice(1, 3);
        switch (choice) {
            case 1:
                buyItem("HP");
                break;
            case 2:
                buyItem("Mana");
                break;
            case 3:
                System.out.println(Combat.Spells);
                Output.wait(1000000);
                buyItem("Lesser Spell");
                
                break;
            case 4:
                // Exit the shop
                Output.slowPrint("Exiting the Sanctum Of Reality...\n");
                Output.wait(1000);
                return;
        }
    }

    // Buy items from the God Shop
    public void buyItem(String item) {
        if (PlayerStats.lifeEssence >= 0) {
            switch (item) {
                case "HP":
                if (PlayerStats.lifeEssence >= Price1)
                PlayerStats.maxHp += 5;
                PlayerStats.hp = PlayerStats.maxHp;
                PlayerStats.lifeEssence-= Price1;
                    AmountBoughtHp += 5;
                    Output.slowPrint("Maximum HP increased by 5! Your lifeforce grows stronger!\n");
                    break;
                case "Mana":
                if (PlayerStats.lifeEssence >= Price2)
                PlayerStats.maxMana += 5;
                PlayerStats.mana = PlayerStats.maxMana;
                PlayerStats.lifeEssence-= Price2;
                    AmountBoughtMp += 5;
                    Output.slowPrint("Maximum Mana increased by 5! Your soul grows stronger!\n");
                    break;
                case "LesserSpell"://make this so you can only unlock 3 lesser spells
                if (PlayerStats.lifeEssence >= 1)
                if (LesserSpellsUnlocked < 3){
                LesserSpellsUnlocked ++; 
                RandomLesserSpell();
                } else {
                    Output.slowPrint("You cannot learn more spells of this kind\n");// maybe rewrite
                    break;
                }
            }
             // Reduce life essence (cost of purchasing)
            Output.slowPrint("Remaining Life Essence: " + Color.ANSI_GREEN + PlayerStats.lifeEssence + Color.ANSI_RESET + "\n");
        } else {
            Output.slowPrint("You don't have enough Life Essence!\n");
        }
    }
    //-------------------------------------------------------------------------------------------------------------------
    public void RandomLesserSpell(){//make a bunch of lesser spells but you can only unlock 3.   

        int max1 = 4;
        int min1 = 1;
        int range1 = max1 - min1 + 1; 
        int randSpell1 = (int)(Math.random() * range1) + min1;

        if (randSpell1 == 1) {
            if(Combat.Spells.indexOf("Shockbolt") == -1) {
                Combat.Spells.add("Shockbolt");
                Combat.spellCosts.add(5);
                Combat.coolDown.add(0);
                System.out.println(Combat.Spells);
                Output.wait(1000000);
            } else {
                RandomLesserSpell();
            }

        }else if (randSpell1 == 2){
            RandomLesserSpell();
        //second spell unlock
        } else if (randSpell1 == 3){
            RandomLesserSpell();
        //third spell unlock
        } else if (randSpell1 == 4){
            RandomLesserSpell();
        //fourth spell unlock
        }
        

        }
        }


        //posion spell
        //Better firebolt spell


    //-------------------------------------------------------------------------------------------------------------------





//Uroxx, Ruler Of the Void