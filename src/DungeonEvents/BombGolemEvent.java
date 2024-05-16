package DungeonEvents;
import MainCode.Event;
import MainCode.Output;
import PlayerInfo.PlayerStats;


public class BombGolemEvent extends Event {
    @Override
    public void run()
    {
        Output.clearScreen();
        Output.slowPrint("You defeat your first two enemies without too much trouble, and move on");//redo writing/improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("But then out of nowhere you trigger a trap!");//redo writing/improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("Suddenly a portion of the ground explodes, you take 6 damage");//redo writing/improve
        PlayerStats.hp -= 6;
        Output.wait(4000);
        Output.clearScreen();
        
        
        // enter into first dungeon lore, first monster has no arms or legs or weapons so can't do much
       // 6 dmg
    
    }
}