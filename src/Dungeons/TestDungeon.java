package Dungeons;
import MainCode.Event;
import MainCode.MainGame;
import MainCode.Monster;
import MainCode.Output;
import MainCode.dungeon;

public class TestDungeon extends dungeon {
    @Override
    public String getName()
    {return "TestDungeon";}

    @Override
    public Event[] getEvents() {
        Event[] events = {
            new Monster("NoAttackhealthDummy", 10, 0, 1),
            new Monster("Health&Damagedummy", 10, 3, 2),
            new Monster("TankDummy", 100, 0, 3)
    };
    return events;
}

    @Override
    public void onComplete() {
       
    }
}