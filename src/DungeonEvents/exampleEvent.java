package DungeonEvents;
import MainCode.Event;
import MainCode.Output;
import PlayerInfo.PlayerStats;


public class exampleEvent extends Event {
    @Override
    public void run()
    {
        System.out.println("Discover healing spring absorb essence");
        Output.wait(1000);
    }
}