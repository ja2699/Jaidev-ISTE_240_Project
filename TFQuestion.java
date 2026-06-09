
class TFQQuestion implements Question {
    private String question;
    private final String[] options = {"True", "False"};
    private String correctAnswer; // "True" or "False"

    public TFQQuestion(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String getClue() {
        return question;
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
        return "TFQ: " + question;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TFQQuestion)) return false;
        TFQQuestion other = (TFQQuestion) o;
        if (this.question == null) return other.question == null;
        return this.question.equals(other.question);
    }

    @Override
    public int hashCode() {
        return question == null ? 0 : question.hashCode();
    }
}