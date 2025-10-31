package domain.factory.factories;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;

/**
 * FACTORY METHOD PATTERN - Concrete Factory
 * Creates Queen Maeve-type superheroes (Warrior)
 */
public class MaeveTypeFactory extends SuperheroFactory {
    
    @Override
    public Superhero createSuperhero(String name) {
        Superhero hero = new Superhero();
        
        hero.setName(name);
        hero.setHeroType("Maeve-Type (Warrior)");
        
        // Maeve stats: Balanced strength and durability
        SuperheroStats stats = new SuperheroStats(
            85,   // strength
            70,   // speed
            85,   // durability (high)
            75,   // intelligence
            75,   // charisma
            60    // stability (PTSD, alcoholism)
        );
        hero.setStats(stats);
        
        // Maeve powers
        hero.addPower(new Power("Super Strength", "Exceptional physical power", 85));
        hero.addPower(new Power("Enhanced Durability", "High resistance to damage", 85));
        hero.addPower(new Power("Combat Mastery", "Expert in all forms of combat", 90));
        hero.addPower(new Power("Weapon Proficiency", "Master of swords and shields", 88));
        hero.addPower(new Power("Limited Flight", "Short-range flight capability", 60));
        
        hero.setCostume("Greek warrior-inspired armor with red cape");
        hero.setWeakness("Alcoholism, PTSD, disillusionment with heroism");
        hero.setTeam("The Seven");
        hero.setBrandValue(4);
        hero.setPublicRating(82);
        hero.setBackstory("Veteran member of The Seven, struggles with moral compromises");
        
        return hero;
    }
}
