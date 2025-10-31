package domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Superhero class - represents a Vought International superhero
 * Implements Cloneable for Prototype pattern
 */
public class Superhero implements Cloneable {
    private String name;
    private String heroType;  // Homelander-type, Starlight-type, etc.
    private SuperheroStats stats;
    private List<Power> powers;
    private String costume;
    private String backstory;
    private String weakness;
    private String team;
    private int brandValue;  // 1-5 stars
    private int publicRating; // 0-100
    
    public Superhero() {
        this.powers = new ArrayList<>();
    }
    
    // Getters
    public String getName() { return name; }
    public String getHeroType() { return heroType; }
    public SuperheroStats getStats() { return stats; }
    public List<Power> getPowers() { return powers; }
    public String getCostume() { return costume; }
    public String getBackstory() { return backstory; }
    public String getWeakness() { return weakness; }
    public String getTeam() { return team; }
    public int getBrandValue() { return brandValue; }
    public int getPublicRating() { return publicRating; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setHeroType(String heroType) { this.heroType = heroType; }
    public void setStats(SuperheroStats stats) { this.stats = stats; }
    public void setPowers(List<Power> powers) { this.powers = powers; }
    public void setCostume(String costume) { this.costume = costume; }
    public void setBackstory(String backstory) { this.backstory = backstory; }
    public void setWeakness(String weakness) { this.weakness = weakness; }
    public void setTeam(String team) { this.team = team; }
    public void setBrandValue(int brandValue) { this.brandValue = brandValue; }
    public void setPublicRating(int publicRating) { this.publicRating = publicRating; }
    
    public void addPower(Power power) {
        this.powers.add(power);
    }
    
    /**
     * Deep clone implementation for Prototype pattern
     */
    @Override
    public Superhero clone() {
        try {
            Superhero cloned = (Superhero) super.clone();
            
            // Deep copy stats
            if (this.stats != null) {
                cloned.stats = this.stats.clone();
            }
            
            // Deep copy powers list
            cloned.powers = new ArrayList<>();
            for (Power power : this.powers) {
                cloned.powers.add(power.clone());
            }
            
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Superhero: ").append(name).append(" (").append(heroType).append(")\n");
        sb.append("  Stats: ").append(stats).append("\n");
        sb.append("  Team: ").append(team).append("\n");
        sb.append("  Costume: ").append(costume).append("\n");
        sb.append("  Brand Value: ").append("*".repeat(brandValue)).append(" | Public Rating: ").append(publicRating).append("%\n");
        sb.append("  Powers:\n");
        for (Power power : powers) {
            sb.append("    - ").append(power.getName()).append("\n");
        }
        sb.append("  Weakness: ").append(weakness).append("\n");
        sb.append("  Backstory: ").append(backstory);
        return sb.toString();
    }
}
