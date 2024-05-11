// Monster class to represent basic monsters in the dungeon
class Monster extends Event{
    private String name;
    private int health;
    private int damage;
    private int lifeEssenceDrop;

    // Constructor
    public Monster(String name, int health, int damage, int lifeEssenceDrop) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.lifeEssenceDrop = lifeEssenceDrop;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public int getLifeEssenceDrop() {
        return lifeEssenceDrop;
    }
}