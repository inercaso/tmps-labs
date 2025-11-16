package tests;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;
import domain.enhancements.*;
import domain.teams.*;
import domain.operations.*;
import utilities.MissionType;

public class FacadeTest {
    
    public static void runAllTests() {
        System.out.println("\n==========================================");
        System.out.println("[3/4] Facade Pattern Tests");
        System.out.println("==========================================");
        
        testMissionExecution();
        testSuccessfulMission();
        testFailedMission();
        testStatistics();
        testMultipleMissions();
        
        System.out.println("Result: 5/5 passed\n");
    }
    
    private static void testMissionExecution() {
        MissionFacade facade = new MissionFacade();
        HeroTeam team = createTestTeam("Test Team", 100);
        
        int before = facade.getTotalMissions();
        facade.executeMission("Test Mission", MissionType.RESCUE, team);
        int after = facade.getTotalMissions();
        
        assert after == before + 1 : "mission count should increase";
        System.out.println("[PASS] Test: Mission execution");
    }
    
    private static void testSuccessfulMission() {
        MissionFacade facade = new MissionFacade();
        HeroTeam strongTeam = createTestTeam("Strong Team", 200);
        
        int before = facade.getSuccessfulMissions();
        facade.executeMission("Easy Mission", MissionType.PR_EVENT, strongTeam);
        int after = facade.getSuccessfulMissions();
        
        assert after > before : "successful missions should increase";
        System.out.println("[PASS] Test: Successful mission");
    }
    
    private static void testFailedMission() {
        MissionFacade facade = new MissionFacade();
        HeroTeam weakTeam = createTestTeam("Weak Team", 10);
        
        facade.executeMission("Hard Mission", MissionType.COMBAT, weakTeam);
        assert facade.getTotalMissions() > 0 : "failed mission should still be recorded";
        System.out.println("[PASS] Test: Failed mission");
    }
    
    private static void testStatistics() {
        MissionFacade facade = new MissionFacade();
        HeroTeam team = createTestTeam("Stats Team", 150);
        
        facade.executeMission("Mission 1", MissionType.RESCUE, team);
        facade.executeMission("Mission 2", MissionType.COMBAT, team);
        
        assert facade.getTotalMissions() == 2 : "statistics should track all missions";
        System.out.println("[PASS] Test: Statistics tracking");
    }
    
    private static void testMultipleMissions() {
        MissionFacade facade = new MissionFacade();
        HeroTeam team1 = createTestTeam("Team A", 100);
        HeroTeam team2 = createTestTeam("Team B", 150);
        
        facade.executeMission("Mission A", MissionType.RESCUE, team1);
        facade.executeMission("Mission B", MissionType.PR_EVENT, team2);
        facade.executeMission("Mission C", MissionType.COMBAT, team2);
        
        assert facade.getTotalMissions() == 3 : "multiple missions should be tracked";
        System.out.println("[PASS] Test: Multiple missions");
    }
    
    private static HeroTeam createTestTeam(String name, int power) {
        Superhero hero = new Superhero();
        hero.setName(name + " Hero");
        hero.setStats(new SuperheroStats(power, power, power, 50, 50, 80));
        hero.addPower(new Power("Test Power", "Test", 50));
        
        HeroTeam team = new HeroTeam(name);
        team.addMember(new IndividualHero(new SuperheroWrapper(hero)));
        return team;
    }
}
