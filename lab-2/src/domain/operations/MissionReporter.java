package domain.operations;

import domain.models.Mission;
import utilities.MissionType;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

// facade pattern: subsystem 2 - generates reports and statistics
public class MissionReporter {
    
    public void displayMissionReport(Mission mission) {
        System.out.println("\n========================================");
        System.out.println("     MISSION EXECUTION REPORT");
        System.out.println("========================================");
        System.out.println("Mission: " + mission.getName());
        System.out.println("Type: " + mission.getType());
        System.out.println("Difficulty: " + mission.getDifficulty());
        System.out.println("Team Power: " + mission.getTeamPower());
        System.out.println("Status: " + (mission.isSuccess() ? "[SUCCESS]" : "[FAILED]"));
        System.out.println("Reason: " + (mission.isSuccess() ? 
            "Team power exceeds difficulty" : "Insufficient team power"));
        System.out.println("========================================");
    }

    public void displayStatistics(List<Mission> missions) {
        if (missions.isEmpty()) {
            System.out.println("No missions completed yet.");
            return;
        }

        int total = missions.size();
        int successful = 0;
        Map<MissionType, Integer> typeCount = new HashMap<>();
        Map<MissionType, Integer> typeSuccess = new HashMap<>();

        for (Mission m : missions) {
            if (m.isSuccess()) successful++;
            
            MissionType type = m.getType();
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
            if (m.isSuccess()) {
                typeSuccess.put(type, typeSuccess.getOrDefault(type, 0) + 1);
            }
        }

        System.out.println("\n========================================");
        System.out.println("       MISSION STATISTICS");
        System.out.println("========================================");
        System.out.println("\n--- Overall Stats ---");
        System.out.println("Total Missions: " + total);
        System.out.println("Successful: " + successful + " (" + (successful * 100 / total) + "%)");
        System.out.println("Failed: " + (total - successful) + " (" + ((total - successful) * 100 / total) + "%)");
        System.out.println("---------------------");

        System.out.println("\n--- By Mission Type ---");
        for (MissionType type : MissionType.values()) {
            if (typeCount.containsKey(type)) {
                int count = typeCount.get(type);
                int success = typeSuccess.getOrDefault(type, 0);
                int successRate = (success * 100) / count;
                System.out.println(type + ": " + count + " missions, " + successRate + "% success");
            }
        }
        System.out.println("-----------------------\n");
    }
}
