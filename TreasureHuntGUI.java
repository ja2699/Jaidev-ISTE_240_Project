import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.WritableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TreasureHuntGUI extends Application {

    // Shared manager injected from Main
    private static QuizManager sharedManager;

    public static void setManager(QuizManager m) {
        sharedManager = m;
    }

    private QuizManager manager;

    // JavaFX core
    private Stage primaryStage;
    private Scene scene;
    private BorderPane root;

    // Header
    private Label titleLabel;
    private Label userLabel;
    private Label timerLabel;
    private Node logoNode;

    // Panes
    private VBox startPane;
    private VBox quizPane;
    private VBox feedbackPane;
    private VBox resultPane;

    // Start pane controls
    private TextField nameField;
    private Label nameErrorLabel;

    // Quiz pane controls
    private Label clueLabel;
    private final List<Button> optionButtons = new ArrayList<>();
    private Button skipButton;

    // Feedback pane controls
    private Label feedbackEmojiLabel;
    private Label feedbackTextLabel;

    // Result pane controls
    private Label finalScoreLabel;
    private Label finalTimeLabel;
    private ListView<String> leaderboardListView;

    // Timer
    private CountdownTimer countdownTimer;
    private final int secondsPerQuestion = 20;
    private boolean acceptingAnswers = false;

    // Quiz timing
    private long quizStartTime;
    private long quizEndTime;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.manager = (sharedManager != null) ? sharedManager : new QuizManager();

        buildUI();

        scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Treasure Hunt Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();

        showStartScreen();
    }

    private void buildUI() {
        root = new BorderPane();
        root.setPadding(new Insets(10));
        // dark mode background
        root.setStyle("-fx-background-color: #202124;");

        buildHeader();
        buildStartPane();
        buildQuizPane();
        buildFeedbackPane();
        buildResultPane();

        startPane.setPadding(new Insets(30, 20, 20, 20));
    }

    private void buildHeader() {
        titleLabel = new Label("Treasure Hunt Quiz");
        titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");

        userLabel = new Label("Player: -");
        userLabel.setStyle("-fx-text-fill: #cccccc;");

        VBox leftBox = new VBox(2, titleLabel, userLabel);

        timerLabel = new Label("Time: --");
        timerLabel.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 16px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        logoNode = createLogoNode();

        HBox header = new HBox(10, leftBox, spacer, timerLabel, logoNode);
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #181818;");

        root.setTop(header);
    }

    private Node createLogoNode() {
        try {
            Image img = new Image(new File("logo/logo_real.jpg").toURI().toString());
            ImageView view = new ImageView(img);
            view.setFitWidth(48);
            view.setFitHeight(48);
            view.setPreserveRatio(false);

            Circle clip = new Circle(24, 24, 24);
            view.setClip(clip);

            return view;
        } catch (Exception e) {
            Label fallback = new Label("LOGO");
            fallback.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");
            return fallback;
        }
    }

    private void buildStartPane() {
        Label welcome = new Label("Welcome to the Treasure Hunt Quiz!");
        welcome.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 18px; -fx-font-weight: bold;");

        Label namePrompt = new Label("Enter your name to begin:");
        namePrompt.setStyle("-fx-text-fill: #dddddd;");

        nameField = new TextField();
        nameField.setPromptText("Your name");
        nameField.setMaxWidth(250);

        Label info = new Label("You will have " + secondsPerQuestion + " seconds per question.\n" +
                "When you answer, a short transition screen will show before the next question.");
        info.setStyle("-fx-text-fill: #bbbbbb;");

        Button startButton = new Button("Start Quiz");
        startButton.setOnAction(e -> handleStart());
        stylePrimaryButton(startButton);

        startPane = new VBox(15, welcome, namePrompt, nameField, info, startButton);
        startPane.setAlignment(Pos.CENTER);
        startPane.setPadding(new Insets(20));

        nameErrorLabel = new Label("User already exists");
        nameErrorLabel.setStyle("-fx-text-fill: #ff4444; -fx-font-size: 12px;");
        nameErrorLabel.setVisible(false);

        startPane = new VBox(15, welcome, namePrompt, nameField, nameErrorLabel, info, startButton);
        startPane.setMaxWidth(400);
    }

    private void buildQuizPane() {
        clueLabel = new Label();
        clueLabel.setWrapText(true);
        clueLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-font-weight: bold;");

        VBox optionsBox = new VBox(10);
        optionsBox.setAlignment(Pos.CENTER);
        optionsBox.setFillWidth(true);

        for (int i = 0; i < 4; i++) {
            Button btn = new Button();
            btn.setMaxWidth(Double.MAX_VALUE);
            int index = i;
            btn.setOnAction(e -> handleAnswer(index));
            styleSecondaryButton(btn);
            optionButtons.add(btn);
            optionsBox.getChildren().add(btn);
        }

        skipButton = new Button("Skip / Next");
        skipButton.setOnAction(e -> handleSkip());
        styleSecondaryButton(skipButton);

        quizPane = new VBox(20, clueLabel, optionsBox, skipButton);
        quizPane.setAlignment(Pos.CENTER);
        quizPane.setPadding(new Insets(20));
    }

    private void buildFeedbackPane() {
        feedbackEmojiLabel = new Label("✅");
        feedbackEmojiLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 40px;");

        feedbackTextLabel = new Label("Correct!");
        feedbackTextLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px;");
        feedbackTextLabel.setWrapText(true);

        feedbackPane = new VBox(15, feedbackEmojiLabel, feedbackTextLabel);
        feedbackPane.setAlignment(Pos.CENTER);
        feedbackPane.setPadding(new Insets(20));
    }

    private void buildResultPane() {
        Label title = new Label("Quiz Results");
        title.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");

        finalScoreLabel = new Label();
        finalScoreLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px;");

        finalTimeLabel = new Label();
        finalTimeLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px;");

        Label leaderboardTitle = new Label("Leaderboard (Top Scores)");
        leaderboardTitle.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-font-weight: bold;");

        leaderboardListView = new ListView<>();
        leaderboardListView.setMaxHeight(200);

        Button playAgainButton = new Button("Play Again");
        stylePrimaryButton(playAgainButton);
        playAgainButton.setOnAction(e -> showStartScreen());

        resultPane = new VBox(15, title, finalScoreLabel, finalTimeLabel,
                leaderboardTitle, leaderboardListView, playAgainButton);
        resultPane.setAlignment(Pos.CENTER);
        resultPane.setPadding(new Insets(20));
    }

    private void stylePrimaryButton(Button b) {
        b.setStyle("-fx-background-color: #4285f4; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
    }

    private void styleSecondaryButton(Button b) {
        b.setStyle("-fx-background-color: #303134; -fx-text-fill: #ffffff;");
    }

    private void showStartScreen() {
        stopTimer();
        timerLabel.setText("Time: --");
        userLabel.setText("Player: -");

        BorderPane.setAlignment(startPane, Pos.CENTER);
        root.setCenter(startPane);
    }

    private boolean nameExistsInLeaderboard(String name) {
        return manager.loadLeaderboard().stream()
                .anyMatch(e -> e.getName().equals(name));
    }

    private void handleStart() {
        String name = nameField.getText().trim();

        try {
            if (nameExistsInLeaderboard(name)) {
                nameErrorLabel.setVisible(true);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        nameErrorLabel.setVisible(false);
        manager.setUserName(name);
        userLabel.setText("Player: " + manager.getUserName());
        manager.selectRandom(11);
        quizStartTime = System.currentTimeMillis();

        animateResize(800, 600);


        showQuestion();
    }

    private void animateResize(double targetWidth, double targetHeight) {
        double startWidth = primaryStage.getWidth();
        double startHeight = primaryStage.getHeight();
        double[] progress = {0};

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(16), e -> {
                    progress[0] += 0.05;
                    if (progress[0] >= 1.0) progress[0] = 1.0;

                    double ease = progress[0] < 0.5
                            ? 2 * progress[0] * progress[0]
                            : 1 - Math.pow(-2 * progress[0] + 2, 2) / 2;

                    primaryStage.setWidth(startWidth + (targetWidth - startWidth) * ease);
                    primaryStage.setHeight(startHeight + (targetHeight - startHeight) * ease);

                    if (progress[0] >= 1.0) primaryStage.centerOnScreen();
                })
        );
        timeline.setCycleCount((400 / 16));
        timeline.play();
    }

    private void showQuestion() {
        Question q = manager.getCurrentQuestion();
        if (q == null) {
            finishQuiz();
            return;
        }

        clueLabel.setText("Q" + manager.currentNumber() + ": " + q.getClue());

        String[] opts = q.getOptions();
        int count = (opts == null) ? 0 : opts.length;

        for (int i = 0; i < optionButtons.size(); i++) {
            Button btn = optionButtons.get(i);
            if (i < count) {
                btn.setText(opts[i]);
                btn.setVisible(true);
                btn.setManaged(true);
                btn.setDisable(false);
            } else {
                btn.setVisible(false);
                btn.setManaged(false);
            }
        }

        skipButton.setDisable(false);
        acceptingAnswers = true;

        root.setCenter(quizPane);

        startTimer(secondsPerQuestion);
    }

    private void handleAnswer(int index) {
        if (!acceptingAnswers) return;

        Question q = manager.getCurrentQuestion();
        if (q == null) return;

        String[] opts = q.getOptions();
        if (opts == null || index < 0 || index >= opts.length) return;

        acceptingAnswers = false;
        stopTimer();

        boolean correct = manager.answerCurrent(opts[index]);
        showFeedback(correct, q);
    }

    private void handleSkip() {
        if (!acceptingAnswers) return;

        Question q = manager.getCurrentQuestion();
        if (q == null) {
            finishQuiz();
            return;
        }

        acceptingAnswers = false;
        stopTimer();

        // Treat skip as wrong for now (no score, no treasure)
        manager.answerCurrent(""); // will be incorrect
        showFeedback(false, q);
    }

    private void showFeedback(boolean correct, Question question) {
        String emoji = correct ? "✅" : "❌";
        feedbackEmojiLabel.setText(emoji);

        String text;
        if (correct && (question instanceof TreasureQuestion)) {
            TreasureQuestion tq = (TreasureQuestion) question;
            String loc = tq.getTreasureLocation();
            if (loc != null && !loc.isBlank()) {
                text = "Correct! Treasure found at: " + loc;
            } else {
                text = "Correct!";
            }
        } else if (correct) {
            text = "Correct!";
        } else {
            text = "Wrong answer!";
        }

        feedbackTextLabel.setText(text);
        root.setCenter(feedbackPane);

        // 1-second transition before next question or results
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            manager.moveToNextQuestion();
            showQuestion();
        });
        pause.play();
    }

    private void finishQuiz() {
        stopTimer();
        timerLabel.setText("Time: --");

        quizEndTime = System.currentTimeMillis();
        long timeMillis = quizEndTime - quizStartTime;

        manager.saveResultToLeaderboard(timeMillis);

        finalScoreLabel.setText("Score: " + manager.getScore() + " / " + manager.total());
        long seconds = timeMillis / 1000;
        finalTimeLabel.setText("Time: " + seconds + " seconds");

        List<QuizManager.LeaderboardEntry> entries = manager.getTopEntries(5);
        leaderboardListView.getItems().clear();
        int rank = 1;
        for (QuizManager.LeaderboardEntry e : entries) {
            long s = e.getTimeMillis() / 1000;
            String line = rank + ". " + e.getName() + " - " +
                    e.getScore() + " pts in " + s + "s";
            leaderboardListView.getItems().add(line);
            rank++;
        }

        root.setCenter(resultPane);
    }

    private void startTimer(int seconds) {
        stopTimer();
        countdownTimer = new CountdownTimer(seconds);
        countdownTimer.start();
    }

    private void stopTimer() {
        if (countdownTimer != null) {
            countdownTimer.stopTimer();
            countdownTimer = null;
        }
    }

    // ===== Inner class using Threads for the countdown timer =====
    private class CountdownTimer extends Thread {
        private int secondsRemaining;
        private volatile boolean running = true;

        CountdownTimer(int seconds) {
            this.secondsRemaining = seconds;
        }

        void stopTimer() {
            running = false;
        }

        @Override
        public void run() {
            updateTimerLabel();

            while (running && secondsRemaining > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }

                secondsRemaining--;

                updateTimerLabel();

                if (secondsRemaining <= 0 && running) {
                    Platform.runLater(() -> handleTimeUp());
                }
            }
        }

        private void updateTimerLabel() {
            int value = secondsRemaining;
            Platform.runLater(() ->
                    timerLabel.setText("Time: " + value + "s"));
        }
    }

    private void handleTimeUp() {
        if (!acceptingAnswers) {
            return;
        }

        acceptingAnswers = false;

        Question q = manager.getCurrentQuestion();
        if (q != null) {
            manager.answerCurrent(""); // treat as wrong / no answer
        }

        showFeedback(false, q);
    }
}