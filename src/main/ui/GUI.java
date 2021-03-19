package ui;

import model.Course;
import model.CoursePlanner;
import model.Date;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setSize(500,500);
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
        panel.revalidate();
        panel.repaint();

    }

    // MODIFIES: this
    // EFFECTS: creates the "Add Course" button
    private void makeAddCourseButton() {
        JButton  newCourse = new JButton("Add Course");
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
        JButton  newTask = new JButton("Add new task");
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
                c.addTask(taskName,taskDate, taskWeight);

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


    public static void main(String[] args) {
        new GUI();
    }





}
