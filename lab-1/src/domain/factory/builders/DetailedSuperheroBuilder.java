package domain.factory.builders;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;

/**
 * BUILDER PATTERN - Concrete Implementation
 * Detailed step-by-step superhero construction
 */
public class DetailedSuperheroBuilder implements SuperheroBuilder {
    private Superhero superhero;
    
    public DetailedSuperheroBuilder() {
        this.superhero = new Superhero();
    }
    
    @Override
    public SuperheroBuilder setName(String name) {
        superhero.setName(name);
        return this;
    }
    
    @Override
    public SuperheroBuilder setHeroType(String heroType) {
        superhero.setHeroType(heroType);
        return this;
    }
    
    @Override
    public SuperheroBuilder setStats(int strength, int speed, int durability,
                                     int intelligence, int charisma, int stability) {
        SuperheroStats stats = new SuperheroStats(
            strength, speed, durability, intelligence, charisma, stability
        );
        superhero.setStats(stats);
        return this;
    }
    
    @Override
    public SuperheroBuilder addPower(String name, String description, int level) {
        Power power = new Power(name, description, level);
        superhero.addPower(power);
        return this;
    }
    
    @Override
    public SuperheroBuilder setCostume(String costume) {
        superhero.setCostume(costume);
        return this;
    }
    
    @Override
    public SuperheroBuilder setBackstory(String backstory) {
        superhero.setBackstory(backstory);
        return this;
    }
    
    @Override
    public SuperheroBuilder setWeakness(String weakness) {
        superhero.setWeakness(weakness);
        return this;
    }
    
    @Override
    public SuperheroBuilder setTeam(String team) {
        superhero.setTeam(team);
        return this;
    }
    
    @Override
    public SuperheroBuilder setBrandValue(int brandValue) {
        superhero.setBrandValue(brandValue);
        return this;
    }
    
    @Override
    public SuperheroBuilder setPublicRating(int rating) {
        superhero.setPublicRating(rating);
        return this;
    }
    
    @Override
    public Superhero build() {
        return superhero;
    }
}
