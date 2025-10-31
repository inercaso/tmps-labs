package domain.factory.factories;

import domain.models.Superhero;

/**
 * FACTORY METHOD PATTERN - Abstract Factory
 * Defines the interface for creating superhero objects
 */
public abstract class SuperheroFactory {
    
    /**
     * Factory method - subclasses override to create specific hero types
     */
    public abstract Superhero createSuperhero(String name);
    
    /**
     * Template method that uses the factory method
     */
    public Superhero createAndRegister(String name) {
        Superhero hero = createSuperhero(name);
        System.out.println("Created " + hero.getHeroType() + ": " + name);
        return hero;
    }
}
