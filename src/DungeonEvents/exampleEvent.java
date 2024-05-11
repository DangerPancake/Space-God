package DungeonEvents;
import MainCode.Event;
import MainCode.Output;
import PlayerInfo.PlayerStats;


public class exampleEvent extends Event {
    @Override
    public void run()
    {
        System.out.println("You are deeply tired.");
        Output.wait(1000);
    }
}