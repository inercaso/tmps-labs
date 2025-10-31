package domain.factory.singleton;

import domain.models.Superhero;
import java.util.HashMap;
import java.util.Map;

/**
 * SINGLETON PATTERN - Vought International Corporation
 * Main corporation managing all superhero operations
 */
public class VoughtInternational {
    private static VoughtInternational instance;
    private final Map<String, Superhero> superheroRegistry;
    private int compoundVStock;
    private int totalSuperherosCreated;
    
    // Private constructor
    private VoughtInternational() {
        superheroRegistry = new HashMap<>();
        compoundVStock = 1000;
        totalSuperherosCreated = 0;
    }
    
    // Thread-safe singleton instance getter
    public static synchronized VoughtInternational getInstance() {
        if (instance == null) {
            instance = new VoughtInternational();
        }
        return instance;
    }
    
    /**
     * Register a new superhero with Vought
     */
    public void registerSuperhero(Superhero hero) {
        superheroRegistry.put(hero.getName(), hero);
        totalSuperherosCreated++;
        System.out.println("Registered superhero with Vought: " + hero.getName());
    }
    
    /**
     * Get a registered superhero
     */
    public Superhero getSuperhero(String name) {
        return superheroRegistry.get(name);
    }
    
    /**
     * Use Compound V
     */
    public boolean useCompoundV(int amount) {
        if (compoundVStock >= amount) {
            compoundVStock -= amount;
            return true;
        }
        return false;
    }
    
    /**
     * Get Compound V stock
     */
    public int getCompoundVStock() {
        return compoundVStock;
    }
    
    /**
     * Display company statistics
     */
    public void displayStatistics() {
        System.out.println("\nVOUGHT INTERNATIONAL - STATISTICS");
        System.out.println("Total Superheroes Created: " + totalSuperherosCreated);
        System.out.println("Currently Registered: " + superheroRegistry.size());
        System.out.println("Compound V Stock: " + compoundVStock);
    }
}
