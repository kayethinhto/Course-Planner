package ui;

import model.Course;
import model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {
    private JPanel panel;
    private Box mainBox;
    private CoursePlanner cp;

    public GUI() {
        cp = new CoursePlanner();
        mainBox = Box.createHorizontalBox();
        makeFrame();
        makePanel();
        makeButtons();
    }

    // MODIFIES: this
    // EFFECTS: initializes this JFrame with width 500, height 500
    private void makeFrame() {
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

    }

    // MODIFIES: this
    // EFFECTS: creates the "Add Course" button
    private void makeAddCourseButton() {
        JButton  newCourse = new JButton("Add Course");
        newCourse.setBounds(50,50,150,50);
        panel.add(newCourse);

        newCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course course = new Course(getCourseName("Enter course name"));
                cp.addCourse(course);
                addCourseButton(course);
            }
        });
        panel.add(newCourse);

    }

    // MODIFIES: this
    // EFFECTS: Creates a specific button for a course
    private void addCourseButton(Course course) {
        JButton button = new JButton(course.getCourseName());
        panel.add(button);
    }

    // EFFECTS: prompts user for input. If user enters null or "Empty" (the default message) return null. Otherwise,
    //          return the user input
    public String getCourseName(String text) {
        String name = JOptionPane.showInputDialog(this, text, "Empty");
        if (name == null || name.equals("Empty")) {
            JOptionPane.showMessageDialog(this, "Invalid name");
            return null;
        } else {
            return name;
        }
    }

    public static void main(String[] args) {
        new GUI();
    }





}
