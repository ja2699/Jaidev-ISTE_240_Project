import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        // Build the quiz manager (loads questions and manages leaderboard)
        QuizManager manager = new QuizManager();

        // Pass the manager to the GUI and launch JavaFX
        TreasureHuntGUI.setManager(manager);
        Application.launch(TreasureHuntGUI.class, args);
    }
}