package iterator;

import model.FilmReel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Iterates through films sorted by condition (worst first).
 * Useful for prioritizing restoration work.
 */
public class ConditionIterator implements ArchiveIterator {
    private final List<FilmReel> sortedFilms;
    private int currentIndex;

    public ConditionIterator(List<FilmReel> films) {
        this.sortedFilms = new ArrayList<>(films);
        this.currentIndex = 0;
        
        // Sort by overall condition score (ascending - worst first)
        sortedFilms.sort(Comparator.comparingInt(
            film -> film.getCondition().getOverallScore()
        ));
    }

    @Override
    public boolean hasNext() {
        return currentIndex < sortedFilms.size();
    }

    @Override
    public FilmReel next() {
        if (!hasNext()) {
            return null;
        }
        return sortedFilms.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }

    /**
     * Returns films that are in critical condition (score <= 3).
     */
    public List<FilmReel> getCriticalFilms() {
        List<FilmReel> critical = new ArrayList<>();
        for (FilmReel film : sortedFilms) {
            if (film.getCondition().getOverallScore() <= 3) {
                critical.add(film);
            }
        }
        return critical;
    }
}
