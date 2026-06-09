public interface Question {
    String getClue();
    String[] getOptions();
    String getCorrectAnswer();
    boolean checkAnswer(String userAnswer);
}