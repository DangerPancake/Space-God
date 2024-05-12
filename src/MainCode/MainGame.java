package MainCode;
import java.io.*;
import java.util.Random;

import Dungeons.FirstSpace;
import Dungeons.beginnersDungeon;
import PlayerInfo.PlayerStats;

public class MainGame {

    //-------------------------------------------------------------------------------------------------------------------
    //Unlocks
    public static boolean godOfSpaceUnlocked = false;
    public static boolean WellspringUnlocked = false;
    private int Price1 = 0; 
    private int Price2 = 0; 
    private int Price3 = 0; 
    private int AmountBoughtHp = 0;
    private int AmountBoughtMp = 0;
    //-------------------------------------------------------------------------------------------------------------------

    public static final Random random = new Random();

    // Main method
    public static void main(String[] args) {
        MainGame e = new MainGame();
        e.startGame();
    }

    // Start the game
    public void startGame() {
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
            for (String spell : Combat.spells) {
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
                Combat.spells = new String[4];
                for (int j = 0; j < Combat.spells.length; j++) {
                    Combat.spells[j] = reader.readLine();
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
                Output.slowPrint("3. Sanctum Of Reality\n");
            } else {
                Output.slowPrint("3. Something Is Missing\n");
            }
            if (WellspringUnlocked) {
                Output.slowPrint("4. Wellspring Of Souls\n");
            } else {
                Output.slowPrint("4. Something is Missing\n");
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
                        Output.slowPrint("You haven't unlocked this option yet.\n"); 
                    }
                    Output.wait(1000);
                    break;
                case 4:
                if (WellspringUnlocked) {
                    Output.slowPrint("LORE LORE LORE LORE \n");
                    PlayerStats.hp = PlayerStats.maxHp;
                    PlayerStats.mana = PlayerStats.maxMana;
                } else {
                    Output.slowPrint("You haven't unlocked this option yet.\n");
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
        for (int i = 0; i < Combat.spells.length; i++) {
            Output.slowPrint(Combat.spells[i] + " (Mana Cost: " + Combat.spellCosts[i] + ")\n");
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
        Output.slowPrint("1. Beginner's Dungeon\n");
        Output.slowPrint("2. 1st Trial of the God of Space\n");
        int choice = Output.getUserChoice(1, 2);
        switch (choice) {
            case 1:
                new beginnersDungeon().explore();
                break;
            case 2:
                new FirstSpace().explore();;
                break;
        }
    }

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
    }

    // God of Space Shop
    public void SanctumOfReality() {

        Price1 = AmountBoughtHp + 5;
        Price2 = AmountBoughtMp + 5;

        Output.clearScreen();
        Output.slowPrint(Color.ANSI_PURPLE + "Hello Miniscule Creature!\n" + Color.ANSI_RESET);
        Output.slowPrint(Color.ANSI_PURPLE + "Do you wish to sacrifice the energy of any souls?\n" + Color.ANSI_RESET);
        Output.slowPrint("Current LifeEssence: " + Color.ANSI_GREEN + PlayerStats.lifeEssence + Color.ANSI_RESET + "\n");
        Output.slowPrint("\n");
        Output.slowPrint("1. Life Essence Absorbtion " + Color.ANSI_GREEN + Price1 + Color.ANSI_RESET + " LE\n");
        Output.slowPrint("2. Life Essence Conversion " + Color.ANSI_GREEN + Price2 + Color.ANSI_RESET + " LE\n");
        Output.slowPrint("3. Exit The Sactum\n");

        int choice = Output.getUserChoice(1, 3);
        switch (choice) {
            case 1:
                buyItem("HP");
                break;
            case 2:
                buyItem("Mana");
                break;
            case 3:
                // Exit the shop
                Output.slowPrint("Exiting the Sanctum Of Reality...\n");
                Output.wait(1000);
                return;
        }
    }

    // Buy items from the God of Space Shop
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
            }
             // Reduce life essence (cost of purchasing)
            Output.slowPrint("Remaining Life Essence: " + Color.ANSI_GREEN + PlayerStats.lifeEssence + Color.ANSI_RESET + "\n");
        } else {
            Output.slowPrint("You don't have enough Life Essence!\n");
        }
    }
}

//Uroxx, Ruler Of the Void