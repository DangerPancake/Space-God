import java.io.*;

public class Combat {

    // Spells
    public static String[] spells = {"Firebolt", "Healing Light", "Lesser Focus"};
    public static int[] spellCosts = {3, 5, 0};

       // Monster attacks during combat
       public static void monsterAttack(Monster monster) {
        if (monster.getHealth() <= 0) {
            return;
        }
        Output.slowPrint(monster.getName() + " attacked you and dealt " + monster.getDamage() + " damage!");
        Output.wait(1000);
        Output.clearScreen();
        PlayerStats.hp -= monster.getDamage();
        if (PlayerStats.hp <= 0) {
            System.out.println("You were defeated by " + monster.getName() + "!");
            System.out.println("You Have Died");
            // Revive the player with life essence
            if (PlayerStats.lifeEssence >= 0) {
                Output.slowPrint("As you lay dead, all your gathered life essence bursts fourth into the heavens as tribute, an unkown power reaches out, you are revived");
                PlayerStats.hp = PlayerStats.maxHp;
                PlayerStats.lifeEssence = 0; // Reviving costs all life essence
            } 
        }
        Output.wait(1000);
    }

     // Cast a spell during combat
     public static void castSpell(Monster monster) {
        // Print encounter message
        Output.clearScreen();
        System.out.print("You are being attacked by a " + monster.getName() + "!\n");
        System.out.print("-----------------------------------------\n");
    
        // Print player stats
        System.out.print("Your HP: " + PlayerStats.hp + "/" + PlayerStats.maxHp + "\n");
        System.out.print("Your Mana: " + PlayerStats.mana + "/" + PlayerStats.maxMana + "\n");
        System.out.print("-----------------------------------------\n");
    
        // Print available spells
        System.out.print("Available Spells:\n");
        for (int i = 0; i < spells.length; i++) {
            System.out.print((i + 1) + ". " + spells[i] + " (Mana Cost: " + spellCosts[i] + ")\n");
        }
    
        // Prompt for spell selection
        Output.slowPrint("Select a spell to cast (1-" + spells.length + "): ");
        int choice = Output.getUserChoice(1, spells.length);
    
        Output.clearScreen(); // Clear the screen after selecting spells
    
        if (PlayerStats.mana >= spellCosts[choice - 1]) {
            // Cast the spell
            Output.slowPrint("You cast " + spells[choice - 1] + "!\n");
            if (choice == 1) { //firebolt
                PlayerStats.mana -= spellCosts[choice - 1];
                int damage = MainGame.random.nextInt(3) + 1; // Random damage between 1 and 3
                Output.slowPrint("You dealt " + damage + " damage to " + monster.getName() + "!\n");
                Output.wait(2000);
                Output.clearScreen();
                monster.setHealth(monster.getHealth() - damage);
            } else if (choice == 2 ) { //healing light
                PlayerStats.mana -= spellCosts[choice - 1];
               Output.slowPrint("For a moment a warm light covers your skin, you heal 5 hp!\n");
               PlayerStats.hp = PlayerStats.hp + 5; //gain 5 hp 
               if (PlayerStats.hp > PlayerStats.maxHp) {
                PlayerStats.hp = PlayerStats.maxHp; //make sure hp does not overflow
                Output.wait(2000);
                Output.clearScreen();
               } 
            } else if (choice == 3) { //lesser focus
                PlayerStats.mana += PlayerStats.maxMana * 0.2; // Restore 20% of max mana with Lesser Focus
                Output.slowPrint("You regained some mana!\n");
                if ( PlayerStats.mana > PlayerStats.maxMana) {
                    PlayerStats.mana = PlayerStats.maxMana; //make sure mana does not overflow
                    Output.wait(2000);
                    Output.clearScreen();
                }
            }
        } else {
            Output.slowPrint("Not enough mana to cast this spell!\n");
        }
        Output.wait(1000);
    }
    
}