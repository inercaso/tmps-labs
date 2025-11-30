package iterator;

import model.FilmReel;

/**
 * Iterator interface for traversing the film archive.
 * Defines the contract for all archive iterators.
 */
public interface ArchiveIterator {
    
    /**
     * Checks if there are more films to iterate.
     */
    boolean hasNext();
    
    /**
     * Returns the next film in the iteration.
     */
    FilmReel next();
    
    /**
     * Resets the iterator to the beginning.
     */
    void reset();
}
