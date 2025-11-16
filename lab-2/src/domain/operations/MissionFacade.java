package domain.operations;

import domain.models.Mission;
import domain.teams.HeroComponent;
import utilities.MissionType;

// facade pattern: main facade - provides unified interface for mission operations
public class MissionFacade {
    private MissionCoordinator coordinator;
    private MissionReporter reporter;

    public MissionFacade() {
        this.coordinator = new MissionCoordinator();
        this.reporter = new MissionReporter();
    }

    public void executeMission(String missionName, MissionType type, HeroComponent team) {
        System.out.println("\n> Mission Coordinator: Creating " + type + " mission...");
        Mission mission = coordinator.createMission(missionName, type);
        
        System.out.println("> Mission Coordinator: Assigning " + team.getName() + "...");
        coordinator.executeMission(mission, team);
        
        System.out.println("> Facade: Coordinating subsystems...\n");
        reporter.displayMissionReport(mission);
    }

    public void displayStatistics() {
        reporter.displayStatistics(coordinator.getAllMissions());
    }

    public int getTotalMissions() {
        return coordinator.getAllMissions().size();
    }

    public int getSuccessfulMissions() {
        return coordinator.getSuccessfulMissions().size();
    }
}
