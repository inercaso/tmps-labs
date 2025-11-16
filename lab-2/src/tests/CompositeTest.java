package tests;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;
import domain.enhancements.*;
import domain.teams.*;

public class CompositeTest {
    
    public static void runAllTests() {
        System.out.println("\n==========================================");
        System.out.println("[2/4] Composite Pattern Tests");
        System.out.println("==========================================");
        
        testIndividualHero();
        testTeamCreation();
        testPowerAggregation();
        testNestedTeams();
        testUniformInterface();
        
        System.out.println("Result: 5/5 passed\n");
    }
    
    private static void testIndividualHero() {
        Superhero hero = createTestHero("Solo Hero", 80, 80, 80);
        ISuperheroComponent wrapped = new SuperheroWrapper(hero);
        IndividualHero individual = new IndividualHero(wrapped);
        
        assert individual.getName().equals("Solo Hero") : "individual hero name should match";
        assert individual.getPowerLevel() > 0 : "individual hero should have power";
        System.out.println("[PASS] Test: Individual hero operations");
    }
    
    private static void testTeamCreation() {
        HeroTeam team = new HeroTeam("Test Team");
        Superhero hero1 = createTestHero("Hero 1", 70, 70, 70);
        Superhero hero2 = createTestHero("Hero 2", 80, 80, 80);
        
        team.addMember(new IndividualHero(new SuperheroWrapper(hero1)));
        team.addMember(new IndividualHero(new SuperheroWrapper(hero2)));
        
        assert team.getMemberCount() == 2 : "team should have 2 members";
        System.out.println("[PASS] Test: Team creation and member addition");
    }
    
    private static void testPowerAggregation() {
        HeroTeam team = new HeroTeam("Power Team");
        Superhero hero1 = createTestHero("Hero A", 100, 100, 100);
        Superhero hero2 = createTestHero("Hero B", 100, 100, 100);
        
        IndividualHero member1 = new IndividualHero(new SuperheroWrapper(hero1));
        IndividualHero member2 = new IndividualHero(new SuperheroWrapper(hero2));
        
        int individual1 = member1.getPowerLevel();
        int individual2 = member2.getPowerLevel();
        
        team.addMember(member1);
        team.addMember(member2);
        
        int teamPower = team.getPowerLevel();
        assert teamPower > (individual1 + individual2) : "team power should include synergy bonus";
        System.out.println("[PASS] Test: Power aggregation with synergy");
    }
    
    private static void testNestedTeams() {
        HeroTeam mainTeam = new HeroTeam("Main Team");
        HeroTeam subTeam = new HeroTeam("Sub Team");
        
        Superhero hero = createTestHero("Nested Hero", 90, 90, 90);
        subTeam.addMember(new IndividualHero(new SuperheroWrapper(hero)));
        mainTeam.addMember(subTeam);
        
        assert mainTeam.getMemberCount() == 1 : "main team should contain sub team";
        assert mainTeam.getPowerLevel() > 0 : "nested structure should calculate power";
        System.out.println("[PASS] Test: Nested teams");
    }
    
    private static void testUniformInterface() {
        HeroComponent individual = new IndividualHero(new SuperheroWrapper(createTestHero("Solo", 50, 50, 50)));
        HeroComponent team = new HeroTeam("Team");
        
        assert individual.getPowerLevel() >= 0 : "uniform interface for individual";
        assert team.getPowerLevel() >= 0 : "uniform interface for team";
        System.out.println("[PASS] Test: Uniform interface");
    }
    
    private static Superhero createTestHero(String name, int str, int spd, int dur) {
        Superhero hero = new Superhero();
        hero.setName(name);
        hero.setStats(new SuperheroStats(str, spd, dur, 50, 50, 80));
        hero.addPower(new Power("Test Power", "Test", 50));
        return hero;
    }
}
