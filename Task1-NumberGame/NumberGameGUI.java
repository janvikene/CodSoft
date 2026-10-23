
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class NumberGameGUI extends JFrame {

    private final Random random = new Random();

    private final Color background = new Color(12, 20, 45);
    private final Color panelColor = new Color(22, 35, 70);
    private final Color accent = new Color(115, 76, 255);
    private final Color cyan = new Color(57, 210, 235);

    private JComboBox<String> difficulty;
    private JTextField guessField;
    private JLabel rangeLabel, attemptsLabel, scoreLabel;
    private JLabel feedbackLabel, hintLabel;
    private JButton guessButton, newGameButton;

    private int secretNumber, maxNumber, maxAttempts;
    private int attempts, score;
    private boolean gameOver = false;

    public NumberGameGUI() {
        setTitle("Number Mastermind | Java Game");
        setSize(650, 590);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel main = new JPanel();
        main.setBackground(background);
        main.setBorder(new EmptyBorder(25, 35, 25, 35));
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("\uD83C\uDFAF");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("NUMBER MASTERMIND");
        title.setFont(new Font("SansSerif", Font.BOLD, 27));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Test your luck, logic and intuition!");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(cyan);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(icon);
        main.add(title);
        main.add(Box.createVerticalStrut(5));
        main.add(subtitle);
        main.add(Box.createVerticalStrut(22));

        JPanel settings = createCard();
        settings.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 12));

        JLabel levelText = createLabel("Difficulty");
        difficulty = new JComboBox<>(
                new String[]{"Easy", "Medium", "Hard"});
        difficulty.setFont(new Font("SansSerif", Font.BOLD, 14));
        difficulty.setBackground(panelColor);
        difficulty.setForeground(Color.WHITE);

        settings.add(levelText);
        settings.add(difficulty);

        rangeLabel = createLabel("Range: 1 - 50");
        settings.add(rangeLabel);
        main.add(settings);
        main.add(Box.createVerticalStrut(18));

        JPanel stats = createCard();
        stats.setLayout(new GridLayout(1, 2, 10, 10));
        attemptsLabel = createStat("ATTEMPTS LEFT", "10");
        scoreLabel = createStat("TOTAL SCORE", "0");

        stats.add(attemptsLabel);
        stats.add(scoreLabel);
        main.add(stats);
        main.add(Box.createVerticalStrut(20));

        JLabel prompt = createLabel("Enter your guess");
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(prompt);
        main.add(Box.createVerticalStrut(10));

        guessField = new JTextField();
        guessField.setMaximumSize(new Dimension(300, 45));
        guessField.setFont(new Font("SansSerif", Font.BOLD, 22));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessField.setBackground(panelColor);
        guessField.setForeground(Color.WHITE);
        guessField.setCaretColor(Color.WHITE);
        guessField.setBorder(
                BorderFactory.createLineBorder(accent, 2, true));

        main.add(guessField);
        main.add(Box.createVerticalStrut(12));

        guessButton = createButton("SUBMIT GUESS", accent);
        newGameButton = createButton("NEW GAME", new Color(25, 115, 230));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        buttons.setBackground(background);
        buttons.add(guessButton);
        buttons.add(newGameButton);
        main.add(buttons);

        main.add(Box.createVerticalStrut(15));

        feedbackLabel = createLabel("Choose a difficulty and start guessing!");
        feedbackLabel.setForeground(Color.WHITE);
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(feedbackLabel);

        hintLabel = createLabel("You will receive hints after each guess.");
        hintLabel.setForeground(cyan);
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(Box.createVerticalStrut(8));
        main.add(hintLabel);

        add(main);

        difficulty.addActionListener(e -> startGame());
        guessButton.addActionListener(e -> checkGuess());
        newGameButton.addActionListener(e -> startGame());
        guessField.addActionListener(e -> checkGuess());

        startGame();
    }

    private JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(48, 67, 115), 1, true),
                new EmptyBorder(10, 12, 10, 12)));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JLabel createStat(String heading, String value) {
        JLabel label = new JLabel(
                "<html><center><font color='#9DB2D9'>"
                + heading
                + "</font><br><font color='#39D2EB' size='6'>"
                + value + "</font></center></html>",
                SwingConstants.CENTER);
        return label;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(12, 22, 12, 22));
        return button;
    }

    private void startGame() {
        String level = (String) difficulty.getSelectedItem();

        switch (level) {
            case "Easy":
                maxNumber = 50;
                maxAttempts = 10;
                break;
            case "Medium":
                maxNumber = 100;
                maxAttempts = 7;
                break;
            default:
                maxNumber = 200;
                maxAttempts = 6;
        }

        secretNumber = random.nextInt(maxNumber) + 1;
        attempts = 0;
        gameOver = false;

        rangeLabel.setText("Range: 1 - " + maxNumber);
        attemptsLabel.setText(
                "<html><center><font color='#9DB2D9'>ATTEMPTS LEFT"
                + "</font><br><font color='#39D2EB' size='6'>"
                + maxAttempts + "</font></center></html>");
        scoreLabel.setText(
                "<html><center><font color='#9DB2D9'>TOTAL SCORE"
                + "</font><br><font color='#39D2EB' size='6'>"
                + score + "</font></center></html>");

        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        difficulty.setEnabled(true);

        feedbackLabel.setForeground(Color.WHITE);
        feedbackLabel.setText("A secret number is waiting for you!");
        hintLabel.setText("Make your first guess.");
        guessField.requestFocusInWindow();
    }

    private void checkGuess() {
        if (gameOver) {
            return;
        }

        String input = guessField.getText().trim();
        int guess;

        try {
            guess = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            feedbackLabel.setForeground(Color.ORANGE);
            feedbackLabel.setText("Enter a valid whole number!");
            return;
        }

        if (guess < 1 || guess > maxNumber) {
            feedbackLabel.setForeground(Color.ORANGE);
            feedbackLabel.setText(
                    "Enter a number from 1 to " + maxNumber + ".");
            return;
        }

        attempts++;
        int remaining = maxAttempts - attempts;

        if (guess == secretNumber) {
            int points = remaining * 10 + 10;
            score += points;
            gameOver = true;

            feedbackLabel.setForeground(
                    new Color(70, 235, 160));
            feedbackLabel.setText(
                    "CORRECT! You earned " + points + " points!");

            hintLabel.setText(
                    "Secret number: " + secretNumber
                    + " | Attempts used: " + attempts);

            guessButton.setEnabled(false);
            guessField.setEnabled(false);

        } else if (remaining == 0) {
            gameOver = true;

            feedbackLabel.setForeground(
                    new Color(255, 100, 115));
            feedbackLabel.setText("GAME OVER! Better luck next time.");

            hintLabel.setText(
                    "The secret number was " + secretNumber);

            guessButton.setEnabled(false);
            guessField.setEnabled(false);

        } else {
            feedbackLabel.setForeground(
                    new Color(255, 190, 90));

            feedbackLabel.setText(
                    guess < secretNumber
                    ? "Too LOW! Try a higher number."
                    : "Too HIGH! Try a lower number.");

            int difference = Math.abs(secretNumber - guess);

            if (difference <= 5) {
                hintLabel.setText("Hint: You are very close!");
            } else if (difference <= 15) {
                hintLabel.setText("Hint: You are getting closer.");
            } else {
                hintLabel.setText("Hint: Try a different range.");
            }
        }

        attemptsLabel.setText(
                "<html><center><font color='#9DB2D9'>ATTEMPTS LEFT"
                + "</font><br><font color='#39D2EB' size='6'>"
                + remaining + "</font></center></html>");

        scoreLabel.setText(
                "<html><center><font color='#9DB2D9'>TOTAL SCORE"
                + "</font><br><font color='#39D2EB' size='6'>"
                + score + "</font></center></html>");

        guessField.setText("");
        guessField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGameGUI game = new NumberGameGUI();
            game.setVisible(true);
        });
    }
}
