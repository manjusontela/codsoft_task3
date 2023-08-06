package codsoft;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
public class StudentManagementSystem extends JFrame implements ActionListener {

	private ArrayList<Student> students;
    private JTextArea textArea;
    private JTextField nameField, ageField, courseField, rollnoField;

    private static final String FILE_PATH = "students.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();

        setTitle("Student Management System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8,7));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);
        inputPanel.add(new JLabel("Rollno:"));
        rollnoField = new JTextField();
        inputPanel.add(rollnoField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        JButton editButton = new JButton("Edit Student");
        editButton.addActionListener(this);
        inputPanel.add(editButton);

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(this);
        inputPanel.add(searchButton);

        JButton displayButton = new JButton("Display Students");
        displayButton.addActionListener(this);
        inputPanel.add(displayButton);
        
        JButton removeButton = new JButton("remove Student");
        removeButton.addActionListener(this);
        inputPanel.add(removeButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        inputPanel.add(exitButton);

        add(inputPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Load students from file
        loadStudents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Add Student")) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();
            int rollno=Integer.parseInt(rollnoField.getText());

            Student student = new Student(name, age, course, rollno);
            students.add(student);
            nameField.setText("");
            ageField.setText("");
            courseField.setText("");
            rollnoField.setText("");
            saveStudents(); // Save updated list of students to file
        } else if (actionCommand.equals("Edit Student")) {
            String nameToEdit = nameField.getText();
            editStudent(nameToEdit);
            saveStudents(); // Save updated list of students to file
        } else if (actionCommand.equals("Search Student")) {
            String nameToSearch = nameField.getText();
            searchStudent(nameToSearch);
        } else if (actionCommand.equals("Display Students")) {
            displayStudents();
        } else if (actionCommand.equals("Exit")) {
            System.exit(0);
        }
        
    }

    private void displayStudents() {
        textArea.setText("");
        for (Student student : students) {
            textArea.append(student.toString() + "\n");
        }
    }

    private void removeStudent(String nameToRemove) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(nameToRemove)) {
                students.remove(i);
                break;
            }
        }
    }

    private void editStudent(String nameToEdit) {
        for (Student student : students) {
            if (student.getName().equals(nameToEdit)) {
                student.setName(nameField.getText());
                student.setAge(Integer.parseInt(ageField.getText()));
                student.setCourse(courseField.getText());
                student.setrollno(Integer.parseInt(rollnoField.getText()));
                break;
            }
        }
    }

    private void searchStudent(String nameToSearch) {
        textArea.setText("");
        for (Student student : students) {
            if (student.getName().equals(nameToSearch)) {
                textArea.append(student.toString() + "\n");
                return; // Found the student, so no need to search further.
            }
        }
        textArea.append("Student not found.\n");
    }

    private void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String course = data[2];
                int rollno=Integer.parseInt(data[3]);
                students.add(new Student(name, age, course, rollno));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getAge() + "," + student.getCourse()+","+student.getrollno());
                writer.newLine();
            }
        } catch (IOException e) 
        {
        	e.printStackTrace();
        }
            
        }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(() -> new StudentManagementSystem().setVisible(true));
	}

}
