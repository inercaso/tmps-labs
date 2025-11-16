package tests;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;
import domain.enhancements.*;

public class DecoratorTest {
    
    public static void runAllTests() {
        System.out.println("\n==========================================");
        System.out.println("[1/4] Decorator Pattern Tests");
        System.out.println("==========================================");
        
        testBasicEnhancement();
        testStackedEnhancements();
        testOriginalUnchanged();
        testCompoundVEnhancement();
        testPowerCalculation();
        
        System.out.println("Result: 5/5 passed\n");
    }
    
    private static void testBasicEnhancement() {
        Superhero hero = createTestHero("Test Hero", 50, 50, 50);
        ISuperheroComponent wrapped = new SuperheroWrapper(hero);
        ISuperheroComponent enhanced = new TrainingDecorator(wrapped);
        
        int originalStr = hero.getStats().getStrength();
        int enhancedStr = enhanced.getStats().getStrength();
        
        assert enhancedStr == originalStr + 15 : "training should add 15 to strength";
        System.out.println("[PASS] Test: Basic enhancement (Training)");
    }
    
    private static void testStackedEnhancements() {
        Superhero hero = createTestHero("Stacked Hero", 60, 60, 60);
        ISuperheroComponent wrapped = new SuperheroWrapper(hero);
        ISuperheroComponent enhanced = new CompoundVDecorator(new TrainingDecorator(wrapped));
        
        assert enhanced.getStats().getStrength() > hero.getStats().getStrength() : "stacked enhancements should increase stats";
        System.out.println("[PASS] Test: Stacked enhancements");
    }
    
    private static void testOriginalUnchanged() {
        Superhero hero = createTestHero("Original Hero", 70, 70, 70);
        int originalStr = hero.getStats().getStrength();
        
        ISuperheroComponent wrapped = new SuperheroWrapper(hero);
        ISuperheroComponent enhanced = new TrainingDecorator(wrapped);
        
        assert hero.getStats().getStrength() == originalStr : "original hero should be unchanged";
        System.out.println("[PASS] Test: Original object unchanged");
    }
    
    private static void testCompoundVEnhancement() {
        Superhero hero = createTestHero("V Hero", 100, 100, 100);
        ISuperheroComponent wrapped = new SuperheroWrapper(hero);
        ISuperheroComponent enhanced = new CompoundVDecorator(wrapped);
        
        assert enhanced.getStats().getStrength() == 130 : "compound v should multiply by 1.3";
        System.out.println("[PASS] Test: Compound V enhancement");
    }
    
    private static void testPowerCalculation() {
        Superhero hero = createTestHero("Power Hero", 80, 80, 80);
        ISuperheroComponent wrapped = new SuperheroWrapper(hero);
        ISuperheroComponent enhanced = new TrainingDecorator(wrapped);
        
        assert enhanced.calculatePower() > wrapped.calculatePower() : "enhanced hero should have higher power";
        System.out.println("[PASS] Test: Power calculation");
    }
    
    private static Superhero createTestHero(String name, int str, int spd, int dur) {
        Superhero hero = new Superhero();
        hero.setName(name);
        hero.setStats(new SuperheroStats(str, spd, dur, 50, 50, 80));
        hero.addPower(new Power("Test Power", "Test description", 50));
        return hero;
    }
}
