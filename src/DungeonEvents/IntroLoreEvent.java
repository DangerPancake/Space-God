package DungeonEvents;
import MainCode.Event;
import MainCode.Output;
import PlayerInfo.PlayerStats;


public class IntroLoreEvent extends Event {
    @Override
    public void run()
    {
        Output.clearScreen();
        Output.slowPrint("As you approach the ruins, you spot an entrance and head inside.");// Rewrite to be better quality
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("After sometime exploring the structure, you notice while some defenses remain.");// Rewrite to be better quality
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("Most are non longer functional, or have severely degraded over the centuries.");// Rewrite to be better quality
        Output.wait(4000);
        Output.clearScreen();

    }
}