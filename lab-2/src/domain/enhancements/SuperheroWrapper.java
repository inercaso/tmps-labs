package domain.enhancements;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;
import java.util.List;

// decorator pattern: concrete component (wraps lab-1 superhero)
public class SuperheroWrapper implements ISuperheroComponent {
    protected Superhero superhero;

    public SuperheroWrapper(Superhero superhero) {
        this.superhero = superhero;
    }

    @Override
    public String getName() {
        return superhero.getName();
    }

    @Override
    public SuperheroStats getStats() {
        return superhero.getStats();
    }

    @Override
    public List<Power> getPowers() {
        return superhero.getPowers();
    }

    @Override
    public int calculatePower() {
        SuperheroStats stats = superhero.getStats();
        int avgStats = (stats.getStrength() + stats.getSpeed() + 
                       stats.getDurability() + stats.getIntelligence()) / 4;
        return avgStats + (superhero.getPowers().size() * 10);
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Stats: " + getStats());
        System.out.println("Powers: " + getPowers().size());
        System.out.println("Total Power: " + calculatePower());
    }

    public Superhero getSuperhero() {
        return superhero;
    }
}
