package strategy;

import model.Condition;
import model.FilmReel;

/**
 * Chemical treatment for physical film decay and deterioration.
 * Best for: vinegar syndrome, brittle film base, mold.
 * Cost: High | Time: 1 month
 */
public class ChemicalTreatmentStrategy implements RestorationStrategy {

    @Override
    public RestorationResult restore(FilmReel film) {
        Condition before = film.getCondition().copy();
        
        // Chemical treatment focuses on physical preservation
        // Moderate improvement to all qualities as film stabilizes
        Condition improved = before.improve(
            1,  // image improvement (stops further decay)
            1,  // audio improvement
            1,  // color improvement
            "Chemically stabilized",
            "Acetate decay halted. Film base reinforced."
        );
        
        film.setCondition(improved);
        
        return new RestorationResult(
            true,
            getName(),
            before,
            improved,
            "Vinegar syndrome treated. Humidity damage reversed. " +
            "Film stored in climate-controlled vault."
        );
    }

    @Override
    public double estimateCost() {
        return 8000.00;
    }

    @Override
    public String estimateTime() {
        return "1 month";
    }

    @Override
    public String getDescription() {
        return "Physical and chemical treatment for deteriorating film stock. " +
               "Essential for films with vinegar syndrome or mold damage.";
    }

    @Override
    public String getName() {
        return "Chemical Treatment";
    }
}
