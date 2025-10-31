package domain.models;

/**
 * Represents a superhero power/ability
 */
public class Power implements Cloneable {
    private String name;
    private String description;
    private int powerLevel;
    
    public Power(String name, String description, int powerLevel) {
        this.name = name;
        this.description = description;
        this.powerLevel = powerLevel;
    }
    
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPowerLevel() { return powerLevel; }
    
    @Override
    public Power clone() {
        try {
            return (Power) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Power(name, description, powerLevel);
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s (Level: %d) - %s", name, powerLevel, description);
    }
}
