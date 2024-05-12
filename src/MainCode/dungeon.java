package MainCode;
import PlayerInfo.PlayerStats;

public class dungeon {
    public String getName() { return "";}

    public Event[] getEvents() {
        Event[] empty = {};
        return empty;
    }

    public void onComplete() {}

    // Explore Beginner's Dungeon
    public void explore() {
        // Dungeon exploration
        Output.slowPrint("Exploring " + getName() + "...\n");
        Output.wait(1000);
        // Monster encounters (Manually set monster stats)
        for (Event event : getEvents()) {
            if (event instanceof Monster){
                Monster monster = (Monster)event;
                Output.clearScreen();
                while (monster.getHealth() > 0 && PlayerStats.hp > 0) {
                    if (monster.Stunned() > 0 ) {
                        Output.wait(500);
                        System.out.print("The Monster Appears to be stunned\n");
                    } else {
                        System.out.print("You are being attacked by a " + monster.getName() + "!\n");
                    }

                    // Player actions
                    System.out.print("-----------------------------------------\n");
                    System.out.print("HP: "+ Color.ANSI_RED + PlayerStats.hp + Color.ANSI_RESET + "/" + Color.ANSI_RED + PlayerStats.maxHp + Color.ANSI_RESET + "\n");
                    System.out.print("MP: "+ Color.ANSI_BLUE + PlayerStats.mana + Color.ANSI_RESET + "/" + Color.ANSI_BLUE + PlayerStats.maxMana + Color.ANSI_RESET + "\n");
                    System.out.print("-----------------------------------------\n");
                    System.out.print("1. Spells\n");
                    System.out.print("2. Flee\n");
                    int playerChoice = Output.getUserChoice(1, 2);
                    switch (playerChoice) {
                        case 1:
                            Combat.castSpell(monster);
                            break;
                        case 2:
                            // Flee with 30% chance of success
                            if (MainGame.random.nextInt(100) < 30) {
                                Output.slowPrint("You successfully fled from " + monster.getName() + "!\n");
                                return;
                            } else {
                                Output.slowPrint("You failed to flee!\n");
                            }
                            break;
                        }
                
                        // Monster actions
                    Combat.monsterAttack(monster);
                }
                // Monster defeated
                if (monster.getHealth() <= 0) {
                    Output.slowPrint("You defeated " + monster.getName() + "!\n");
                    int lifeEssenceDrop = monster.getLifeEssenceDrop();
                    PlayerStats.lifeEssence += lifeEssenceDrop;
                    Output.slowPrint("You received " + Color.ANSI_GREEN + lifeEssenceDrop + Color.ANSI_RESET + " Life Essence!\n");
                    Output.wait(3000);
                    Output.clearScreen();
                }
            } else {
                event.run();
                Output.wait(1000);
                Output.clearScreen();
            }
        }
        // After defeating all monsters
        Output.slowPrint("You cleared " + getName() + "!\n");
        Output.slowPrint("Press any key to continue...\n");
        Output.scanner.nextLine(); // Output.wait for any key press
        Output.wait(1000);
        onComplete();
    }
}