public class Room {
    private Monster mob = new Monster(null, 0, 0, 0);
    private int type;
    private String roomName = "";
    private String description = "";
    public Room (String name, int health, int damage, int drop) {
        type = 1;
        mob = new Monster(name, health, damage, drop);
    }
    public Room (String name, String description) {
        type = 2;
        this.roomName = name;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public Monster getMonster() {
        return mob;
    }

    public String getName() {
        return roomName;
    }

    public String getDescription() {
        return description;
    }
}
