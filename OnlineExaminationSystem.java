import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class OnlineExaminationSystem extends JFrame {
    private String username = "student";
    private String password = "1234";
    private String displayName = "Student";
    private final List<Question> questions = new ArrayList<>();
    private int currentQuestion = 0;
    private int score = 0;
    private long examStartTime;
    private long examEndTime;
    private int remainingSeconds = 30 * 60;
    private Timer timer;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField displayNameField;
    private JPasswordField newPasswordField;
    private JLabel questionLabel;
    private JLabel questionNumberLabel;
    private JLabel timerLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private ButtonGroup optionGroup;
    private JLabel resultLabel;
    private JTextArea breakdownArea;
    private boolean examStarted = false;
    public OnlineExaminationSystem() {

        setTitle("Online Examination System");
        setSize(800, 600);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        loadQuestions();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createProfilePanel(), "PROFILE");
        mainPanel.add(createExamPanel(), "EXAM");
        mainPanel.add(createResultPanel(), "RESULT");

        add(mainPanel);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                if (examStarted) {

                    int choice = JOptionPane.showConfirmDialog(
                            OnlineExaminationSystem.this,
                            "Are you sure you want to quit?",
                            "Confirm Exit",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION) {

                        if (timer != null) {
                            timer.stop();
                        }

                        System.exit(0);
                    }

                } else {

                    System.exit(0);
                }
            }
        });

        setVisible(true);
    }
    //Questions
    private void loadQuestions() {

        questions.add(new Question(
                "Which language is used for Android development?",
                new String[]{
                        "Java",
                        "HTML",
                        "CSS",
                        "SQL"
                },
                0
        ));

        questions.add(new Question(
                "Which keyword is used to create a class in Java?",
                new String[]{
                        "function",
                        "class",
                        "define",
                        "struct"
                },
                1
        ));

        questions.add(new Question(
                "Which Swing component is used for a button?",
                new String[]{
                        "JTextField",
                        "JLabel",
                        "JButton",
                        "JPanel"
                },
                2
        ));

        questions.add(new Question(
                "Which component allows selecting one option from many?",
                new String[]{
                        "JRadioButton",
                        "JTextArea",
                        "JLabel",
                        "JFrame"
                },
                0
        ));

        questions.add(new Question(
                "Which keyword is used to inherit a class?",
                new String[]{
                        "implements",
                        "extends",
                        "inherits",
                        "super"
                },
                1
        ));

        questions.add(new Question(
                "Which method starts a Java program?",
                new String[]{
                        "start()",
                        "run()",
                        "main()",
                        "execute()"
                },
                2
        ));

        questions.add(new Question(
                "Which Swing class provides a countdown timer?",
                new String[]{
                        "Thread",
                        "Timer",
                        "Clock",
                        "Time"
                },
                1
        ));

        questions.add(new Question(
                "Which layout is useful for switching between screens?",
                new String[]{
                        "FlowLayout",
                        "GridLayout",
                        "CardLayout",
                        "BorderLayout"
                },
                2
        ));

        questions.add(new Question(
                "Which collection stores elements in order?",
                new String[]{
                        "ArrayList",
                        "HashSet",
                        "HashMap",
                        "TreeSet"
                },
                0
        ));

        questions.add(new Question(
                "Which keyword prevents a variable from being changed?",
                new String[]{
                        "static",
                        "constant",
                        "final",
                        "private"
                },
                2
        ));
    }
      // Login screen
    private JPanel createLoginPanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        JPanel box = new JPanel(new GridBagLayout());
        box.setBorder(
                BorderFactory.createTitledBorder(
                        "Student Login"
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel(
                "ONLINE EXAMINATION SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        box.add(title, gbc);

        gbc.gridwidth = 1;

        JLabel userLabel = new JLabel("Username:");

        gbc.gridx = 0;
        gbc.gridy = 1;

        box.add(userLabel, gbc);

        usernameField = new JTextField(15);

        gbc.gridx = 1;

        box.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");

        gbc.gridx = 0;
        gbc.gridy = 2;

        box.add(passLabel, gbc);

        passwordField = new JPasswordField(15);

        gbc.gridx = 1;

        box.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        box.add(loginButton, gbc);

        JLabel info = new JLabel(
                "Demo Login: student / 1234",
                SwingConstants.CENTER
        );

        gbc.gridy = 4;

        box.add(info, gbc);

        loginButton.addActionListener(e -> login());

        panel.add(box);

        return panel;
    }


    private void login() {

        String user = usernameField.getText();

        String pass =
                new String(passwordField.getPassword());

        if (user.equals(username)
                && pass.equals(password)) {

            displayNameField.setText(displayName);

            newPasswordField.setText("");

            cardLayout.show(mainPanel, "PROFILE");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
     // Profile screen
    private JPanel createProfilePanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        JPanel box = new JPanel(new GridBagLayout());

        box.setBorder(
                BorderFactory.createTitledBorder(
                        "Update Profile"
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel =
                new JLabel("Display Name:");

        gbc.gridx = 0;
        gbc.gridy = 0;

        box.add(nameLabel, gbc);

        displayNameField =
                new JTextField(15);

        gbc.gridx = 1;

        box.add(displayNameField, gbc);

        JLabel passwordLabel =
                new JLabel("New Password:");

        gbc.gridx = 0;
        gbc.gridy = 1;

        box.add(passwordLabel, gbc);

        newPasswordField =
                new JPasswordField(15);

        gbc.gridx = 1;

        box.add(newPasswordField, gbc);

        JButton startButton =
                new JButton("Start Exam");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        box.add(startButton, gbc);

        JButton backButton =
                new JButton("Logout");

        gbc.gridy = 3;

        box.add(backButton, gbc);

        startButton.addActionListener(
                e -> updateProfileAndStart()
        );

        backButton.addActionListener(
                e -> logout()
        );

        panel.add(box);

        return panel;
    }


    private void updateProfileAndStart() {

        String name =
                displayNameField.getText().trim();

        String newPass =
                new String(
                        newPasswordField.getPassword()
                ).trim();

        if (!name.isEmpty()) {
            displayName = name;
        }

        if (!newPass.isEmpty()) {
            password = newPass;
        }

        startExam();
    }
    // Exam screen
    private JPanel createExamPanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());

        questionNumberLabel =
                new JLabel("Question 1");

        questionNumberLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        timerLabel =
                new JLabel("Time: 30:00");

        timerLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        topPanel.add(
                questionNumberLabel,
                BorderLayout.WEST
        );

        topPanel.add(
                timerLabel,
                BorderLayout.EAST
        );

        panel.add(topPanel, BorderLayout.NORTH);
        // Question area
        JPanel questionPanel =
                new JPanel(new BorderLayout());

        questionLabel =
                new JLabel();

        questionLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        questionPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        questionPanel.add(
                questionLabel,
                BorderLayout.NORTH
        );
        // Options
        JPanel optionsPanel =
                new JPanel();

        optionsPanel.setLayout(
                new BoxLayout(
                        optionsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionGroup = new ButtonGroup();

        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        questionPanel.add(
                optionsPanel,
                BorderLayout.CENTER
        );

        panel.add(
                questionPanel,
                BorderLayout.CENTER
        );
        // Bottom buttons
        JPanel bottomPanel = new JPanel();

        JButton previousButton =
                new JButton("Previous");

        JButton nextButton =
                new JButton("Next");

        JButton submitButton =
                new JButton("Submit Exam");

        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(submitButton);

        panel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        previousButton.addActionListener(
                e -> previousQuestion()
        );

        nextButton.addActionListener(
                e -> nextQuestion()
        );

        submitButton.addActionListener(
                e -> manualSubmit()
        );

        return panel;
    }


    private void startExam() {

        currentQuestion = 0;

        score = 0;

        remainingSeconds = 30 * 60;

        examStarted = true;

        examStartTime =
                System.currentTimeMillis();

        showQuestion();

        startTimer();

        cardLayout.show(mainPanel, "EXAM");
    }
    // Display questions

    private void showQuestion() {

        Question q =
                questions.get(currentQuestion);

        questionNumberLabel.setText(
                "Question "
                        + (currentQuestion + 1)
                        + " of "
                        + questions.size()
        );

        questionLabel.setText(
                "<html>"
                        + q.question
                        + "</html>"
        );

        option1.setText(q.options[0]);
        option2.setText(q.options[1]);
        option3.setText(q.options[2]);
        option4.setText(q.options[3]);

        optionGroup.clearSelection();
    }
    // Navigation
    private void nextQuestion() {

        if (currentQuestion <
                questions.size() - 1) {

            currentQuestion++;

            showQuestion();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "This is the last question."
            );
        }
    }


    private void previousQuestion() {

        if (currentQuestion > 0) {

            currentQuestion--;

            showQuestion();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "This is the first question."
            );
        }
    }
    // Timer
    private void startTimer() {

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(
                1000,
                e -> updateTimer()
        );

        timer.start();
    }


    private void updateTimer() {

        remainingSeconds--;

        int minutes =
                remainingSeconds / 60;

        int seconds =
                remainingSeconds % 60;

        timerLabel.setText(
                String.format(
                        "Time: %02d:%02d",
                        minutes,
                        seconds
                )
        );

        if (remainingSeconds <= 0) {

            timer.stop();

            JOptionPane.showMessageDialog(
                    this,
                    "Time is up! Your exam will be submitted automatically."
            );

            submitExam();
        }
    }
    // Answer checking
    private int getSelectedAnswer() {

        if (option1.isSelected())
            return 0;

        if (option2.isSelected())
            return 1;

        if (option3.isSelected())
            return 2;

        if (option4.isSelected())
            return 3;

        return -1;
    }
    // Manual submission
    private void manualSubmit() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to submit the exam?",
                        "Confirm Submission",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            submitExam();
        }
    }
    // Submit exam
    private void submitExam() {

        if (timer != null) {
            timer.stop();
        }

        examEndTime =
                System.currentTimeMillis();

        calculateScore();

        examStarted = false;

        showResult();
    }
    private void calculateScore() {
        score = 0;
    }
    // Result screen
    private JPanel createResultPanel() {

        JPanel panel =
                new JPanel(new BorderLayout(10, 10));

        JLabel title =
                new JLabel(
                        "EXAM RESULT",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        panel.add(title, BorderLayout.NORTH);

        resultLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        resultLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        panel.add(
                resultLabel,
                BorderLayout.CENTER
        );

        breakdownArea =
                new JTextArea();

        breakdownArea.setEditable(false);

        panel.add(
                new JScrollPane(
                        breakdownArea
                ),
                BorderLayout.SOUTH
        );

        JButton logoutButton =
                new JButton("Logout");

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(logoutButton);

        panel.add(
                buttonPanel,
                BorderLayout.PAGE_END
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        return panel;
    }


    private void showResult() {

        long timeTaken =
                (examEndTime - examStartTime)
                        / 1000;

        long minutes =
                timeTaken / 60;

        long seconds =
                timeTaken % 60;

        resultLabel.setText(
                "<html>"
                        + "Student: "
                        + displayName
                        + "<br>"
                        + "Score: "
                        + score
                        + " out of "
                        + questions.size()
                        + "<br>"
                        + "Time Taken: "
                        + minutes
                        + " minutes "
                        + seconds
                        + " seconds"
                        + "</html>"
        );

        breakdownArea.setText(
                "RESULT BREAKDOWN\n"
                        + "-------------------------\n"
                        + "Total Questions: "
                        + questions.size()
                        + "\n"
                        + "Correct Answers: "
                        + score
                        + "\n"
                        + "Incorrect Answers: "
                        + (questions.size() - score)
        );

        cardLayout.show(
                mainPanel,
                "RESULT"
        );
}
    // Logout method
    private void logout() {

        if (timer != null) {
            timer.stop();
        }

        examStarted = false;

        usernameField.setText("");
        passwordField.setText("");

        cardLayout.show(
                mainPanel,
                "LOGIN"
        );
    }
    // Main

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new OnlineExaminationSystem()
        );
    }
// class question
    static class Question {

        String question;
        String[] options;
        int correctAnswer;

        Question(
                String question,
                String[] options,
                int correctAnswer) {

            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
}