package core;

import iterator.*;
import memento.FilmSnapshot;
import memento.RestorationHistory;
import model.FilmReel;
import strategy.RestorationResult;
import strategy.RestorationStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Central class for managing the film archive.
 * Coordinates between Iterator, Memento, and Strategy patterns.
 */
public class FilmArchive {
    private final List<FilmReel> films;
    private final RestorationHistory history;
    private final String archiveName;

    public FilmArchive(String archiveName) {
        this.archiveName = archiveName;
        this.films = new ArrayList<>();
        this.history = new RestorationHistory();
    }

    // --- Collection Management ---

    public void addFilm(FilmReel film) {
        films.add(film);
    }

    public FilmReel getFilmById(String id) {
        for (FilmReel film : films) {
            if (film.getId().equals(id)) {
                return film;
            }
        }
        return null;
    }

    public List<FilmReel> getAllFilms() {
        return new ArrayList<>(films);
    }

    public int getFilmCount() {
        return films.size();
    }

    // --- Iterator Pattern: Create different iterators ---

    public ArchiveIterator createSequentialIterator() {
        return new SequentialIterator(films);
    }

    public ArchiveIterator createDecadeIterator(int decade) {
        return new DecadeIterator(films, decade);
    }

    public ArchiveIterator createGenreIterator(String genre) {
        return new GenreIterator(films, genre);
    }

    public ArchiveIterator createConditionIterator() {
        return new ConditionIterator(films);
    }

    // --- Memento Pattern: Snapshot management ---

    public void saveSnapshot(FilmReel film, String stage, String archivist) {
        history.save(film, stage, archivist);
    }

    public List<FilmSnapshot> getFilmHistory(FilmReel film) {
        return history.getHistory(film.getId());
    }

    public void restoreFilmToSnapshot(FilmReel film, int snapshotIndex) {
        history.restoreTo(film, snapshotIndex);
    }

    // --- Strategy Pattern: Apply restoration ---

    public RestorationResult restore(FilmReel film, RestorationStrategy strategy, 
                                     String archivist) {
        // Save pre-restoration snapshot
        saveSnapshot(film, "Pre-" + strategy.getName(), archivist);
        
        // Apply restoration strategy
        RestorationResult result = strategy.restore(film);
        
        // Save post-restoration snapshot
        saveSnapshot(film, "Post-" + strategy.getName(), archivist);
        
        return result;
    }

    // --- Statistics ---

    public int getRestoredCount() {
        int count = 0;
        for (FilmReel film : films) {
            if (history.getSnapshotCount(film.getId()) > 1) {
                count++;
            }
        }
        return count;
    }

    public int getCriticalCount() {
        int count = 0;
        for (FilmReel film : films) {
            if (film.getCondition().getOverallScore() <= 3) {
                count++;
            }
        }
        return count;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public RestorationHistory getHistory() {
        return history;
    }
}
