package domain.enhancements;

import domain.models.SuperheroStats;
import domain.models.Power;
import java.util.List;

// decorator pattern: component interface
public interface ISuperheroComponent {
    String getName();
    SuperheroStats getStats();
    List<Power> getPowers();
    int calculatePower();
    void displayInfo();
}
