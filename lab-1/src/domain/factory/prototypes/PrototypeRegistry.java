package domain.factory.prototypes;

import domain.models.Power;
import domain.models.Superhero;
import domain.models.SuperheroStats;
import java.util.HashMap;
import java.util.Map;

/**
 * PROTOTYPE PATTERN - Registry
 * Stores and manages superhero templates for cloning
 */
public class PrototypeRegistry {
    private static PrototypeRegistry instance;
    private Map<String, Superhero> templates;
    
    private PrototypeRegistry() {
        templates = new HashMap<>();
        initializeTemplates();
    }
    
    public static PrototypeRegistry getInstance() {
        if (instance == null) {
            instance = new PrototypeRegistry();
        }
        return instance;
    }
    
    /**
     * Initialize pre-configured superhero templates
     */
    private void initializeTemplates() {
        // The Seven Leader Template
        Superhero sevenLeader = new Superhero();
        sevenLeader.setName("Seven Leader Template");
        sevenLeader.setHeroType("Alpha Leader");
        sevenLeader.setStats(new SuperheroStats(100, 95, 98, 70, 60, 30));
        sevenLeader.addPower(new Power("Flight", "High-speed flight", 95));
        sevenLeader.addPower(new Power("Super Strength", "Maximum strength", 100));
        sevenLeader.addPower(new Power("Heat Vision", "Laser beams", 98));
        sevenLeader.setCostume("American flag theme with cape");
        sevenLeader.setTeam("The Seven");
        sevenLeader.setBrandValue(5);
        sevenLeader.setPublicRating(95);
        sevenLeader.setWeakness("Psychological instability");
        templates.put("seven_leader", sevenLeader);
        
        // Speedster Template
        Superhero speedster = new Superhero();
        speedster.setName("Speedster Template");
        speedster.setHeroType("Speedster");
        speedster.setStats(new SuperheroStats(70, 99, 50, 65, 70, 50));
        speedster.addPower(new Power("Super Speed", "Extreme speed", 99));
        speedster.addPower(new Power("Enhanced Metabolism", "Fast healing", 80));
        speedster.setCostume("Aerodynamic racing suit");
        speedster.setTeam("The Seven");
        speedster.setBrandValue(3);
        speedster.setPublicRating(75);
        speedster.setWeakness("Heart problems, drug dependency");
        templates.put("speedster", speedster);
        
        // Warrior Template
        Superhero warrior = new Superhero();
        warrior.setName("Warrior Template");
        warrior.setHeroType("Warrior");
        warrior.setStats(new SuperheroStats(85, 70, 85, 75, 75, 60));
        warrior.addPower(new Power("Super Strength", "High strength", 85));
        warrior.addPower(new Power("Combat Mastery", "Expert fighter", 90));
        warrior.addPower(new Power("Enhanced Durability", "Tough defense", 85));
        warrior.setCostume("Warrior armor with cape");
        warrior.setTeam("The Seven");
        warrior.setBrandValue(4);
        warrior.setPublicRating(82);
        warrior.setWeakness("PTSD, alcoholism");
        templates.put("warrior", warrior);
        
        // Energy Projector Template
        Superhero energyHero = new Superhero();
        energyHero.setName("Energy Projector Template");
        energyHero.setHeroType("Energy Projector");
        energyHero.setStats(new SuperheroStats(60, 65, 60, 80, 90, 85));
        energyHero.addPower(new Power("Energy Blasts", "Light/energy projection", 75));
        energyHero.addPower(new Power("Light Manipulation", "Control light", 70));
        energyHero.setCostume("White and gold costume");
        energyHero.setTeam("The Seven");
        energyHero.setBrandValue(4);
        energyHero.setPublicRating(88);
        energyHero.setWeakness("Requires light source");
        templates.put("energy_projector", energyHero);
    }
    
    /**
     * Get a cloned template by name
     */
    public Superhero getTemplate(String templateName) {
        Superhero template = templates.get(templateName);
        if (template == null) {
            throw new IllegalArgumentException("Template not found: " + templateName);
        }
        return template.clone();
    }
    
    /**
     * Register a new template
     */
    public void registerTemplate(String name, Superhero hero) {
        templates.put(name, hero);
    }
    
    /**
     * List all available templates
     */
    public void listTemplates() {
        System.out.println("\nAvailable Superhero Templates:");
        for (String key : templates.keySet()) {
            Superhero hero = templates.get(key);
            System.out.println("  - " + key + ": " + hero.getHeroType() + " (Power Level: " + hero.getStats().getPowerLevel() + ")");
        }
    }
}
