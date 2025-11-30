package strategy;

import model.Condition;
import model.FilmReel;

/**
 * Digital restoration using AI-enhanced scanning and color correction.
 * Best for: faded colors, general quality improvement.
 * Cost: Medium | Time: 2 weeks
 */
public class DigitalRemasterStrategy implements RestorationStrategy {

    @Override
    public RestorationResult restore(FilmReel film) {
        Condition before = film.getCondition().copy();
        
        // Digital remaster improves image and color significantly, audio moderately
        Condition improved = before.improve(
            3,  // image improvement
            1,  // audio improvement
            3,  // color improvement
            "Digitally restored",
            "AI upscaling and color correction applied"
        );
        
        film.setCondition(improved);
        
        return new RestorationResult(
            true,
            getName(),
            before,
            improved,
            "4K scan completed. Neural network color restoration applied."
        );
    }

    @Override
    public double estimateCost() {
        return 5000.00;
    }

    @Override
    public String estimateTime() {
        return "2 weeks";
    }

    @Override
    public String getDescription() {
        return "High-resolution scanning with AI-enhanced restoration. " +
               "Ideal for faded colors and general quality improvement.";
    }

    @Override
    public String getName() {
        return "Digital Remaster";
    }
}
