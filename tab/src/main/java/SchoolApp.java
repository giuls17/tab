import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SchoolApp {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextArea studentTextArea;
    private JTextArea professorTextArea;
    private Map<String, String> studentData;
    private Map<String, String> professorData;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SchoolApp window = new SchoolApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SchoolApp() {
        initialize();
        studentData = new HashMap<>();
        professorData = new HashMap<>();
    }

    private void initialize() {
        frame = new JFrame("School App");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Creating the "Professore" panel
        JPanel professorPanel = new JPanel();
        professorPanel.setLayout(new GridLayout(4, 2));

        JLabel professorNameLabel = new JLabel("Nome:");
        JTextField professorNameField = new JTextField();
        JLabel professorSurnameLabel = new JLabel("Cognome:");
        JTextField professorSurnameField = new JTextField();
        JLabel professorSubjectLabel = new JLabel("Materia:");
        JTextField professorSubjectField = new JTextField();

        JButton professorSaveButton = new JButton("OK");

        professorPanel.add(professorNameLabel);
        professorPanel.add(professorNameField);
        professorPanel.add(professorSurnameLabel);
        professorPanel.add(professorSurnameField);
        professorPanel.add(professorSubjectLabel);
        professorPanel.add(professorSubjectField);
        professorPanel.add(new JLabel()); // Empty cell
        professorPanel.add(professorSaveButton);

        tabbedPane.addTab("Professore", null, professorPanel, null);

        // Creating the "Studente" panel
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new GridLayout(4, 2));

        JLabel studentNameLabel = new JLabel("Nome:");
        JTextField studentNameField = new JTextField();
        JLabel studentSurnameLabel = new JLabel("Cognome:");
        JTextField studentSurnameField = new JTextField();
        JLabel studentAverageLabel = new JLabel("Media:");
        JTextField studentAverageField = new JTextField();

        JButton studentSaveButton = new JButton("OK");

        studentPanel.add(studentNameLabel);
        studentPanel.add(studentNameField);
        studentPanel.add(studentSurnameLabel);
        studentPanel.add(studentSurnameField);
        studentPanel.add(studentAverageLabel);
        studentPanel.add(studentAverageField);
        studentPanel.add(new JLabel()); // Empty cell
        studentPanel.add(studentSaveButton);

        tabbedPane.addTab("Studente", null, studentPanel, null);

        // Creating the text areas for displaying saved data
        studentTextArea = new JTextArea();
        studentTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font and size
        studentTextArea.setEditable(false); // Make it non-editable
        professorTextArea = new JTextArea();
        professorTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font and size
        professorTextArea.setEditable(false); // Make it non-editable

        JPanel textAreaPanel = new JPanel(new GridLayout(1, 2));
        textAreaPanel.add(new JScrollPane(studentTextArea));
        textAreaPanel.add(new JScrollPane(professorTextArea));
        frame.getContentPane().add(textAreaPanel, BorderLayout.SOUTH);

        studentSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = studentNameField.getText();
                String surname = studentSurnameField.getText();
                String average = studentAverageField.getText();

                studentData.put(name + " " + surname, "Media: " + average);
                updateStudentTextArea();

                studentNameField.setText("");
                studentSurnameField.setText("");
                studentAverageField.setText("");
            }
        });

        professorSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = professorNameField.getText();
                String surname = professorSurnameField.getText();
                String subject = professorSubjectField.getText();

                professorData.put(name + " " + surname, "Materia: " + subject);
                updateProfessorTextArea();

                professorNameField.setText("");
                professorSurnameField.setText("");
                professorSubjectField.setText("");
            }
        });
    }

    private void updateStudentTextArea() {
        studentTextArea.setText("");
        for (Map.Entry<String, String> entry : studentData.entrySet()) {
            studentTextArea.append("Studente: " + entry.getKey() + " - " + entry.getValue() + "\n");
        }
    }

    private void updateProfessorTextArea() {
        professorTextArea.setText("");
        for (Map.Entry<String, String> entry : professorData.entrySet()) {
            professorTextArea.append("Professore: " + entry.getKey() + " - " + entry.getValue() + "\n");
        }
    }
}
