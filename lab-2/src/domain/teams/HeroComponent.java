package domain.teams;

// composite pattern: component interface
public interface HeroComponent {
    String getName();
    int getPowerLevel();
    void displayInfo();
    void displayHierarchy(int indent);
    boolean isAvailable();
}
