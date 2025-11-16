package domain.models;

import utilities.MissionType;
import utilities.MissionStatus;

public class Mission {
    private static int nextId = 1;
    
    private int id;
    private String name;
    private MissionType type;
    private MissionStatus status;
    private int difficulty;
    private int teamPower;
    private boolean success;

    public Mission(String name, MissionType type) {
        this.id = nextId++;
        this.name = name;
        this.type = type;
        this.difficulty = type.getDifficulty();
        this.status = MissionStatus.PENDING;
    }

    public void execute(int teamPower) {
        this.teamPower = teamPower;
        this.status = MissionStatus.IN_PROGRESS;
        this.success = teamPower > difficulty;
        this.status = success ? MissionStatus.SUCCESS : MissionStatus.FAILED;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public MissionType getType() { return type; }
    public MissionStatus getStatus() { return status; }
    public int getDifficulty() { return difficulty; }
    public int getTeamPower() { return teamPower; }
    public boolean isSuccess() { return success; }
}
