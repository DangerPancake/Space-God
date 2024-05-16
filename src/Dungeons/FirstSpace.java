package Dungeons;

import MainCode.Event;
import MainCode.MainGame;
import MainCode.Monster;
import MainCode.Output;
import MainCode.dungeon;

public class FirstSpace extends dungeon { //first floor of towering black spire that seems to rise all the way to space, (Maybe how has nobody seen this)
    @Override
    public String getName()
    {return "The First Trial";} //change 

    @Override
    public Event[] getEvents() {
        Event[] events = {
            new Monster("Placeholder", 0, 3, 1), // redo this monster 
            new Monster("Placeholder", 0, 3, 2), // redo this monster 
            new Monster("Placeholder", 0, 3, 3) // redo this monster 
    };
    return events;
}

    @Override
    public void onComplete() {
        // Unlock the option to visit sanctum
        //He grants you a grimoire, replace spells

        Output.slowPrint("You feel a connection with the God of Space...\n"); //Make pact with higher being
        Output.slowPrint("The option to pray to the God of Space is now unlocked!\n"); //This, THIS IS NO GOD WHATEVER THIS IS SURPASSES ONE
        MainGame.godOfSpaceUnlocked = true;

        Output.wait(2000);
    }
}