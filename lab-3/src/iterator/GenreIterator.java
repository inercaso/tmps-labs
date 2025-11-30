package iterator;

import model.FilmReel;
import java.util.ArrayList;
import java.util.List;

/**
 * Iterates through films filtered by genre.
 */
public class GenreIterator implements ArchiveIterator {
    private final List<FilmReel> filteredFilms;
    private int currentIndex;

    public GenreIterator(List<FilmReel> films, String genre) {
        this.filteredFilms = new ArrayList<>();
        this.currentIndex = 0;
        
        // Filter films by genre (case-insensitive)
        for (FilmReel film : films) {
            if (film.getGenre().equalsIgnoreCase(genre)) {
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
     * Returns the count of films in this genre.
     */
    public int getCount() {
        return filteredFilms.size();
    }
}
