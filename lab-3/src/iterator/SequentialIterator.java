package iterator;

import model.FilmReel;
import java.util.List;

/**
 * Iterates through films in the order they were added to the archive.
 */
public class SequentialIterator implements ArchiveIterator {
    private final List<FilmReel> films;
    private int currentIndex;

    public SequentialIterator(List<FilmReel> films) {
        this.films = films;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < films.size();
    }

    @Override
    public FilmReel next() {
        if (!hasNext()) {
            return null;
        }
        return films.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }
}
