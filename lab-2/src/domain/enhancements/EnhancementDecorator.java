package domain.enhancements;

import domain.models.SuperheroStats;
import domain.models.Power;
import java.util.List;
import java.util.ArrayList;

// decorator pattern: abstract decorator
public abstract class EnhancementDecorator implements ISuperheroComponent {
    protected ISuperheroComponent wrappedHero;

    public EnhancementDecorator(ISuperheroComponent hero) {
        this.wrappedHero = hero;
    }

    @Override
    public String getName() {
        return wrappedHero.getName();
    }

    @Override
    public SuperheroStats getStats() {
        return wrappedHero.getStats();
    }

    @Override
    public List<Power> getPowers() {
        return wrappedHero.getPowers();
    }

    @Override
    public int calculatePower() {
        return wrappedHero.calculatePower();
    }

    @Override
    public void displayInfo() {
        wrappedHero.displayInfo();
    }

    public abstract String getEnhancementName();
}
