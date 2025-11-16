package utilities;

public enum MissionType {
    RESCUE(60, "Save civilians from danger"),
    COMBAT(80, "Fight villains and threats"),
    PR_EVENT(40, "Public relations and media appearances");

    private final int difficulty;
    private final String description;

    MissionType(int difficulty, String description) {
        this.difficulty = difficulty;
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }
}
