package ui;

import model.Course;
import model.CoursePlanner;
import model.Date;
import model.Task;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class GUI extends JFrame {
    private JPanel panel;
    private Box mainBox;
    private CoursePlanner cp;


    public GUI() {
        cp = new CoursePlanner();
        mainBox = Box.createHorizontalBox();
        makeFrame();
        makePanel();
        panel.setLayout(new BoxLayout(panel, 3));
        makeButtons();

    }

    // MODIFIES: this
    // EFFECTS: initializes this JFrame with width 500, height 500
    private void makeFrame() {
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    // MODIFIES: this
    // EFFECTS: creates a JPanel of width 500 and height 500 and adds it to this JFrame
    private void makePanel() {
        panel = new JPanel();
        panel.setSize(500, 500);
        add(panel);
    }

    // MODIFIES: this
    // EFFECTS: makes buttons for this JFrame
    private void makeButtons() {
        makeAddCourseButton();
        makeSaveButton();
        makeOpenButton();
        panel.revalidate();
        panel.repaint();

    }

    // MODIFIES: this
    // EFFECTS: creates the "Add Course" button
    private void makeAddCourseButton() {
        JButton newCourse = new JButton("Add Course");
        newCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(newCourse);

        newCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course course = new Course(getInputString("Enter course name"));
                cp.addCourse(course);
                addCourseButton(course);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(newCourse);

    }

    private void makeAddTaskButton(Course c, JPanel panel) {
        JButton newTask = new JButton("Add new task");
        newTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(newTask);

        newTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = getInputString("Enter Task name");

                int year = getInputInt("Enter the year this task is due");
                int month = getInputInt("Enter the month this task is due");
                int day = getInputInt("Enter the day this task is due");

                Date taskDate = new Date(year, month, day);

                int taskWeight = getInputInt("Enter weight");
                c.addTask(taskName, taskDate, taskWeight);

                addTaskButton(c, taskName, panel);
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(newTask);

    }

    // MODIFIES: this
    // EFFECTS: Creates a specific button for a task
    private void addTaskButton(Course c, String taskName, JPanel panel) {
        JButton taskButton = new JButton(taskName);
        taskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(taskButton);

        taskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = c.getTaskObj(taskName);
                String format = "Task name: " + task.getTaskName()
                        + "\nDue Date: " + task.getYear() + "/" + task.getMonth()
                        + "/" + task.getDay()
                        + "\nGrade weight: " + task.getTaskGradeWeight() + "%";
                JOptionPane.showMessageDialog(panel,
                        format,
                        taskName,
                        JOptionPane.PLAIN_MESSAGE);
                panel.revalidate();
                panel.repaint();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Creates a specific button for a course
    private void addCourseButton(Course course) {
        JButton button = new JButton(course.getCourseName());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame secondFrame;
                if (course.getJframe() == null) {
                    secondFrame = new JFrame("Tasks for " + course.getCourseName());
                    JPanel secondPanel = new JPanel();
                    secondPanel.setLayout(new BoxLayout(secondPanel, 3));
                    secondFrame.getContentPane().add(secondPanel);

                    course.setJframe(secondFrame);

                    makeAddTaskButton(course, secondPanel);
                } else {
                    secondFrame = course.getJframe();
                }
                secondFrame.setVisible(true);
                panel.revalidate();
                panel.repaint();
            }
        });

    }

    // EFFECTS: prompts user for input. If user enters null or "Empty" (the default message) return null. Otherwise,
    //          return the user input
    public String getInputString(String text) {
        String name = JOptionPane.showInputDialog(this, text, "Empty");
        if (name == null || name.equals("Empty")) {
            JOptionPane.showMessageDialog(this, "Invalid name");
            return null;
        } else {
            return name;
        }
    }

    // EFFECTS: prompts user for input. If user enters null or "Empty" (the default message) return null. Otherwise,
    //          return the user input
    public int getInputInt(String text) {
        String number = JOptionPane.showInputDialog(this, text, "Empty");
        if (number == null || number.equals("Empty")) {
            JOptionPane.showMessageDialog(this, "Invalid number");
            return 0;
        } else {
            return Integer.parseInt(number);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the "Save" button
    private void makeSaveButton() {
        JButton save = new JButton("Save");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(save);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        panel.add(save);
    }

    // EFFECTS: user inputs name of the file, and saves data as a JSON file
    private void saveFile() {
        String fileName = getInputString("Enter the name of the file");
        try {
            if (fileName == null) {
                throw new NullPointerException();
            }
            if (cp.getCoursePlannerName() == null) {
                String cpName = getInputString("Enter the name of your Course Planner");
                cp.setCoursePlannerName(cpName);
            }
            String fileLocation = "./data/" + fileName + ".json";
            JsonWriter writer = new JsonWriter(fileLocation);
            writer.open();
            writer.write(cp);
            writer.close();
            JOptionPane.showMessageDialog(panel, "Successfully saved!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panel, "Invalid file name", "Status", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            // pass it has already been handled
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the "Open" button
    private void makeOpenButton() {
        JButton save = new JButton("Open");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(save);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        panel.add(save);
    }

    // EFFECTS: user inputs name of the file, and opens the JSON file
    private void openFile() {
        String fileName = getInputString("Enter the name of the file you want to open (without the extension)");
        try {
            if (fileName == null) {
                throw new IOException();
            }
            fileName = "./data/" + fileName + ".json";
            JsonReader reader = new JsonReader(fileName);
            cp = reader.read();
            remakeButtons();
            JOptionPane.showMessageDialog(this,
                    "Opened " + cp.getCoursePlannerName() + " successfully!");
            panel.revalidate();
            panel.repaint();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Could not find file!", "Status", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidPathException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid name!", "Status", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remakeButtons() {
        for (Course course : cp.getCourseList()) {
            addCourseButton(course);
            for (Task task: course.getTaskList()) {
                JFrame secondFrame = new JFrame("Tasks for " + course.getCourseName());
                JPanel secondPanel = new JPanel();
                secondPanel.setLayout(new BoxLayout(secondPanel, 3));
                secondFrame.getContentPane().add(secondPanel);

                course.setJframe(secondFrame);
                makeAddTaskButton(course, secondPanel);
                addTaskButton(course,task.getTaskName(),secondPanel);
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    }


}
