package memento;

import model.Condition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Memento class - stores a snapshot of film condition at a point in time.
 * Immutable to preserve the integrity of historical records.
 */
public class FilmSnapshot {
    private final LocalDate timestamp;
    private final Condition condition;
    private final String restorationStage;
    private final String archivist;

    public FilmSnapshot(LocalDate timestamp, Condition condition, 
                        String restorationStage, String archivist) {
        this.timestamp = timestamp;
        this.condition = condition;
        this.restorationStage = restorationStage;
        this.archivist = archivist;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getRestorationStage() {
        return restorationStage;
    }

    public String getArchivist() {
        return archivist;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        return String.format(
            "%s - %s\n    %s\n    Archivist: %s",
            timestamp.format(formatter),
            restorationStage,
            condition.toString(),
            archivist
        );
    }
}
