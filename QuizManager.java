import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizManager {

    private static final String LEADERBOARD_FILE = "leaderboard.txt";

    private final ArrayList<Question> allQuestions = new ArrayList<>();
    private final ArrayList<Question> selectedQuestions = new ArrayList<>();

    private int currentIndex = 0;
    private int score = 0;
    private final ArrayList<String> foundTreasures = new ArrayList<>();
    private String userName = "Player";

    public QuizManager() {
        loadDefaultQuestions();
    }

    // ===== POOL OF ALL QUESTIONS (NO FILE INPUT) =====
    private void loadDefaultQuestions() {
        allQuestions.clear();

        allQuestions.add(new MCQQuestion(
                "In which classroom does the GCIS124 lecture usually take place?",
                new String[]{"AUD-1", "AUD-2", "AUD-3", "LAB-1"},
                "AUD-3"
        ));
        allQuestions.add(new MCQQuestion(
                "Which of these is not a valid access modifier in Java?",
                new String[]{"Private", "Protected", "Internal", "Public"},
                "Internal"
        ));
        allQuestions.add(new MCQQuestion(
                "What is the size of a boolean in Java?",
                new String[]{"1 bit", "1 byte", "2 bytes", "Not precisely defined"},
                "Not precisely defined"
        ));
        allQuestions.add(new MCQQuestion(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"extends", "implements", "inherits", "instanceof"},
                "extends"
        ));
        allQuestions.add(new MCQQuestion(
                "Which of the following is NOT a Java primitive type?",
                new String[]{"int", "double", "String", "boolean"},
                "String"
        ));
        allQuestions.add(new MCQQuestion(
                "Which method is the entry point of a Java application?",
                new String[]{"start()", "run()", "main()", "init()"},
                "main()"
        ));
        allQuestions.add(new MCQQuestion(
                "Which of these cannot be used as a variable name in Java?",
                new String[]{"myVar", "var1", "class", "_value"},
                "class"
        ));
        allQuestions.add(new MCQQuestion(
                "Which collection does NOT allow duplicate elements in Java?",
                new String[]{"ArrayList", "LinkedList", "HashSet", "Vector"},
                "HashSet"
        ));
        allQuestions.add(new MCQQuestion(
                "Which of these is used to handle exceptions in Java?",
                new String[]{"try-catch", "if-else", "switch-case", "do-while"},
                "try-catch"
        ));
        allQuestions.add(new MCQQuestion(
                "Which keyword prevents a variable from being changed in Java?",
                new String[]{"static", "final", "const", "immutable"},
                "final"
        ));


        allQuestions.add(new TFQQuestion(
                "Can a static method access instance variables directly?",
                "False"
        ));
        allQuestions.add(new TFQQuestion(
                "The 'finally' block always executes whether or not an exception is thrown.",
                "True"
        ));
        allQuestions.add(new TFQQuestion(
                "Do ArrayLists maintain insertion order?",
                "True"
        ));
        allQuestions.add(new TFQQuestion(
                "Java supports multiple inheritance of classes.",
                "False"
        ));
        allQuestions.add(new TFQQuestion(
                "An abstract class can be instantiated directly.",
                "False"
        ));
        allQuestions.add(new TFQQuestion(
                "A method can be overloaded by changing only its return type.",
                "False"
        ));
        allQuestions.add(new TFQQuestion(
                "A method can be overloaded by changing only its return type.",
                "False"
        ));
        allQuestions.add(new TFQQuestion(
                "A private method can be overridden in a subclass.",
                "False"
        ));
        allQuestions.add(new TFQQuestion(
                "A constructor can be marked as Final",
                "False"
        ));
        allQuestions.add(new TFQQuestion(
                "The '==' operator compares object references for objects in Java.",
                "True"
        ));
    }

    public void setUserName(String userName) {
        if (userName != null && !userName.trim().isEmpty()) {
            this.userName = userName.trim();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void selectRandom(int number) {
        selectedQuestions.clear();
        ArrayList<Question> copy = new ArrayList<>(allQuestions);
        Collections.shuffle(copy);
        int limit = Math.min(number, copy.size());
        for (int i = 0; i < limit; i++) {
            selectedQuestions.add(copy.get(i));
        }
        currentIndex = 0;
        score = 0;
        foundTreasures.clear();
    }

    public Question getCurrentQuestion() {
        if (currentIndex < 0 || currentIndex >= selectedQuestions.size()) {
            return null;
        }
        return selectedQuestions.get(currentIndex);
    }

    public boolean hasNextQuestion() {
        return currentIndex + 1 < selectedQuestions.size();
    }

    public void moveToNextQuestion() {
        if (hasNextQuestion()) {
            currentIndex++;
        } else {
            currentIndex = selectedQuestions.size(); // past the end
        }
    }

    public int getScore() {
        return score;
    }

    public int total() {
        return selectedQuestions.size();
    }

    public int currentNumber() {
        return currentIndex + 1;
    }

    /**
     * Checks the answer for the current question and updates score.
     */
    public boolean answerCurrent(String userAnswer) {
        Question q = getCurrentQuestion();
        if (q == null) {
            return false;
        }
        boolean correct = q.checkAnswer(userAnswer);
        if (correct) {
            score++;

        }
        return correct;
    }

    // ===== Leaderboard support =====

    public static class LeaderboardEntry {
        private final String name;
        private final int score;
        private final long timeMillis;

        public LeaderboardEntry(String name, int score, long timeMillis) {
            this.name = name;
            this.score = score;
            this.timeMillis = timeMillis;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public long getTimeMillis() {
            return timeMillis;
        }

        public String format() {
            return name + ";" + score + ";" + timeMillis;
        }

        public static LeaderboardEntry parse(String line) {
            if (line == null) return null;
            String[] parts = line.split(";");
            if (parts.length < 3) return null;
            try {
                String name = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());
                long time = Long.parseLong(parts[2].trim());
                return new LeaderboardEntry(name, score, time);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    public void saveResultToLeaderboard(long timeMillis) {
        LeaderboardEntry entry = new LeaderboardEntry(userName, score, timeMillis);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(LEADERBOARD_FILE, true)))) {
            out.println(entry.format());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<LeaderboardEntry> loadLeaderboard() {
        List<LeaderboardEntry> list = new ArrayList<>();
        File file = new File(LEADERBOARD_FILE);
        if (!file.exists()) {
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                LeaderboardEntry e = LeaderboardEntry.parse(line.trim());
                if (e != null) {
                    list.add(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<LeaderboardEntry> getTopEntries(int max) {
        List<LeaderboardEntry> list = loadLeaderboard();
        list.sort((a, b) -> {
            // score descending
            int cmp = Integer.compare(b.getScore(), a.getScore());
            if (cmp != 0) return cmp;
            // time ascending
            return Long.compare(a.getTimeMillis(), b.getTimeMillis());
        });
        if (list.size() > max) {
            return new ArrayList<>(list.subList(0, max));
        }
        return list;
    }
}