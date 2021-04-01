package ui;

import model.Course;
import model.CoursePlanner;
import model.Date;
import model.Task;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.HashMap;

// Represents the GUI for the CoursePlanner
public class GUI extends JFrame {
    private JPanel panel;
    private JPanel coursePanel;
    private Box mainBox;
    private CoursePlanner cp;
    HashMap<Course, JFrame> courseJFrames;
    HashMap<Course, JPanel> courseJPanels;

    private String courseIconPath = "data/Course_Icon.png";
    private String taskIconPath = "data/Task_Icon.jpeg";
    private String openIconPath = "data/Open_Icon.jpeg";
    private String saveIconPath = "data/Save_Icon.png";


    //--------------------------- Set up -------------------------------

    //Constructor
    public GUI() {
        cp = new CoursePlanner();
        mainBox = Box.createHorizontalBox();
        courseJFrames = new HashMap<>();
        courseJPanels = new HashMap<>();
        makePanel();
        makeCoursePanel();
        makeFrame();
        makeButtons();

    }

    // MODIFIES: this
    // EFFECTS: initializes this JFrame with width 500, height 500
    private void makeFrame() {
        setSize(350, 350);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSplitPane splitPane = new JSplitPane();
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(5);
        splitPane.setTopComponent(panel);
        splitPane.setBottomComponent(coursePanel);
        update();

    }

    // MODIFIES: this
    // EFFECTS: creates a JPanel of width 350 and height 350 and adds it to this JFrame
    private void makePanel() {
        panel = new JPanel();
        panel.setSize(350, 350);
        add(panel);
    }

    // MODIFIES: this
    // EFFECTS: creates a JPanel of width 350 and height 350 and adds it to this JFrame
    private void makeCoursePanel() {
        coursePanel = new JPanel();
        coursePanel.setSize(350, 350);
        add(coursePanel);
        update();
    }

    // MODIFIES: this
    // EFFECTS: makes buttons for this JFrame
    private void makeButtons() {
        makeAddCourseButton();
        makeSaveButton();
        makeOpenButton();
        update();
    }

    // EFFECTS: automatically refreshes JPanels to display any changes made
    private void update() {
        panel.revalidate();
        panel.repaint();
        coursePanel.revalidate();
        coursePanel.repaint();
    }

    //--------------------------- Buttons/General GUI -------------------------------

    // MODIFIES: this
    // EFFECTS: creates the "Add Course" button
    private void makeAddCourseButton() {
        JButton newCourse = new JButton("Add Course", createButtonIcon(courseIconPath));
        newCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(newCourse);
        update();

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
        update();
    }

    // MODIFIES: this
    // EFFECTS: creates the "Add Task" button in each respective window
    private void makeAddTaskButton(Course c, JPanel panel) {
        JButton newTask = new JButton("Add new task", createButtonIcon(taskIconPath));
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
        update();

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
        panel.add(taskButton);
        update();
    }

    // MODIFIES: this
    // EFFECTS: creates the add course button
    private void addCourseButton(Course course) {
        JButton button = new JButton(course.getCourseName());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        coursePanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame secondFrame = createSecondPanel(course);
                if (!courseJFrames.containsKey(course)) {
                    JPanel secondPanel = new JPanel();
                    secondPanel.setLayout(new BoxLayout(secondPanel, 3));
                    secondFrame.getContentPane().add(secondPanel);
                    secondPanel.setSize(200, 200);
                    secondFrame.setSize(200, 200);

                    courseJFrames.put(course, secondFrame);

                    makeAddTaskButton(course, secondPanel);
                } else {
                    secondFrame = courseJFrames.get(course);
                }
                secondFrame.setVisible(true);
                update();
            }
        });
        update();
    }

    //EFFECTS: creates a second window for each new course button
    private JFrame createSecondPanel(Course course) {
        JFrame secondFrame = new JFrame("Tasks for " + course.getCourseName());
        return secondFrame;
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

    //--------------------------- Save/Open Features -------------------------------

    // MODIFIES: this
    // EFFECTS: creates the "Save" button
    private void makeSaveButton() {
        JButton save = new JButton("Save", createButtonIcon(saveIconPath));
        save.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(save);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        panel.add(save);
        update();
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
        JButton open = new JButton("Open", createButtonIcon(openIconPath));
        open.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(open);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        panel.add(open);
        update();
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
            //recreates the data into buttons
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

    // MODIFIES: this
    // EFFECTS: Recreates the buttons for each course and task in the current list. Adds buttons to this JFrame
    private void remakeButtons() {
        for (Course course : cp.getCourseList()) {
            addCourseButton(course);
            remakeCourseButton(course);
            makeAddTaskButton(course, courseJPanels.get(course));

            for (Task task : course.getTaskList()) {
                addTaskButton(course, task.getTaskName(), courseJPanels.get(course));
                update();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates the add course button as well as remakes all existing courses and their tabs
    private void remakeCourseButton(Course course) {
        JFrame secondFrame = createSecondPanel(course);
        if (!courseJFrames.containsKey(course)) {
            JPanel secondPanel = new JPanel();
            secondPanel.setLayout(new BoxLayout(secondPanel, 3));
            secondFrame.getContentPane().add(secondPanel);
            secondPanel.setSize(200, 200);
            secondFrame.setSize(200, 200);

            courseJFrames.put(course, secondFrame);
            courseJPanels.put(course, secondPanel);
        } else {
            secondFrame = courseJFrames.get(course);
        }
        secondFrame.setVisible(true);
        update();
    }


    //--------------------------- Additional Feature (Displaying image icons)  -------------------------------

    //EFFECTS: creates an Icon for the specified button
    private Icon createButtonIcon(String path) {
        BufferedImage categoryImage = null;
        try {
            categoryImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Cant find image file", "Status", JOptionPane.ERROR_MESSAGE);
        }
        Icon icon = new ImageIcon((Image) categoryImage.getScaledInstance(
                20, 20, Image.SCALE_DEFAULT));
        return icon;
    }

    public static void main(String[] args) {
        new GUI();
    }

}
