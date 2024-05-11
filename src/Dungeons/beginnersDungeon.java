package Dungeons;
import DungeonEvents.exampleEvent;
import MainCode.Event;
import MainCode.Monster;
import MainCode.dungeon;

public class beginnersDungeon extends dungeon{

    @Override
    public String getName()
    {return "the Beginner's Dungeon";}

    @Override
    public Event[] getEvents() {
        Event[] events = {
        new Monster("Wooden Box", 1, 0, 0),
        new exampleEvent(),
        new Monster("Ball Of Ants", 1, 0, 1),
        new Monster("5 Pound Rat", 5, 1, 2)
    };
    return events;
}
}