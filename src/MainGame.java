import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class MainGame {
    // Initialize scanner for user input
    private static final Scanner scanner = new Scanner(System.in);
    //-------------------------------------------------------------------------------------------------------------------

    // Player attributes
    private String playerName;
    private int hp = 10;
    private int maxHp = 10;
    private int mana = 10; 
    private int maxMana = 10;
    private int lifeEssence = 0;

    //-------------------------------------------------------------------------------------------------------------------

    //Unlocks
    private boolean godOfSpaceUnlocked = false;
    private int Price1 = 0; 
    private int Price2 = 0; 
    private int Price3 = 0; 
    private int AmountBoughtHp = 0;
    private int AmountBoughtMp = 0;
    //-------------------------------------------------------------------------------------------------------------------

    // Spells
    private String[] spells = {"Firebolt", "Healing Light", "Lesser Focus"};
    private int[] spellCosts = {3, 5, 0};

    //-------------------------------------------------------------------------------------------------------------------

    private static final Random random = new Random();
    private boolean goFast = false;

    // Main method
    public static void main(String[] args) {
        MainGame e = new MainGame();
        e.startGame();
    }

    // Start the game
    public void startGame() {
        while (true) {
            clearScreen();
            // Main menu
            slowPrint("Main Menu:\n");
            slowPrint("1. New Game\n");
            slowPrint("2. Load Game\n");
            slowPrint("3. Save Game\n");
            slowPrint("4. Exit\n");
            int choice = getUserChoice(1, 5);
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
                    goFast = true;
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Start a new game
    public void startNewGame() {
        // Reset stats for new game
        hp = 10;
        maxHp = 10;
        mana = 10;
        maxMana = 10;
        lifeEssence = 0; //Money kinda
        clearScreen();
        slowPrint("New game started!\n");
        wait(1000);
        slowPrint("Enter your name: ");
        playerName = scanner.nextLine();
        createCharacter();
        showInGameMenu();
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Save the current game progress
    public void saveGame() {
        clearScreen();
        slowPrint("Select a save slot (1-3): ");
        int slot = getUserChoice(1, 3);
        String fileName = "save" + slot + ".txt";
        File file = new File(fileName);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            // Write character data to file
            writer.println(playerName);
            writer.println(hp);
            writer.println(maxHp);
            writer.println(mana);
            writer.println(maxMana);
            writer.println(lifeEssence);
            writer.println(AmountBoughtHp);
            writer.println(AmountBoughtMp);
            if (godOfSpaceUnlocked)
                writer.println(1);
            else
                writer.println(0);
            for (String spell : spells) {
                writer.println(spell);
            }
            slowPrint("Game saved to slot " + slot + ".\n");
        } catch (IOException e) {
            slowPrint("Error saving game.\n");
        }
        wait(1000);
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Load a saved game
    public void loadGame() {
        clearScreen();
        slowPrint("Available save slots:\n");
        for (int i = 1; i <= 3; i++) {
            File file = new File("save" + i + ".txt");
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    // Read character data from file
                    slowPrint(i + ". " + reader.readLine() + "'s save slot\n");
                } catch (IOException | NumberFormatException e) {
                    slowPrint("Error loading save slot " + i + ".\n");
                }
            } else {
                slowPrint(i + ". Empty save slot\n");
            }
        }
        int slot = getUserChoice(1, 3);
        String fileName = "save" + slot + ".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Read character data from file
                playerName = reader.readLine();
                hp = Integer.parseInt(reader.readLine());
                maxHp = Integer.parseInt(reader.readLine());
                mana = Integer.parseInt(reader.readLine());
                maxMana = Integer.parseInt(reader.readLine());
                lifeEssence = Integer.parseInt(reader.readLine());
                AmountBoughtHp = Integer.parseInt(reader.readLine());
                AmountBoughtMp = Integer.parseInt(reader.readLine());
                int temp = Integer.parseInt(reader.readLine());
                if (temp == 1) 
                    godOfSpaceUnlocked = true;
                else
                    godOfSpaceUnlocked = false;
                spells = new String[3];
                for (int j = 0; j < spells.length; j++) {
                    spells[j] = reader.readLine();
                }
            } catch (IOException | NumberFormatException e) {
                slowPrint("Error loading save slot.\n");
            }
            slowPrint("Game loaded from " + playerName + "'s save slot.\n");
            wait(1000);
            showInGameMenu();
        } else {
            slowPrint("Slot " + slot + " is empty. No game loaded.\n");
            wait(1000);
        }
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Display in-game menu
    public void showInGameMenu() {
        while (true) {
            System.out.println("ttestin");
            clearScreen();
            // In-game menu
            slowPrint("In-Game Menu:\n");
            slowPrint("1. Explore\n");
            slowPrint("2. Stats\n");
            if (godOfSpaceUnlocked) {
                slowPrint("3. SanctumOfReality\n");
            } else {
                slowPrint("3. Something Is Missing\n");
            }
            slowPrint("4. Tavern\n");
            slowPrint("5. Exit to Main Menu\n");
            int choice = getUserChoice(1, 5);
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
                        slowPrint("You haven't unlocked this option yet.\n");
                    }
                    wait(1000);
                    break;
                case 4:
                    slowPrint("You visit the tavern and are feeling much better! \n");
                    hp = maxHp;
                    mana = maxMana;
                    wait(1000);
                    break;
                case 5:
                    return; // Return to main menu
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Show player stats
    public void showStats() {
        clearScreen();
        // Player stats
        slowPrint("Player Stats:\n");
        slowPrint("Name: " + playerName + "\n");
        slowPrint("HP: " + hp + "/" + maxHp + "\n");
        slowPrint("Mana: " + mana + "/" + maxMana + "\n");
        slowPrint("Life Essence: " + lifeEssence + "\n");
        slowPrint("Spells:\n");
        for (int i = 0; i < spells.length; i++) {
            slowPrint(spells[i] + " (Mana Cost: " + spellCosts[i] + ")\n");
        }
        slowPrint("Press any key to continue...\n");
        scanner.nextLine(); // Wait for any key press
    }
    //-------------------------------------------------------------------------------------------------------------------

    // Explore dungeons
    public void exploreDungeon() {
        clearScreen();
        // Available dungeons sorted by difficulty
        slowPrint("Available Dungeons:\n");
        slowPrint("1. Beginner's Dungeon\n");
        slowPrint("2. 1st Trial of the God of Space\n");
        int choice = getUserChoice(1, 2);

        ArrayList<Object> dungeon = parseDungeonInfo(loadDungeonInfo(choice));
        for (int i = 1; i <dungeon.size(); i++) {
            //monster rooms
            if (((Room)dungeon.get(i)).getType() == 1) {
                runCombat(((Room)dungeon.get(i)).getMonster());
            //text rooms (wip)
            } else if (((Room)dungeon.get(i)).getType() == 2) {
                slowPrint(((Room)dungeon.get(i)).getDescription());
                wait(2000);
            }

            //extra unlocks and such
            if (((ArrayList<String>) dungeon.get(0)).get(0).equals("1st Trial of the God of Space") && i == dungeon.size()-1) {
                godOfSpaceUnlocked = true;
                slowPrint("You feel a connection with the God of Space...\n");
                slowPrint("The option to pray to the God of Space is now unlocked!\n");
                wait(2000);
            }
        }
        
    }

    //-------------------------------------------------------------------------------------------------------------------
    
    // Cast a spell during combat
    public int castSpell(Monster monster) {
    // Print encounter message
    clearScreen();
    System.out.print("You are being attacked by a " + monster.getName() + "!\n");
    System.out.print("-----------------------------------------\n");

    // Print player stats
    System.out.print("Your HP: " + hp + "/" + maxHp + "\n");
    System.out.print("Your Mana: " + mana + "/" + maxMana + "\n");
    System.out.print("-----------------------------------------\n");

    // Print available spells
    System.out.print("Available Spells:\n");
    for (int i = 0; i < spells.length; i++) {
        System.out.print((i + 1) + ". " + spells[i] + " (Mana Cost: " + spellCosts[i] + ")\n");
    }

    // Prompt for spell selection
    slowPrint("Select a spell to cast (1-" + spells.length + "): ");
    int choice = getUserChoice(1, spells.length);

    clearScreen(); // Clear the screen after selecting spells

    if (mana >= spellCosts[choice - 1]) {
        // Cast the spell
        slowPrint("You cast " + spells[choice - 1] + "!\n");
        if (choice == 1) { //firebolt
            mana -= spellCosts[choice - 1];
            int damage = random.nextInt(3) + 1; // Random damage between 1 and 3
            slowPrint("You dealt " + damage + " damage to " + monster.getName() + "!\n");
            wait(2000);
            clearScreen();
            return damage;
        } else if (choice == 2 ) { //healing light
           mana -= spellCosts[choice - 1];
           slowPrint("For a moment a warm light covers your skin, you heal 5 hp!\n");
           hp = hp + 5; //gain 5 hp 
           if (hp > maxHp) {
            hp = maxHp; //make sure hp does not overflow
            wait(2000);
            clearScreen();
           } 
           return 0;
        } else if (choice == 3) { //lesser focus
            mana += maxMana * 0.2; // Restore 20% of max mana with Lesser Focus
            slowPrint("You regained some mana!\n");
            if ( mana > maxMana) {
                mana = maxMana; //make sure mana does not overflow
                wait(2000);
                clearScreen();
            }
            return 0;
        }
    } else {
        slowPrint("Not enough mana to cast this spell!\n");
    }
    wait(1000);
    return 0;
}

   // Monster attacks during combat
    public void monsterAttack(Monster monster) {
        slowPrint(monster.getName() + " attacked you and dealt " + monster.getDamage() + " damage!");
        wait(1000);
        clearScreen();
        hp -= monster.getDamage();
        if (hp <= 0) {
            System.out.println("You were defeated by " + monster.getName() + "!");
            System.out.println("You Have Died");
            // Revive the player with life essence
            if (lifeEssence >= 0) {
                slowPrint("As you lay dead, all your gathered life essence bursts fourth into the heavens as tribute, an unknown power reaches out, you are revived");
                hp = maxHp;
                lifeEssence = 0; // Reviving costs all life essence
            } 
        }
        wait(1000);
    }

    //combat runner
    public void runCombat(Monster monster) {
        clearScreen();
        while (monster.getHealth() > 0 && hp > 0) {
            System.out.print("You are being attacked by a " + monster.getName() + "!\n");
            // Player actions
            System.out.print("-----------------------------------------\n");
            System.out.print("Your HP: " + hp + "/" + maxHp + "\n");
            System.out.print("Your Mana: " + mana + "/" + maxMana + "\n");
            System.out.print("-----------------------------------------\n");
            System.out.print("1. Spells\n");
            System.out.print("2. Flee\n");
            int playerChoice = getUserChoice(1, 2);
            switch (playerChoice) {
                case 1:
                    monster.setHealth(monster.getHealth() - castSpell(monster));
                    break;
                case 2:
                    // Flee with 30% chance of success
                    if (random.nextInt(100) < 30) {
                        slowPrint("You successfully fled from " + monster.getName() + "!\n");
                        return;
                    } else {
                        slowPrint("You failed to flee!\n");
                    }
                    break;
            }
            // Monster actions
            if (monster.getHealth() > 0)
                monsterAttack(monster);
        }
        // Monster defeated
        if (monster.getHealth() <= 0) {
            slowPrint("You defeated " + monster.getName() + "!\n");
            int lifeEssenceDrop = monster.getLifeEssenceDrop();
            lifeEssence += lifeEssenceDrop;
            slowPrint("You received " + lifeEssenceDrop + " Life Essence!\n");
            wait(3000);
            clearScreen();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------

    // Get user choice from the given range
    public int getUserChoice(int min, int max) {
        int choice;
        do {
            slowPrint("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                slowPrint("Invalid input. Please enter a number between " + min + " and " + max + ": ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        } while (choice < min || choice > max);
        return choice;
    }

    // Print text with delay for dramatic effect
    public void slowPrint(String input) {
        for (int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            try {
                if (goFast) {
                    Thread.sleep(2);
                } else {
                    Thread.sleep(30); // Faster printing in dungeons
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Clear the screen for better readability
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Wait for a specified amount of time
    public void wait(int time) {
        try {
            if (goFast) {
                Thread.sleep(5);
            } else {
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // End the game
    public void endGame() {
        slowPrint("\033[H\033[2J"); // Clear the screen
        slowPrint("Thank you for playing!\n");
        System.exit(0);
    }

    // Character creation process
    public void createCharacter() {
        slowPrint("Character creation...\n");
        wait(2000);
        slowPrint("Character created!\n");
        wait(1000);
    }
    
    //-------------------------------------------------------------------------------------------------------------------

    // God of Space Shop
    public void SanctumOfReality() {

        Price1 = AmountBoughtHp + 5;
        Price2 = AmountBoughtMp + 5;

        clearScreen();
        slowPrint("Hello Miniscule Creature!\n");
        slowPrint("Do you wish to sacrifice the energy of any souls?\n");
        slowPrint("Current LifeEssence: " + lifeEssence + "\n");
        slowPrint("\n");
        slowPrint("1. Life Essence Absorbtion " + Price1 + " LE\n");
        slowPrint("2. Life Essence Conversion " + Price2 + " LE\n");
        slowPrint("3. Exit The Sactum\n");

        int choice = getUserChoice(1, 3);
        switch (choice) {
            case 1:
                buyItem("HP");
                break;
            case 2:
                buyItem("Mana");
                break;
            case 3:
                // Exit the shop
                slowPrint("Exiting the SanctumOfReality...\n");
                wait(1000);
                return;
        }
    }

    // Buy items from the God of Space Shop
    public void buyItem(String item) {
        if (lifeEssence >= 0) {
            switch (item) {
                case "HP":
                if (lifeEssence >= Price1)
                    maxHp += 5;
                    hp = maxHp;
                    lifeEssence-= Price1;
                    AmountBoughtHp += 5;
                    slowPrint("Maximum HP increased by 5! Your lifeforce grows stronger!\n");
                    break;
                case "Mana":
                if (lifeEssence >= Price2)
                    maxMana += 5;
                    mana = maxMana;
                    lifeEssence-= Price2;
                    AmountBoughtMp += 5;
                    slowPrint("Maximum Mana increased by 5! Your soul grows stronger!\n");
                    break;
            }
             // Reduce life essence (cost of purchasing)
            slowPrint("Remaining Life Essence: " + lifeEssence + "\n");
        } else {
            slowPrint("You don't have enough Life Essence!\n");
        }
    }

    //-------------------------------------------------------------------------------------------------------------------
    
    //dungeon api stuff
    public ArrayList<Object> parseDungeonInfo(ArrayList<String> entry) {
        ArrayList<Object> info = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        temp.add(entry.get(0));
        for (int i = 0; i < 3; i++) {
            if (entry.get(i+1).equals("b")) {
                temp.add(entry.get(i+1));
            }
        }
        info.add(temp);
        for (int i = 4; i < entry.size(); i+=3) {
            if (Integer.parseInt(entry.get(i)) == 1) {
                info.add(new Room(entry.get(i+1), Integer.parseInt(entry.get(i+2)), Integer.parseInt(entry.get(i+3)), Integer.parseInt(entry.get(i+4))));
                i+=2;
            } else if (Integer.parseInt(entry.get(i)) == 2) {
                info.add(new Room(entry.get(i+1), entry.get(i+2)));
            }
        }

        return info;
    }
    //loads dungeon info into an arraylist
    public ArrayList<String> loadDungeonInfo(int dungeon) {
        ArrayList<String> info = new ArrayList<String>();
        String fileName = "dungeon" + dungeon + ".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String e = "";
                do {
                    e = reader.readLine();
                    System.out.println(e);
                    if (!e.toString().equals("end")) {
                        info.add(e);
                    }
                } while (!e.toString().equals("end"));
            } catch (IOException | NumberFormatException e) {
                System.out.println("error loading dungeon uh oh scoobs.0");
            }
        } else {
            System.out.println("error loading dungeon uh oh scoobs.1");
        }
        return info;
    }
}

//Uroxx, Ruler Of the Void