package client;

import domain.factory.builders.*;
import domain.factory.factories.*;
import domain.factory.prototypes.PrototypeRegistry;
import domain.factory.singleton.*;
import domain.models.Superhero;

/**
 * VOUGHT INTERNATIONAL - SUPERHERO MANAGEMENT SYSTEM
 * Demonstrates all Creational Design Patterns
 * 
 * Patterns Implemented:
 * 1. Builder Pattern - Complex superhero construction
 * 2. Factory Method Pattern - Different superhero type creation
 * 3. Prototype Pattern - Template cloning
 * 4. Singleton Pattern - Vought management systems
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("VOUGHT INTERNATIONAL - SUPERHERO MANAGEMENT SYSTEM");
        System.out.println("Creational Design Patterns Demonstration\n");
        
        // Get singleton instances
        VoughtInternational vought = VoughtInternational.getInstance();
        TheSevenManager theSeven = TheSevenManager.getInstance();
        PrototypeRegistry registry = PrototypeRegistry.getInstance();
        
        // DEMONSTRATION 1: BUILDER PATTERN
        System.out.println("=== DEMONSTRATION 1: BUILDER PATTERN ===");
        System.out.println("Creating a custom superhero step-by-step...\n");
        
        SuperheroBuilder builder = new DetailedSuperheroBuilder();
        Superhero customHero = builder
            .setName("Captain Liberty")
            .setHeroType("Custom Hero (Builder Pattern)")
            .setStats(90, 85, 88, 75, 80, 70)
            .addPower("Flight", "High-speed aerial movement", 85)
            .addPower("Energy Shield", "Protective force field", 80)
            .addPower("Leadership", "Inspires team members", 90)
            .setCostume("Stars and stripes tactical suit")
            .setBackstory("Former military officer enhanced with Compound V")
            .setWeakness("Overconfidence in leadership abilities")
            .setTeam("The Seven")
            .setBrandValue(4)
            .setPublicRating(85)
            .build();
        
        System.out.println("Created: " + customHero.getName() + " (" + customHero.getHeroType() + ")");
        System.out.println("Stats: " + customHero.getStats());
        System.out.println("Powers: " + customHero.getPowers().size() + " abilities");
        
        vought.registerSuperhero(customHero);
        theSeven.addMember(customHero);
        
        // DEMONSTRATION 2: FACTORY METHOD PATTERN
        System.out.println("\n=== DEMONSTRATION 2: FACTORY METHOD PATTERN ===");
        System.out.println("Creating different superhero types with factories...\n");
        
        SuperheroFactory homelanderFactory = new HomelanderTypeFactory();
        Superhero homelander = homelanderFactory.createSuperhero("Homelander");
        System.out.println("Created: " + homelander.getName() + " (Power Level: " + homelander.getStats().getPowerLevel() + ")");
        vought.registerSuperhero(homelander);
        theSeven.addMember(homelander);
        
        SuperheroFactory starlightFactory = new StarlightTypeFactory();
        Superhero starlight = starlightFactory.createSuperhero("Starlight");
        System.out.println("Created: " + starlight.getName() + " (Power Level: " + starlight.getStats().getPowerLevel() + ")");
        vought.registerSuperhero(starlight);
        theSeven.addMember(starlight);
        
        SuperheroFactory atrainFactory = new ATrainTypeFactory();
        Superhero atrain = atrainFactory.createSuperhero("A-Train");
        System.out.println("Created: " + atrain.getName() + " (Power Level: " + atrain.getStats().getPowerLevel() + ")");
        vought.registerSuperhero(atrain);
        theSeven.addMember(atrain);
        
        SuperheroFactory maeveFactory = new MaeveTypeFactory();
        Superhero maeve = maeveFactory.createSuperhero("Queen Maeve");
        System.out.println("Created: " + maeve.getName() + " (Power Level: " + maeve.getStats().getPowerLevel() + ")");
        vought.registerSuperhero(maeve);
        theSeven.addMember(maeve);
        
        // DEMONSTRATION 3: PROTOTYPE PATTERN
        System.out.println("\n=== DEMONSTRATION 3: PROTOTYPE PATTERN ===");
        System.out.println("Cloning superhero templates...\n");
        
        System.out.println("Available templates:");
        System.out.println("- seven_leader");
        System.out.println("- speedster");
        System.out.println("- warrior");
        System.out.println("- energy_projector");
        
        System.out.println("\nCloning 'speedster' template...");
        Superhero clonedSpeedster = registry.getTemplate("speedster");
        clonedSpeedster.setName("Velocity");
        clonedSpeedster.setBackstory("Second-generation speedster, trained by A-Train");
        clonedSpeedster.getStats().setSpeed(95);
        System.out.println("Created clone: " + clonedSpeedster.getName());
        vought.registerSuperhero(clonedSpeedster);
        theSeven.addMember(clonedSpeedster);
        
        System.out.println("\nCloning 'warrior' template...");
        Superhero clonedWarrior = registry.getTemplate("warrior");
        clonedWarrior.setName("Battle Maiden");
        clonedWarrior.setBackstory("Warrior princess from mythical realm");
        clonedWarrior.getStats().setStrength(90);
        System.out.println("Created clone: " + clonedWarrior.getName());
        vought.registerSuperhero(clonedWarrior);
        theSeven.addMember(clonedWarrior);
        
        // DEMONSTRATION 4: SINGLETON PATTERN
        System.out.println("\n=== DEMONSTRATION 4: SINGLETON PATTERN ===");
        System.out.println("Verifying single instances of management systems...\n");
        
        VoughtInternational vought2 = VoughtInternational.getInstance();
        TheSevenManager theSeven2 = TheSevenManager.getInstance();
        
        System.out.println("VoughtInternational instance check: " + (vought == vought2));
        System.out.println("TheSevenManager instance check: " + (theSeven == theSeven2));
        System.out.println("Singleton pattern verified!\n");
        
        // Display final statistics
        System.out.println("\n=== FINAL STATISTICS ===");
        System.out.println("Total heroes created: 7");
        System.out.println("The Seven members: " + theSeven.getTeamSize() + "/7");
        
        System.out.println("\n=== THE SEVEN ROSTER ===");
        for (Superhero hero : theSeven.getMembers()) {
            System.out.println("- " + hero.getName() + " (" + hero.getHeroType() + ")");
        }
        
        System.out.println("\n=== DEMONSTRATION COMPLETE ===");
        System.out.println("Patterns demonstrated:");
        System.out.println("1. Builder Pattern - Complex object construction");
        System.out.println("2. Factory Method Pattern - Type-specific creation");
        System.out.println("3. Prototype Pattern - Template cloning");
        System.out.println("4. Singleton Pattern - Single instances");
    }
}
