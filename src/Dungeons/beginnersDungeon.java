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
    {return "Mountain Cave";}

    @Override
    public Event[] getEvents() {
        Event[] events = {
        new Monster("Wooden Box", 1, 0, 0), // redo this monster balance to be super hard
        new Monster("Ball Of Ants", 1, 0, 1), // redo this monster balance to be super hard
        new Monster("5 Pound Rat", 5, 1, 2) // redo this monster balance to be super hard
    };
    return events;
}
@Override
    public void onComplete() {
        // Unlock the option to pray to the God of Space
        //first meeting lore find shreds of his power, he lets you bathe in the blood of the gods hes slain gain slight buff unlock pool. 
        Output.slowPrint("LORE WOW\n");// redo 
        Output.slowPrint("LORE UNLOCKED WOW!\n"); //redo 
        MainGame.WellspringUnlocked = true;

        Output.wait(2000);
    }
}