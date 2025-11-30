package iterator;

import model.FilmReel;
import java.util.ArrayList;
import java.util.List;

/**
 * Iterates through films filtered by a specific decade (1920s, 1930s, etc.).
 */
public class DecadeIterator implements ArchiveIterator {
    private final List<FilmReel> filteredFilms;
    private int currentIndex;

    public DecadeIterator(List<FilmReel> films, int decade) {
        this.filteredFilms = new ArrayList<>();
        this.currentIndex = 0;
        
        // Filter films by decade
        for (FilmReel film : films) {
            if (film.getDecade() == decade) {
                filteredFilms.add(film);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < filteredFilms.size();
    }

    @Override
    public FilmReel next() {
        if (!hasNext()) {
            return null;
        }
        return filteredFilms.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    /**
     * Returns the count of films in this decade.
     */
    public int getCount() {
        return filteredFilms.size();
    }
}
