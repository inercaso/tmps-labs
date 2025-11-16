package domain.teams;

import domain.enhancements.ISuperheroComponent;

// composite pattern: leaf - represents individual hero
public class IndividualHero implements HeroComponent {
    private ISuperheroComponent hero;
    private boolean available;

    public IndividualHero(ISuperheroComponent hero) {
        this.hero = hero;
        this.available = true;
    }

    @Override
    public String getName() {
        return hero.getName();
    }

    @Override
    public int getPowerLevel() {
        return hero.calculatePower();
    }

    @Override
    public void displayInfo() {
        System.out.println("Hero: " + getName() + " [Power: " + getPowerLevel() + "]");
    }

    @Override
    public void displayHierarchy(int indent) {
        StringBuilder spacing = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            spacing.append(" ");
        }
        System.out.println(spacing.toString() + "└─ " + getName() + " [Power: " + getPowerLevel() + "]");
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ISuperheroComponent getHero() {
        return hero;
    }
}
