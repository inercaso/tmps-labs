package model;

import memento.FilmSnapshot;
import java.time.LocalDate;

/**
 * Represents a vintage film reel in the archive.
 * Acts as the Originator in the Memento pattern.
 */
public class FilmReel {
    private final String id;
    private final String title;
    private final String director;
    private final int year;
    private final String genre;
    private final String country;
    private Condition condition;

    public FilmReel(String id, String title, String director, int year, 
                    String genre, String country, Condition condition) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.country = country;
        this.condition = condition;
    }

    // --- Memento Pattern: Originator methods ---

    /**
     * Creates a snapshot of the current film condition.
     */
    public FilmSnapshot createSnapshot(String stage, String archivist) {
        return new FilmSnapshot(
            LocalDate.now(),
            condition.copy(),
            stage,
            archivist
        );
    }

    /**
     * Restores film condition from a snapshot.
     */
    public void restoreFromSnapshot(FilmSnapshot snapshot) {
        this.condition = snapshot.getCondition().copy();
    }

    // --- Getters ---

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }

    public Condition getCondition() {
        return condition;
    }

    /**
     * Returns the decade of the film (e.g., 1920 for 1922).
     */
    public int getDecade() {
        return (year / 10) * 10;
    }

    // --- Setters ---

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    // --- Display ---

    @Override
    public String toString() {
        return String.format("%s (%d) - %s", title, year, director);
    }

    /**
     * Detailed display for CLI output.
     */
    public String toDetailedString() {
        return String.format(
            "%s (%d) - %s\n     Condition: %s | %s | %s",
            title, year, director,
            condition.getProgressBar(), genre, country
        );
    }
}
