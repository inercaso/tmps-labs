package model;

/**
 * Represents the physical and visual condition of a film reel.
 * Immutable class to ensure condition snapshots remain unchanged.
 */
public class Condition {
    private final int imageQuality;    // 1-10
    private final int audioQuality;    // 1-10
    private final int colorFading;     // 1-10 (10 = no fading)
    private final String physicalState;
    private final String notes;

    public Condition(int imageQuality, int audioQuality, int colorFading, 
                     String physicalState, String notes) {
        this.imageQuality = clamp(imageQuality);
        this.audioQuality = clamp(audioQuality);
        this.colorFading = clamp(colorFading);
        this.physicalState = physicalState;
        this.notes = notes;
    }

    private int clamp(int value) {
        return Math.max(1, Math.min(10, value));
    }

    public int getImageQuality() {
        return imageQuality;
    }

    public int getAudioQuality() {
        return audioQuality;
    }

    public int getColorFading() {
        return colorFading;
    }

    public String getPhysicalState() {
        return physicalState;
    }

    public String getNotes() {
        return notes;
    }

    /**
     * Calculate overall condition score (average of all quality metrics).
     */
    public int getOverallScore() {
        return (imageQuality + audioQuality + colorFading) / 3;
    }

    /**
     * Create a copy with improved qualities based on given deltas.
     */
    public Condition improve(int imageDelta, int audioDelta, int colorDelta, 
                             String newState, String newNotes) {
        return new Condition(
            imageQuality + imageDelta,
            audioQuality + audioDelta,
            colorFading + colorDelta,
            newState,
            newNotes
        );
    }

    /**
     * Create a deep copy of this condition.
     */
    public Condition copy() {
        return new Condition(imageQuality, audioQuality, colorFading, 
                            physicalState, notes);
    }

    /**
     * Generate a visual progress bar for the overall condition.
     */
    public String getProgressBar() {
        int score = getOverallScore();
        StringBuilder bar = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            bar.append(i <= score ? "\u2588" : "\u2591");
        }
        return bar.toString() + " " + score + "/10";
    }

    @Override
    public String toString() {
        return String.format("Image: %d/10 | Audio: %d/10 | Color: %d/10", 
                            imageQuality, audioQuality, colorFading);
    }
}
