package Dungeons;

import MainCode.Event;
import MainCode.MainGame;
import MainCode.Monster;
import MainCode.Output;
import MainCode.dungeon;

public class FirstSpace extends dungeon {
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
        // Unlock the option to pray to the God of Space
        Output.slowPrint("You feel a connection with the God of Space...\n"); //improve and redo lore
        Output.slowPrint("The option to pray to the God of Space is now unlocked!\n"); //improve and redo 
        MainGame.godOfSpaceUnlocked = true;

        Output.wait(2000);
    }
}