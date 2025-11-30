package strategy;

import model.Condition;

/**
 * Holds the result of a restoration operation.
 */
public class RestorationResult {
    private final boolean success;
    private final String strategyUsed;
    private final Condition conditionBefore;
    private final Condition conditionAfter;
    private final String notes;

    public RestorationResult(boolean success, String strategyUsed,
                             Condition conditionBefore, Condition conditionAfter,
                             String notes) {
        this.success = success;
        this.strategyUsed = strategyUsed;
        this.conditionBefore = conditionBefore;
        this.conditionAfter = conditionAfter;
        this.notes = notes;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getStrategyUsed() {
        return strategyUsed;
    }

    public Condition getConditionBefore() {
        return conditionBefore;
    }

    public Condition getConditionAfter() {
        return conditionAfter;
    }

    public String getNotes() {
        return notes;
    }

    /**
     * Calculate the improvement percentage.
     */
    public int getImprovementPercentage() {
        int before = conditionBefore.getOverallScore();
        int after = conditionAfter.getOverallScore();
        if (before == 0) return 0;
        return ((after - before) * 100) / before;
    }

    @Override
    public String toString() {
        return String.format(
            "Restoration %s using %s\n  Before: %s\n  After:  %s\n  Improvement: +%d%%\n  Notes: %s",
            success ? "successful" : "failed",
            strategyUsed,
            conditionBefore.toString(),
            conditionAfter.toString(),
            getImprovementPercentage(),
            notes
        );
    }
}
