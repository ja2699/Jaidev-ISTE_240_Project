public class MCQQuestion implements Question {
    protected String clue;
    protected String[] options;
    protected String correctAnswer;

    public MCQQuestion(String clue, String[] options, String correctAnswer) {
        this.clue = clue;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getClue() {
        return clue;
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        if (userAnswer == null) {
            return false;
        }
        return correctAnswer != null &&
                correctAnswer.equalsIgnoreCase(userAnswer.trim());
    }

    @Override
    public String toString() {
        return "MCQ: " + clue;
    }

    @Override
    public boolean equals(Object o) { // check if question is same
        if (!(o instanceof MCQQuestion)) return false;
        MCQQuestion other = (MCQQuestion) o;
        if (this.clue == null) return other.clue == null;
        return this.clue.equals(other.clue);
    }

    @Override
    public int hashCode() {
        return clue == null ? 0 : clue.hashCode();
    }
}