package memento;

import model.FilmReel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Caretaker class - manages the history of snapshots for all films.
 * Responsible for storing and retrieving FilmSnapshot mementos.
 */
public class RestorationHistory {
    private final Map<String, List<FilmSnapshot>> history;

    public RestorationHistory() {
        this.history = new HashMap<>();
    }

    /**
     * Saves a snapshot for a given film.
     */
    public void save(FilmReel film, String stage, String archivist) {
        String filmId = film.getId();
        history.computeIfAbsent(filmId, k -> new ArrayList<>());
        
        FilmSnapshot snapshot = film.createSnapshot(stage, archivist);
        history.get(filmId).add(snapshot);
    }

    /**
     * Gets the complete restoration history for a film.
     */
    public List<FilmSnapshot> getHistory(String filmId) {
        return history.getOrDefault(filmId, new ArrayList<>());
    }

    /**
     * Gets the most recent snapshot for a film.
     */
    public FilmSnapshot getLatest(String filmId) {
        List<FilmSnapshot> snapshots = history.get(filmId);
        if (snapshots == null || snapshots.isEmpty()) {
            return null;
        }
        return snapshots.get(snapshots.size() - 1);
    }

    /**
     * Restores a film to a specific snapshot index.
     */
    public void restoreTo(FilmReel film, int snapshotIndex) {
        List<FilmSnapshot> snapshots = history.get(film.getId());
        if (snapshots != null && snapshotIndex >= 0 && snapshotIndex < snapshots.size()) {
            film.restoreFromSnapshot(snapshots.get(snapshotIndex));
        }
    }

    /**
     * Compares two snapshots and returns the improvement percentage.
     */
    public int compareSnapshots(FilmSnapshot before, FilmSnapshot after) {
        int scoreBefore = before.getCondition().getOverallScore();
        int scoreAfter = after.getCondition().getOverallScore();
        
        if (scoreBefore == 0) return 0;
        return ((scoreAfter - scoreBefore) * 100) / scoreBefore;
    }

    /**
     * Gets the number of snapshots for a film.
     */
    public int getSnapshotCount(String filmId) {
        List<FilmSnapshot> snapshots = history.get(filmId);
        return snapshots == null ? 0 : snapshots.size();
    }

    /**
     * Checks if a film has any restoration history.
     */
    public boolean hasHistory(String filmId) {
        return history.containsKey(filmId) && !history.get(filmId).isEmpty();
    }
}
