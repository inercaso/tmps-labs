package strategy;

import model.FilmReel;

/**
 * Strategy interface for film restoration techniques.
 * Defines the contract for all restoration algorithms.
 */
public interface RestorationStrategy {
    
    /**
     * Applies the restoration technique to a film.
     */
    RestorationResult restore(FilmReel film);
    
    /**
     * Returns the estimated cost of this restoration technique.
     */
    double estimateCost();
    
    /**
     * Returns the estimated time for restoration.
     */
    String estimateTime();
    
    /**
     * Returns a description of this restoration technique.
     */
    String getDescription();
    
    /**
     * Returns the name of this strategy.
     */
    String getName();
}
