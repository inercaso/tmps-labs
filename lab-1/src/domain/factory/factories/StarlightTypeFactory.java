package domain.factory.factories;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;

/**
 * FACTORY METHOD PATTERN - Concrete Factory
 * Creates Starlight-type superheroes (Energy Projector)
 */
public class StarlightTypeFactory extends SuperheroFactory {
    
    @Override
    public Superhero createSuperhero(String name) {
        Superhero hero = new Superhero();
        
        hero.setName(name);
        hero.setHeroType("Starlight-Type (Energy Projector)");
        
        // Starlight stats: High charisma, balanced powers
        SuperheroStats stats = new SuperheroStats(
            60,   // strength
            65,   // speed
            60,   // durability
            80,   // intelligence
            90,   // charisma (very high - good PR)
            85    // stability (mentally stable)
        );
        hero.setStats(stats);
        
        // Starlight powers
        hero.addPower(new Power("Light Manipulation", "Control and project light energy", 75));
        hero.addPower(new Power("Energy Blasts", "Shoot concentrated light beams", 70));
        hero.addPower(new Power("Blinding Flash", "Temporarily blind enemies", 65));
        hero.addPower(new Power("Levitation", "Limited flight ability", 50));
        
        hero.setCostume("White and gold costume with star motif");
        hero.setWeakness("Requires light source to power up, moderate durability");
        hero.setTeam("The Seven");
        hero.setBrandValue(4);
        hero.setPublicRating(88);
        hero.setBackstory("Small-town hero recruited to The Seven, maintains moral compass");
        
        return hero;
    }
}
