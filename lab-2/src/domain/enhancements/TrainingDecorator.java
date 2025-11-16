package domain.enhancements;

import domain.models.SuperheroStats;
import domain.models.Power;
import java.util.List;
import java.util.ArrayList;

// decorator pattern: concrete decorator - training enhancement
public class TrainingDecorator extends EnhancementDecorator {
    private static final int BONUS = 15;

    public TrainingDecorator(ISuperheroComponent hero) {
        super(hero);
    }

    @Override
    public SuperheroStats getStats() {
        SuperheroStats baseStats = wrappedHero.getStats();
        return new SuperheroStats(
            baseStats.getStrength() + BONUS,
            baseStats.getSpeed() + BONUS,
            baseStats.getDurability() + BONUS,
            baseStats.getIntelligence() + BONUS,
            baseStats.getCharisma() + BONUS,
            baseStats.getStability()
        );
    }

    @Override
    public List<Power> getPowers() {
        List<Power> powers = new ArrayList<>(wrappedHero.getPowers());
        powers.add(new Power("Advanced Combat Training", "Enhanced fighting techniques", 50));
        return powers;
    }

    @Override
    public int calculatePower() {
        return wrappedHero.calculatePower() + 75;
    }

    @Override
    public String getEnhancementName() {
        return "Training";
    }

    @Override
    public void displayInfo() {
        wrappedHero.displayInfo();
        System.out.println("Enhancement: " + getEnhancementName() + " (+" + BONUS + " to all stats)");
    }
}
