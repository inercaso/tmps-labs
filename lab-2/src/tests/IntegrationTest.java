package tests;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;
import domain.enhancements.*;
import domain.teams.*;
import domain.operations.*;
import utilities.MissionType;

public class IntegrationTest {
    
    public static void runAllTests() {
        System.out.println("\n==========================================");
        System.out.println("[4/4] Integration Tests (All Patterns)");
        System.out.println("==========================================");
        
        testEnhancedHeroInTeam();
        testTeamMissionWithFacade();
        testAllPatternsTogether();
        
        System.out.println("Result: 3/3 passed\n");
    }
    
    private static void testEnhancedHeroInTeam() {
        Superhero hero = createTestHero("Integration Hero", 80, 80, 80);
        ISuperheroComponent enhanced = new TrainingDecorator(new SuperheroWrapper(hero));
        
        HeroTeam team = new HeroTeam("Enhanced Team");
        team.addMember(new IndividualHero(enhanced));
        
        assert team.getPowerLevel() > 0 : "enhanced hero should work in team";
        System.out.println("[PASS] Test: Enhanced hero in team (Decorator + Composite)");
    }
    
    private static void testTeamMissionWithFacade() {
        Superhero hero1 = createTestHero("Team Hero 1", 90, 90, 90);
        Superhero hero2 = createTestHero("Team Hero 2", 85, 85, 85);
        
        HeroTeam team = new HeroTeam("Integrated Team");
        team.addMember(new IndividualHero(new SuperheroWrapper(hero1)));
        team.addMember(new IndividualHero(new SuperheroWrapper(hero2)));
        
        MissionFacade facade = new MissionFacade();
        facade.executeMission("Integration Mission", MissionType.RESCUE, team);
        
        assert facade.getTotalMissions() > 0 : "team mission should work with facade";
        System.out.println("[PASS] Test: Team mission with facade (Composite + Facade)");
    }
    
    private static void testAllPatternsTogether() {
        Superhero hero1 = createTestHero("Pattern Hero 1", 70, 70, 70);
        Superhero hero2 = createTestHero("Pattern Hero 2", 80, 80, 80);
        
        ISuperheroComponent enhanced1 = new CompoundVDecorator(new SuperheroWrapper(hero1));
        ISuperheroComponent enhanced2 = new TrainingDecorator(new SuperheroWrapper(hero2));
        
        HeroTeam team = new HeroTeam("All Patterns Team");
        team.addMember(new IndividualHero(enhanced1));
        team.addMember(new IndividualHero(enhanced2));
        
        MissionFacade facade = new MissionFacade();
        facade.executeMission("Full Integration", MissionType.COMBAT, team);
        
        assert facade.getSuccessfulMissions() > 0 : "all patterns should work together";
        System.out.println("[PASS] Test: All patterns working together");
    }
    
    private static Superhero createTestHero(String name, int str, int spd, int dur) {
        Superhero hero = new Superhero();
        hero.setName(name);
        hero.setStats(new SuperheroStats(str, spd, dur, 50, 50, 80));
        hero.addPower(new Power("Integration Power", "Test", 50));
        return hero;
    }
}
