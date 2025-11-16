package domain.operations;

import domain.models.Mission;
import domain.teams.HeroComponent;
import utilities.MissionType;
import java.util.ArrayList;
import java.util.List;

// facade pattern: subsystem 1 - manages mission creation and execution
public class MissionCoordinator {
    private List<Mission> missions;

    public MissionCoordinator() {
        this.missions = new ArrayList<>();
    }

    public Mission createMission(String name, MissionType type) {
        Mission mission = new Mission(name, type);
        missions.add(mission);
        return mission;
    }

    public void executeMission(Mission mission, HeroComponent team) {
        int teamPower = team.getPowerLevel();
        mission.execute(teamPower);
    }

    public List<Mission> getAllMissions() {
        return new ArrayList<>(missions);
    }

    public List<Mission> getSuccessfulMissions() {
        List<Mission> successful = new ArrayList<>();
        for (Mission m : missions) {
            if (m.isSuccess()) {
                successful.add(m);
            }
        }
        return successful;
    }

    public List<Mission> getFailedMissions() {
        List<Mission> failed = new ArrayList<>();
        for (Mission m : missions) {
            if (!m.isSuccess() && m.getStatus() == utilities.MissionStatus.FAILED) {
                failed.add(m);
            }
        }
        return failed;
    }
}
