package MainCode;
import java.io.*;
import java.util.ArrayList;
import PlayerInfo.PlayerStats;

public class Combat {
    // Spells
    public static ArrayList<String> Spells = new ArrayList<String>() {{
        add("Firebolt");
        add("Healing Light");
        add("Lesser Focus");   
    }}; 
    public static ArrayList<Integer> spellCosts = new ArrayList<Integer>() {{
        add(2);
        add(4); 
        add(0);
        
    }}; 
    public static ArrayList<Integer> coolDown = new ArrayList<Integer>(); 
    // Monster attacks during combat
    public static int monsterAttack(Monster monster) {
        if (monster.getHealth() <= 0) {
            return 0;
        }
        if (monster.Stunned() > 0 ) {
            Output.clearScreen();
            monster.StunnedCounter -= 1; 
            Output.slowPrint(monster.getName() + " is stunned!");
            Output.wait(1000);
            Output.clearScreen();
        } else {

            Output.slowPrint(monster.getName() + " attacked you and dealt " + monster.getDamage() + " damage!");
            Output.wait(1000);
            Output.clearScreen();
            PlayerStats.hp -= monster.getDamage();
            if (PlayerStats.hp <= 0) {
                System.out.println("You were defeated by " + monster.getName() + "!");
                System.out.println("You Have Died");
                // Revive the player with life essence
                if (PlayerStats.lifeEssence >= 0) {
                    Output.clearScreen();
                    Output.slowPrint("As you lay dead, all your gathered life essence bursts fourth into the heavens as tribute, an unkown power reaches out, you are revived");
                    PlayerStats.hp = PlayerStats.maxHp;
                    PlayerStats.lifeEssence = 0; // Reviving costs all life essence
                    return 1;
                }
            } 
        }
        return 0;
    }

     // Cast a spell during combat
    public static void castSpell(Monster monster) {
        // Print encounter message
        Output.clearScreen();
        if (monster.Stunned() > 0 ) {
            Output.clearScreen();
            System.out.print("The Monster Appears to be stunned\n"); 
        } else {
            System.out.print("You are being attacked by " + monster.getName() + "!\n");
        }
    
        // Print player stats
        System.out.print("-----------------------------------------\n");
        System.out.print("HP: "+ Color.ANSI_RED + PlayerStats.hp + Color.ANSI_RESET + "/" + Color.ANSI_RED + PlayerStats.maxHp + Color.ANSI_RESET + "\n");
        System.out.print("MP: " + Color.ANSI_BLUE + PlayerStats.mana + Color.ANSI_RESET + "/" + Color.ANSI_BLUE + PlayerStats.maxMana + Color.ANSI_RESET + "\n");
        System.out.print("-----------------------------------------\n");
    
        // Print available spells
        System.out.print("Available Spells:\n");
        for (int i = 0; i < Spells.size(); i++) {
            System.out.print((i + 1) + ". " + Spells.get(i) + " (Mana Cost: " + spellCosts.get(i) + ")");
            if(coolDown.get(i) > 0) {
                System.out.print(" CD:" + coolDown.get(i));
            }
            System.out.print("\n");
            
        }
    
        // Prompt for spell selection
        for (int i = 0; i <Spells.size()-1; i++)
        {
          coolDown.set(i, Math.max(0,coolDown.get(i)-1));
        }
        int choice = Output.getUserChoice(1, Spells.size());
    
        Output.clearScreen(); // Clear the screen after selecting spells
    
        if (PlayerStats.mana >= spellCosts.get(choice - 1)) {
            if (coolDown.get(choice - 1) > 0) {
                Output.slowPrint("You don't feel as if you have the energy to cast this again yet!\n");
                return;
            }
            // Cast the spell
            Output.slowPrint("You cast " + Spells.get(choice-1) + "!\n");
            if (choice == 1) { //firebolt
                PlayerStats.mana -= spellCosts.get(choice-1);
                int damage = MainGame.random.nextInt(2) + 2; // Random damage between 2 and 3
                Output.slowPrint("You dealt " + damage + " damage to " + monster.getName() + "!\n");
                Output.wait(2000);
                Output.clearScreen();
                monster.setHealth(monster.getHealth() - damage);
            } else if (choice == 2 ) { //healing light
                PlayerStats.mana -= spellCosts.get(choice - 1);
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
                Output.wait(1000);
                Output.clearScreen();
                if ( PlayerStats.mana > PlayerStats.maxMana) {
                    PlayerStats.mana = PlayerStats.maxMana; //make sure mana does not overflow
                    Output.wait(2000);
                    Output.clearScreen();
                }
            } else if (choice == 4) { //ShockBolt 
                coolDown.set(3, coolDown.get(3) + 2); 
                PlayerStats.mana -= spellCosts.get(choice - 1);
                int damage = MainGame.random.nextInt(2) + 1; // 2 DMG
                monster.StunnedCounter += 1;
                Output.slowPrint("You dealt " + damage + " damage to " + monster.getName() + "!\n");
                Output.slowPrint("You stunned your target!");
                Output.wait(2000);
                Output.clearScreen();
                monster.setHealth(monster.getHealth() - damage);
            } else {
                Output.slowPrint("Not enough mana to cast this spell!\n");
            }
        }
    }
}