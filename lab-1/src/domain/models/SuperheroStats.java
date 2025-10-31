package domain.models;

/**
 * Represents the core statistics of a superhero
 */
public class SuperheroStats implements Cloneable {
    private int strength;
    private int speed;
    private int durability;
    private int intelligence;
    private int charisma;
    private int stability;  // Psychological stability
    
    public SuperheroStats(int strength, int speed, int durability, 
                         int intelligence, int charisma, int stability) {
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.stability = stability;
    }
    
    // Getters
    public int getStrength() { return strength; }
    public int getSpeed() { return speed; }
    public int getDurability() { return durability; }
    public int getIntelligence() { return intelligence; }
    public int getCharisma() { return charisma; }
    public int getStability() { return stability; }
    
    // Setters
    public void setStrength(int strength) { this.strength = strength; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setDurability(int durability) { this.durability = durability; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
    public void setCharisma(int charisma) { this.charisma = charisma; }
    public void setStability(int stability) { this.stability = stability; }
    
    public int getPowerLevel() {
        return (strength + speed + durability + intelligence) / 4;
    }
    
    @Override
    public SuperheroStats clone() {
        try {
            return (SuperheroStats) super.clone();
        } catch (CloneNotSupportedException e) {
            return new SuperheroStats(strength, speed, durability, intelligence, charisma, stability);
        }
    }
    
    @Override
    public String toString() {
        return String.format(
            "STR: %d, SPD: %d, DUR: %d, INT: %d, CHA: %d, STAB: %d, Power Level: %d",
            strength, speed, durability, intelligence, charisma, stability, getPowerLevel()
        );
    }
}
