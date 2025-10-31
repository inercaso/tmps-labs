package domain.factory.factories;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;

/**
 * FACTORY METHOD PATTERN - Concrete Factory
 * Creates A-Train-type superheroes (Speedster)
 */
public class ATrainTypeFactory extends SuperheroFactory {
    
    @Override
    public Superhero createSuperhero(String name) {
        Superhero hero = new Superhero();
        
        hero.setName(name);
        hero.setHeroType("A-Train-Type (Speedster)");
        
        // A-Train stats: Extreme speed, low durability
        SuperheroStats stats = new SuperheroStats(
            70,   // strength
            99,   // speed (maximum)
            50,   // durability (low - fragile at high speed)
            65,   // intelligence
            70,   // charisma
            50    // stability (drug issues)
        );
        hero.setStats(stats);
        
        // A-Train powers
        hero.addPower(new Power("Super Speed", "Near light-speed movement", 99));
        hero.addPower(new Power("Enhanced Metabolism", "Rapid healing and energy processing", 80));
        hero.addPower(new Power("Rapid Healing", "Quick recovery from injuries", 70));
        hero.addPower(new Power("Vibration Phasing", "Phase through objects at high speed", 75));
        
        hero.setCostume("Blue and silver aerodynamic racing suit");
        hero.setWeakness("Heart problems from Compound V, drug dependency, fragile bones");
        hero.setTeam("The Seven");
        hero.setBrandValue(3);
        hero.setPublicRating(75);
        hero.setBackstory("Fastest man alive, struggling with performance enhancement addiction");
        
        return hero;
    }
}
