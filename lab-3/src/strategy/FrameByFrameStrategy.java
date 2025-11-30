package strategy;

import model.Condition;
import model.FilmReel;

/**
 * Frame-by-frame manual restoration by expert archivists.
 * Best for: severe scratches, missing frames, heavy damage.
 * Cost: Very High | Time: 3 months
 */
public class FrameByFrameStrategy implements RestorationStrategy {

    @Override
    public RestorationResult restore(FilmReel film) {
        Condition before = film.getCondition().copy();
        
        // Frame-by-frame is the most thorough - significant improvements across all metrics
        Condition improved = before.improve(
            4,  // image improvement (scratches removed, frames repaired)
            2,  // audio improvement
            2,  // color improvement
            "Fully restored",
            "Each frame individually examined and repaired by experts"
        );
        
        film.setCondition(improved);
        
        return new RestorationResult(
            true,
            getName(),
            before,
            improved,
            "Manual restoration complete. 847 frames repaired. " +
            "12 frames reconstructed from adjacent frames."
        );
    }

    @Override
    public double estimateCost() {
        return 15000.00;
    }

    @Override
    public String estimateTime() {
        return "3 months";
    }

    @Override
    public String getDescription() {
        return "Meticulous frame-by-frame restoration by expert archivists. " +
               "The most thorough but time-consuming method.";
    }

    @Override
    public String getName() {
        return "Frame-by-Frame";
    }
}
