package domain.factory.builders;

import domain.models.Superhero;
import domain.models.SuperheroStats;
import domain.models.Power;

/**
 * BUILDER PATTERN - Interface
 * Defines the steps for building a complex Superhero object
 */
public interface SuperheroBuilder {
    SuperheroBuilder setName(String name);
    SuperheroBuilder setHeroType(String heroType);
    SuperheroBuilder setStats(int strength, int speed, int durability, 
                              int intelligence, int charisma, int stability);
    SuperheroBuilder addPower(String name, String description, int level);
    SuperheroBuilder setCostume(String costume);
    SuperheroBuilder setBackstory(String backstory);
    SuperheroBuilder setWeakness(String weakness);
    SuperheroBuilder setTeam(String team);
    SuperheroBuilder setBrandValue(int brandValue);
    SuperheroBuilder setPublicRating(int rating);
    Superhero build();
}
