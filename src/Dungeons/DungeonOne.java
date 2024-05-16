package Dungeons;
import DungeonEvents.BombGolemEvent;
import DungeonEvents.IntroLoreEvent;
import MainCode.Color;
import MainCode.Event;
import MainCode.MainGame;
import MainCode.Monster;
import MainCode.Output;
import MainCode.dungeon;
import PlayerInfo.PlayerStats;

public class DungeonOne extends dungeon{

    @Override
    public String getName()
    {return "Crumbling Ruins";}

    @Override
    public Event[] getEvents() {
        Event[] events = {
        new IntroLoreEvent(),
        new Monster("an Ancient Weathered Golem", 3, 1, 0), //redo name maybe
        new Monster("a Crumbling Defense Turret", 4, 2, 1), //redo name 
        new BombGolemEvent(),
        
        
    };
    return events;
}
@Override
    public void onComplete() {
        Output.clearScreen();// rewrite/improve
        Output.slowPrint("Though gravely injured, you spot a crack in a wall damaged by the explosion");
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("You press on the section, and it collapses into a staircase downwards");
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("While walking deeper into the ruins, you begin to hear the mummer of running water.");//perhaps improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("As you approach the source of the sound, the air heats, and the passage opens up."); //perhaps improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("You observe the plain stone walls, shift into a black metallic surface."); //perhaps improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("Ahead of you lies a pool of shimmering fluid defying anything you've ever seen."); //perhaps improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("The liquid seems to endlessly flow from ornate yet strage devices on the walls."); //perhaps improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("However depite the strangeness of it, it appears to be some kind of hotspring."); //perhaps improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("So you decide to rest for a bit in the odd pool."); //perhaps improve
        Output.wait(3000);
        Output.clearScreen();
        Output.slowPrint("As you begin to submerge yourself into the weirdly thick fluid."); //perhaps improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint(Color.ANSI_PURPLE + " SUDDENLY, a strange power rushes through your body." + Color.ANSI_RESET); //perhaps improve
        Output.wait(4000);
        Output.clearScreen();
        Output.slowPrint("While resting in the pool you drift to sleep."); //perhaps improve
        Output.wait(3500);
        Output.clearScreen();
        Output.slowPrint("When you awaken sometime later you wounds have disappeared, and you feel somehow stronger."); //perhaps improve
        Output.wait(4000);

        PlayerStats.maxHp = 20;
        PlayerStats.maxMana = 20;
        PlayerStats.hp = PlayerStats.maxHp;
        PlayerStats.mana = PlayerStats.maxMana;

        MainGame.WellspringUnlocked = true; //you can know also revive
    }
}