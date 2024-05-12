package Dungeons;
import DungeonEvents.exampleEvent;
import MainCode.Event;
import MainCode.MainGame;
import MainCode.Monster;
import MainCode.Output;
import MainCode.dungeon;

public class beginnersDungeon extends dungeon{

    @Override
    public String getName()
    {return "the Beginner's Dungeon";}

    @Override
    public Event[] getEvents() {
        Event[] events = {
        new Monster("Wooden Box", 1, 0, 0),
        new Monster("Ball Of Ants", 1, 0, 1),
        new Monster("5 Pound Rat", 5, 1, 2)
    };
    return events;
}
@Override
    public void onComplete() {
        // Unlock the option to pray to the God of Space
        Output.slowPrint("LORE WOW\n");
        Output.slowPrint("LORE UNLOCKED WOW!\n");
        MainGame.WellspringUnlocked = true;

        Output.wait(2000);
    }
}