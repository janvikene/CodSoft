
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ATMInterface extends JFrame {

    // ===== Professional Navy Blue Color Palette =====
    private final Color BACKGROUND = new Color(244, 247, 251);
    private final Color WHITE = Color.WHITE;
    private final Color NAVY = new Color(36, 59, 90);
    private final Color NAVY_LIGHT = new Color(52, 75, 104);
    private final Color NAVY_DARK = new Color(28, 46, 71);
    private final Color TEXT = new Color(38, 48, 64);
    private final Color MUTED = new Color(112, 123, 140);
    private final Color BORDER = new Color(222, 228, 237);
    private final Color SUCCESS = new Color(39, 125, 91);
    private final Color ERROR = new Color(177, 61, 70);

    private double balance = 5000.00;
    private final String correctPin = "1234";

    private JLabel balanceLabel;
    private JTextArea historyArea;
    private final ArrayList<String> transactions = new ArrayList<>();

    public ATMInterface() {
        setTitle("ATM Banking System");
        setSize(780, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BACKGROUND);

        showLoginScreen();
    }

    // ===== Login Screen =====
    private void showLoginScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(35, 45, 35, 45)
        ));
        card.setPreferredSize(new Dimension(390, 340));

        JLabel title = new JLabel("ATM BANKING");
        title.setFont(new Font("SansSerif", Font.BOLD, 27));
        title.setForeground(NAVY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Secure Banking Access");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel pinLabel = new JLabel("Enter your 4-digit PIN");
        pinLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        pinLabel.setForeground(TEXT);
        pinLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPasswordField pinField = new JPasswordField();
        pinField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        pinField.setHorizontalAlignment(JTextField.CENTER);
        pinField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        pinField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(5, 10, 5, 10)
        ));

        JButton loginButton = createButton("Login", NAVY);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));

        JLabel hint = new JLabel("Demo PIN: 1234");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        hint.setForeground(MUTED);
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(title);
        card.add(Box.createVerticalStrut(7));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(32));
        card.add(pinLabel);
        card.add(Box.createVerticalStrut(9));
        card.add(pinField);
        card.add(Box.createVerticalStrut(20));
        card.add(loginButton);
        card.add(Box.createVerticalStrut(15));
        card.add(hint);

        panel.add(card);
        setContentPane(panel);
        revalidate();
        repaint();

        loginButton.addActionListener(e -> {
            String enteredPin = new String(pinField.getPassword());

            if (enteredPin.equals(correctPin)) {
                showMainScreen();
            } else {
                showMessage(
                        "Login Failed",
                        "Incorrect PIN. Please try again.",
                        ERROR
                );
                pinField.setText("");
                pinField.requestFocusInWindow();
            }
        });

        pinField.addActionListener(e -> loginButton.doClick());
    }

    // ===== Main ATM Screen =====
    private void showMainScreen() {
        JPanel main = new JPanel(null);
        main.setBackground(BACKGROUND);

        // Header
        JPanel header = new JPanel(null);
        header.setBackground(WHITE);
        header.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, BORDER
        ));
        header.setBounds(0, 0, 780, 82);

        JLabel title = new JLabel("ATM BANKING SYSTEM");
        title.setFont(new Font("SansSerif", Font.BOLD, 23));
        title.setForeground(NAVY);
        title.setBounds(30, 15, 400, 30);

        JLabel subtitle = new JLabel("Secure • Simple • Reliable");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(MUTED);
        subtitle.setBounds(32, 46, 300, 20);

        header.add(title);
        header.add(subtitle);
        main.add(header);

        // Balance Card
        JPanel balanceCard = new JPanel(null);
        balanceCard.setBackground(NAVY);
        balanceCard.setBounds(30, 105, 320, 150);

        JLabel balanceTitle = new JLabel("AVAILABLE BALANCE");
        balanceTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        balanceTitle.setForeground(new Color(218, 228, 241));
        balanceTitle.setBounds(22, 20, 260, 22);

        balanceLabel = new JLabel(formatMoney(balance));
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
        balanceLabel.setForeground(WHITE);
        balanceLabel.setBounds(20, 54, 285, 45);

        JLabel accountLabel = new JLabel("Savings Account •••• 1234");
        accountLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        accountLabel.setForeground(new Color(218, 228, 241));
        accountLabel.setBounds(22, 111, 270, 20);

        balanceCard.add(balanceTitle);
        balanceCard.add(balanceLabel);
        balanceCard.add(accountLabel);
        main.add(balanceCard);

        // Action Buttons
        JLabel actionTitle = new JLabel("TRANSACTIONS");
        actionTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
        actionTitle.setForeground(TEXT);
        actionTitle.setBounds(375, 107, 300, 25);
        main.add(actionTitle);

        JButton depositBtn = createButton("Deposit", NAVY);
        depositBtn.setBounds(375, 143, 165, 43);
        main.add(depositBtn);

        JButton withdrawBtn = createButton("Withdraw", NAVY_LIGHT);
        withdrawBtn.setBounds(550, 143, 165, 43);
        main.add(withdrawBtn);

        JButton balanceBtn = createButton("Check Balance", NAVY_DARK);
        balanceBtn.setBounds(375, 198, 165, 43);
        main.add(balanceBtn);

        JButton logoutBtn = createButton(
                "Logout", new Color(67, 81, 101)
        );
        logoutBtn.setBounds(550, 198, 165, 43);
        main.add(logoutBtn);

        // Transaction History
        JPanel historyCard = new JPanel(new BorderLayout(0, 10));
        historyCard.setBackground(WHITE);
        historyCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(13, 16, 13, 16)
        ));
        historyCard.setBounds(30, 280, 685, 215);

        JLabel historyTitle = new JLabel("RECENT TRANSACTIONS");
        historyTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
        historyTitle.setForeground(NAVY);
        historyCard.add(historyTitle, BorderLayout.NORTH);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFocusable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        historyArea.setForeground(TEXT);
        historyArea.setBackground(WHITE);
        historyArea.setText("No transactions yet.");

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER));
        historyCard.add(scrollPane, BorderLayout.CENTER);
        main.add(historyCard);

        JLabel footer = new JLabel(
                "Demo application only — no real banking transactions"
        );
        footer.setFont(new Font("SansSerif", Font.PLAIN, 10));
        footer.setForeground(MUTED);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBounds(30, 505, 685, 18);
        main.add(footer);

        // Button Actions
        depositBtn.addActionListener(e -> depositMoney());
        withdrawBtn.addActionListener(e -> withdrawMoney());

        balanceBtn.addActionListener(e ->
                showMessage(
                        "Balance Information",
                        "Your available balance is " + formatMoney(balance),
                        NAVY
                )
        );

        logoutBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                showLoginScreen();
            }
        });

        setContentPane(main);
        revalidate();
        repaint();
    }

    // ===== Deposit =====
    private void depositMoney() {
        String input = JOptionPane.showInputDialog(
                this,
                "Enter amount to deposit (₹):",
                "Deposit Money",
                JOptionPane.PLAIN_MESSAGE
        );

        if (input == null) {
            return;
        }

        try {
            double amount = Double.parseDouble(input.trim());

            if (!Double.isFinite(amount) || amount <= 0
                    || amount > 1_000_000) {
                showMessage(
                        "Invalid Amount",
                        "Enter an amount greater than ₹0 and at most ₹10,00,000.",
                        ERROR
                );
                return;
            }

            balance += amount;
            updateBalance();

            addTransaction("DEPOSIT", amount);

            showMessage(
                    "Deposit Successful",
                    formatMoney(amount) + " deposited successfully.",
                    SUCCESS
            );

        } catch (NumberFormatException ex) {
            showMessage(
                    "Invalid Input",
                    "Please enter a valid numeric amount.",
                    ERROR
            );
        }
    }

    // ===== Withdraw =====
    private void withdrawMoney() {
        String input = JOptionPane.showInputDialog(
                this,
                "Enter amount to withdraw (₹):",
                "Withdraw Money",
                JOptionPane.PLAIN_MESSAGE
        );

        if (input == null) {
            return;
        }

        try {
            double amount = Double.parseDouble(input.trim());

            if (!Double.isFinite(amount) || amount <= 0
                    || amount > 50_000) {
                showMessage(
                        "Invalid Amount",
                        "Enter an amount greater than ₹0 and at most ₹50,000.",
                        ERROR
                );
                return;
            }

            if (amount > balance) {
                showMessage(
                        "Insufficient Balance",
                        "Your account does not have sufficient funds.",
                        ERROR
                );
                return;
            }

            balance -= amount;
            updateBalance();

            addTransaction("WITHDRAW", amount);

            showMessage(
                    "Withdrawal Successful",
                    formatMoney(amount) + " withdrawn successfully.",
                    SUCCESS
            );

        } catch (NumberFormatException ex) {
            showMessage(
                    "Invalid Input",
                    "Please enter a valid numeric amount.",
                    ERROR
            );
        }
    }

    // ===== Update Balance =====
    private void updateBalance() {
        if (balanceLabel != null) {
            balanceLabel.setText(formatMoney(balance));
        }
    }

    // ===== Transaction History =====
    private void addTransaction(String type, double amount) {
        String time = new SimpleDateFormat(
                "dd MMM, hh:mm a"
        ).format(new Date());

        transactions.add(
                time + " | " + type + " | " + formatMoney(amount)
        );

        StringBuilder history = new StringBuilder();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            history.append(transactions.get(i)).append("\n");
        }

        historyArea.setText(history.toString());
        historyArea.setCaretPosition(0);
    }

    // ===== Custom Button =====
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);

        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(WHITE);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        // Subtle hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(color);
            }
        });

        return button;
    }

    // ===== Custom Professional Popup =====
    private void showMessage(
            String title,
            String message,
            Color accentColor
    ) {
        JPanel panel = new JPanel(new BorderLayout(12, 0));
        panel.setBackground(WHITE);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel accent = new JPanel();
        accent.setBackground(accentColor);
        accent.setPreferredSize(new Dimension(5, 55));

        JLabel messageLabel = new JLabel(
                "<html><div style='width:250px;'>"
                        + escapeHtml(message).replace("\n", "<br>")
                        + "</div></html>"
        );

        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        messageLabel.setForeground(TEXT);

        panel.add(accent, BorderLayout.WEST);
        panel.add(messageLabel, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(
                this,
                panel,
                title,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;");
    }

    // ===== Currency Formatting =====
    private String formatMoney(double amount) {
        return String.format(java.util.Locale.US, "₹%,.2f", amount);
    }

    // ===== Main Method =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
                // Use default Swing look and feel.
            }

            ATMInterface atm = new ATMInterface();
            atm.setVisible(true);
        });
    }
}
