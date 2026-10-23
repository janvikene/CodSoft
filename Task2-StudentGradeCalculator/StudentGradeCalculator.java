
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class StudentGradeCalculator extends JFrame {

    // Theme colors
    private static final Color BG = new Color(241, 239, 250);
    private static final Color WHITE = Color.WHITE;
    private static final Color INK = new Color(43, 40, 68);
    private static final Color PURPLE = new Color(111, 82, 180);
    private static final Color LAVENDER = new Color(238, 232, 250);
    private static final Color MUTED = new Color(128, 124, 148);
    private static final Color LINE = new Color(231, 227, 240);
    private static final Color GREEN = new Color(39, 145, 103);
    private static final Color RED = new Color(204, 73, 85);

    private JTextField nameField;
    private JTextField rollField;
    private JTextField[] markFields;

    private JLabel totalLabel;
    private JLabel percentageLabel;
    private JLabel gradeLabel;
    private JLabel statusLabel;
    private JLabel messageLabel;

    private final String[] subjects = {
        "Java Programming",
        "Data Structures",
        "Database Management",
        "Operating Systems",
        "Machine Learning"
    };

    public StudentGradeCalculator() {
        setTitle("GradeBook | Student Grade Calculator");
        setSize(760, 660);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(0, 14));
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(18, 22, 14, 22));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel brand = new JPanel(new FlowLayout(FlowLayout.LEFT, 9, 0));
        brand.setOpaque(false);

        JLabel logo = new JLabel("G", SwingConstants.CENTER);
        logo.setFont(new Font("Serif", Font.BOLD, 22));
        logo.setForeground(WHITE);
        logo.setOpaque(true);
        logo.setBackground(PURPLE);
        logo.setPreferredSize(new Dimension(38, 38));

        JPanel brandText = new JPanel();
        brandText.setOpaque(false);
        brandText.setLayout(new BoxLayout(brandText, BoxLayout.Y_AXIS));

        JLabel appName = new JLabel("GradeBook");
        appName.setFont(new Font("Serif", Font.BOLD, 21));
        appName.setForeground(INK);

        JLabel appSub = new JLabel("STUDENT PERFORMANCE");
        appSub.setFont(new Font("SansSerif", Font.BOLD, 9));
        appSub.setForeground(MUTED);

        brandText.add(appName);
        brandText.add(appSub);
        brand.add(logo);
        brand.add(brandText);

        JLabel term = new JLabel("ACADEMIC RECORD");
        term.setFont(new Font("SansSerif", Font.BOLD, 9));
        term.setForeground(PURPLE);

        header.add(brand, BorderLayout.WEST);
        header.add(term, BorderLayout.EAST);

        // TITLE
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Grade Calculator");
        title.setFont(new Font("Serif", Font.BOLD, 27));
        title.setForeground(INK);

        JLabel subtitle = new JLabel(
            "Enter marks below to calculate academic performance."
        );
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        subtitle.setForeground(MUTED);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(subtitle);

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(header);
        top.add(Box.createVerticalStrut(19));
        top.add(titlePanel);

        // STUDENT DETAILS
        JPanel details = createCard();
        details.setLayout(new GridLayout(1, 2, 12, 0));

        nameField = createInput("Enter full name");
        rollField = createInput("Enter roll number");

        details.add(createFieldGroup("STUDENT NAME", nameField));
        details.add(createFieldGroup("ROLL NUMBER", rollField));

        // MARKS TABLE
        JPanel marksCard = createCard();
        marksCard.setLayout(new BorderLayout(0, 10));

        JPanel tableHeading = new JPanel(new BorderLayout());
        tableHeading.setOpaque(false);

        JLabel marksTitle = new JLabel("Subject marks");
        marksTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        marksTitle.setForeground(INK);

        JLabel maxMarks = new JLabel("OUT OF 100");
        maxMarks.setFont(new Font("SansSerif", Font.BOLD, 9));
        maxMarks.setForeground(MUTED);

        tableHeading.add(marksTitle, BorderLayout.WEST);
        tableHeading.add(maxMarks, BorderLayout.EAST);
        marksCard.add(tableHeading, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridLayout(0, 3, 8, 7));
        table.setOpaque(false);

        table.add(createTableHeader("SUBJECT"));
        table.add(createTableHeader("MAXIMUM"));
        table.add(createTableHeader("MARKS"));

        markFields = new JTextField[subjects.length];

        for (int i = 0; i < subjects.length; i++) {
            JLabel subject = new JLabel(subjects[i]);
            subject.setFont(new Font("SansSerif", Font.PLAIN, 11));
            subject.setForeground(INK);

            JLabel maximum = new JLabel("100", SwingConstants.CENTER);
            maximum.setFont(new Font("SansSerif", Font.PLAIN, 11));
            maximum.setForeground(MUTED);

            markFields[i] = createInput("0-100");
            markFields[i].setHorizontalAlignment(JTextField.CENTER);
            markFields[i].setPreferredSize(new Dimension(65, 30));

            table.add(subject);
            table.add(maximum);
            table.add(markFields[i]);
        }

        marksCard.add(table, BorderLayout.CENTER);

        JLabel note = new JLabel(
            "Enter whole-number marks between 0 and 100."
        );
        note.setFont(new Font("SansSerif", Font.PLAIN, 10));
        note.setForeground(MUTED);
        marksCard.add(note, BorderLayout.SOUTH);

        // RESULT SUMMARY
        JPanel resultCard = createCard();
        resultCard.setLayout(new BoxLayout(resultCard, BoxLayout.Y_AXIS));

        JLabel resultHeading = new JLabel("RESULT SUMMARY");
        resultHeading.setFont(new Font("SansSerif", Font.BOLD, 10));
        resultHeading.setForeground(MUTED);

        percentageLabel = new JLabel("--%");
        percentageLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        percentageLabel.setForeground(INK);

        JLabel averageText = new JLabel("OVERALL PERCENTAGE");
        averageText.setFont(new Font("SansSerif", Font.BOLD, 9));
        averageText.setForeground(MUTED);

        totalLabel = new JLabel("-- / 500");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 19));
        totalLabel.setForeground(INK);

        JLabel totalDescription = new JLabel("Marks obtained out of 500");
        totalDescription.setFont(new Font("SansSerif", Font.PLAIN, 10));
        totalDescription.setForeground(MUTED);

        JSeparator separator = new JSeparator();
        separator.setForeground(LINE);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        gradeLabel = new JLabel("--");
        gradeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        gradeLabel.setForeground(PURPLE);

        statusLabel = new JLabel("Awaiting marks");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        statusLabel.setForeground(PURPLE);

        messageLabel = new JLabel(
            "<html>Complete all fields to<br>view your result.</html>"
        );
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        messageLabel.setForeground(MUTED);

        resultCard.add(resultHeading);
        resultCard.add(Box.createVerticalStrut(12));
        resultCard.add(percentageLabel);
        resultCard.add(Box.createVerticalStrut(4));
        resultCard.add(averageText);
        resultCard.add(Box.createVerticalStrut(8));
        resultCard.add(totalLabel);
        resultCard.add(totalDescription);
        resultCard.add(Box.createVerticalStrut(14));
        resultCard.add(separator);
        resultCard.add(Box.createVerticalStrut(13));
        resultCard.add(createSmallHeading("FINAL GRADE"));
        resultCard.add(Box.createVerticalStrut(4));
        resultCard.add(gradeLabel);
        resultCard.add(Box.createVerticalStrut(15));
        resultCard.add(statusLabel);
        resultCard.add(Box.createVerticalStrut(6));
        resultCard.add(messageLabel);

        // MAIN LAYOUT
        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        left.add(details);
        left.add(Box.createVerticalStrut(12));
        left.add(marksCard);

        JPanel resultHolder = new JPanel(new BorderLayout());
        resultHolder.setOpaque(false);
        resultHolder.add(resultCard, BorderLayout.CENTER);
        resultHolder.setPreferredSize(new Dimension(215, 0));

        JPanel main = new JPanel(new BorderLayout(14, 0));
        main.setOpaque(false);
        main.add(left, BorderLayout.CENTER);
        main.add(resultHolder, BorderLayout.EAST);

        // BUTTONS
        JButton calculateButton = createButton(
            "Calculate Result", LAVENDER, INK
        );
        JButton resetButton = createButton(
            "Clear Form", WHITE, PURPLE
        );

        JPanel actions = new JPanel(new GridLayout(1, 2, 10, 0));
        actions.setOpaque(false);
        actions.add(calculateButton);
        actions.add(resetButton);

        // LEFT CONTENT INCLUDING BUTTONS
        JPanel leftContent = new JPanel(new BorderLayout(0, 12));
        leftContent.setOpaque(false);
        leftContent.add(left, BorderLayout.CENTER);
        leftContent.add(actions, BorderLayout.SOUTH);

        main.remove(left);
        main.add(leftContent, BorderLayout.CENTER);

        // FOOTER
        JLabel footer = new JLabel(
            "GRADEBOOK  |  JAVA SWING  |  CODSOFT TASK 02",
            SwingConstants.CENTER
        );
        footer.setFont(new Font("SansSerif", Font.BOLD, 9));
        footer.setForeground(MUTED);

        JPanel center = new JPanel(new BorderLayout(0, 15));
        center.setOpaque(false);
        center.add(top, BorderLayout.NORTH);
        center.add(main, BorderLayout.CENTER);

        root.add(center, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);

        setContentPane(root);

        calculateButton.addActionListener(e -> calculateResult());
        resetButton.addActionListener(e -> clearForm());

        getRootPane().setDefaultButton(calculateButton);
    }

    private JPanel createCard() {
        JPanel panel = new JPanel();
        panel.setBackground(WHITE);
        panel.setBorder(new CompoundBorder(
            new LineBorder(LINE, 1, true),
            new EmptyBorder(13, 14, 13, 14)
        ));
        return panel;
    }

    private JTextField createInput(String hint) {
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 12));
        field.setForeground(INK);
        field.setBackground(new Color(250, 249, 253));
        field.setCaretColor(PURPLE);
        field.setToolTipText(hint);
        field.setBorder(new CompoundBorder(
            new LineBorder(LINE, 1, true),
            new EmptyBorder(5, 8, 5, 8)
        ));
        return field;
    }

    private JPanel createFieldGroup(String title, JTextField field) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = createSmallHeading(title);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        panel.add(label);
        panel.add(Box.createVerticalStrut(6));
        panel.add(field);

        return panel;
    }

    private JLabel createSmallHeading(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 9));
        label.setForeground(MUTED);
        return label;
    }

    private JLabel createTableHeader(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 9));
        label.setForeground(MUTED);
        return label;
    }

    private JButton createButton(
        String text, Color background, Color foreground
    ) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 11));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new CompoundBorder(
            new LineBorder(
                background.equals(WHITE) ? LINE : background, 1, true
            ),
            new EmptyBorder(9, 12, 9, 12)
        ));
        return button;
    }

    private void calculateResult() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();

        if (name.isEmpty() || roll.isEmpty()) {
            showError("Please enter the student name and roll number.");
            return;
        }

        int total = 0;
        boolean passed = true;

        for (int i = 0; i < markFields.length; i++) {
            String text = markFields[i].getText().trim();
            int mark;

            try {
                mark = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                showError(
                    "Enter valid whole-number marks for "
                    + subjects[i] + "."
                );
                markFields[i].requestFocusInWindow();
                return;
            }

            if (mark < 0 || mark > 100) {
                showError(
                    "Marks for " + subjects[i]
                    + " must be between 0 and 100."
                );
                markFields[i].requestFocusInWindow();
                return;
            }

            total += mark;

            if (mark < 35) {
                passed = false;
            }
        }

        double percentage = total / 5.0;
        String grade;

        if (!passed) {
            grade = "F";
        } else if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 80) {
            grade = "A";
        } else if (percentage >= 70) {
            grade = "B+";
        } else if (percentage >= 60) {
            grade = "B";
        } else if (percentage >= 50) {
            grade = "C";
        } else {
            grade = "D";
        }

        percentageLabel.setText(String.format("%.1f%%", percentage));
        totalLabel.setText(total + " / 500");
        gradeLabel.setText(grade);

        if (passed) {
            statusLabel.setText("PASSED");
            statusLabel.setForeground(GREEN);
            messageLabel.setText(
                "<html>Well done, " + escapeHtml(name)
                + "!<br>Roll No: " + escapeHtml(roll) + "</html>"
            );
        } else {
            statusLabel.setText("NEEDS IMPROVEMENT");
            statusLabel.setForeground(RED);
            messageLabel.setText(
                "<html>At least one subject<br>has marks below 35.</html>"
            );
        }
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Check Your Input",
            JOptionPane.WARNING_MESSAGE
        );
    }

    private void clearForm() {
        nameField.setText("");
        rollField.setText("");

        for (JTextField field : markFields) {
            field.setText("");
        }

        totalLabel.setText("-- / 500");
        percentageLabel.setText("--%");
        gradeLabel.setText("--");
        statusLabel.setText("Awaiting marks");
        statusLabel.setForeground(PURPLE);
        messageLabel.setText(
            "<html>Complete all fields to<br>view your result.</html>"
        );
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
            // Keep the default Swing appearance.
        }

        SwingUtilities.invokeLater(() -> {
            StudentGradeCalculator app = new StudentGradeCalculator();
            app.setVisible(true);
        });
    }
}
