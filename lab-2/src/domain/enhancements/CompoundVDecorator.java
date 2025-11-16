package domain.enhancements;

import domain.models.SuperheroStats;
import domain.models.Power;
import java.util.List;
import java.util.ArrayList;

// decorator pattern: concrete decorator - compound v enhancement
public class CompoundVDecorator extends EnhancementDecorator {
    private static final double MULTIPLIER = 1.3;

    public CompoundVDecorator(ISuperheroComponent hero) {
        super(hero);
    }

    @Override
    public SuperheroStats getStats() {
        SuperheroStats baseStats = wrappedHero.getStats();
        return new SuperheroStats(
            (int)(baseStats.getStrength() * MULTIPLIER),
            (int)(baseStats.getSpeed() * MULTIPLIER),
            (int)(baseStats.getDurability() * MULTIPLIER),
            baseStats.getIntelligence(),
            baseStats.getCharisma(),
            Math.max(0, baseStats.getStability() - 20)
        );
    }

    @Override
    public List<Power> getPowers() {
        List<Power> powers = new ArrayList<>(wrappedHero.getPowers());
        powers.add(new Power("V-Enhanced State", "Temporary superhuman boost", 60));
        return powers;
    }

    @Override
    public int calculatePower() {
        return (int)(wrappedHero.calculatePower() * MULTIPLIER) + 50;
    }

    @Override
    public String getEnhancementName() {
        return "Compound V";
    }

    @Override
    public void displayInfo() {
        wrappedHero.displayInfo();
        System.out.println("Enhancement: " + getEnhancementName() + " (+30% physical stats)");
    }
}
