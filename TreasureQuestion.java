public class TreasureQuestion extends MCQQuestion {
    private String treasureLocation;

    public TreasureQuestion(String clue, String[] options, String correctAnswer, String treasureLocation) {
        super(clue, options, correctAnswer);
        this.treasureLocation = treasureLocation;
    }

    public String getTreasureLocation() {
        return treasureLocation;
    }

    public void setTreasureLocation(String treasureLocation) {
        this.treasureLocation = treasureLocation;
    }

    @Override
    public String toString() {
        return "TreasureQ: " + clue;
    }
}