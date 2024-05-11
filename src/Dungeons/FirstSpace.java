public class FirstSpace extends dungeon {
    @Override
    public String getName()
    {return "the 1st Trial of the God of Space";}

    @Override
    public Event[] getEvents() {
        Event[] events = {
            new Monster("Placeholder", 0, 3, 1),
            new Monster("Placeholder", 0, 3, 2),
            new Monster("Placeholder", 0, 3, 3)
    };
    return events;
}

    @Override
    public void onComplete() {
        // Unlock the option to pray to the God of Space
        Output.slowPrint("You feel a connection with the God of Space...\n");
        Output.slowPrint("The option to pray to the God of Space is now unlocked!\n");
        MainGame.godOfSpaceUnlocked = true;

        Output.wait(2000);
    }
}