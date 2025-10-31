package domain.factory.factories;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;

/**
 * FACTORY METHOD PATTERN - Concrete Factory
 * Creates Homelander-type superheroes (Alpha Leader)
 */
public class HomelanderTypeFactory extends SuperheroFactory {
    
    @Override
    public Superhero createSuperhero(String name) {
        Superhero hero = new Superhero();
        
        hero.setName(name);
        hero.setHeroType("Homelander-Type (Alpha Leader)");
        
        // Homelander stats: Maximum power
        SuperheroStats stats = new SuperheroStats(
            100,  // strength
            95,   // speed
            98,   // durability
            70,   // intelligence
            60,   // charisma
            30    // stability (low - psychologically unstable)
        );
        hero.setStats(stats);
        
        // Homelander powers
        hero.addPower(new Power("Flight", "High-speed flight capability", 95));
        hero.addPower(new Power("Super Strength", "Extreme physical strength", 100));
        hero.addPower(new Power("Heat Vision", "Laser eye beams", 98));
        hero.addPower(new Power("Invulnerability", "Near-total damage immunity", 98));
        hero.addPower(new Power("Enhanced Senses", "X-ray vision, super hearing", 90));
        
        hero.setCostume("American flag-themed suit with cape");
        hero.setWeakness("Psychological instability, ego, lack of real affection");
        hero.setTeam("The Seven");
        hero.setBrandValue(5);
        hero.setPublicRating(95);
        hero.setBackstory("Lab-raised by Vought, marketed as America's greatest hero");
        
        return hero;
    }
}
