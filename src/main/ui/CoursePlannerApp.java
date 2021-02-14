package ui;

import model.Course;
import model.CoursePlanner;
import model.Task;

import java.util.Scanner;

public class CoursePlannerApp {

    private CoursePlanner cp;
    private Scanner input;
    private boolean keepRunning;



    public CoursePlannerApp() {
        keepRunning = false;
        runCP();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCP() {
        String command = null;

        init();

        while (!keepRunning) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();
            System.out.println();

            processCommand(command);

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye!");
    }

    private void init() {
        cp = new CoursePlanner();
        input = new Scanner(System.in);
    }


    private void processCommand(String command) {
        if (command.equals("c")) {
            //TODO: write function for adding course
            addNewCourse();

        } else if (command.equals("t")) {
            //TODO: write function for adding task
            addTask();

        } else if (command.equals("r")) {
            //TODO: write function for removing task
            removeTask();
        }

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> new course");
        System.out.println("\tt -> new task");
        System.out.println("\tr -> remove task");
        System.out.println("\tq -> quit");
    }

    private void addNewCourse() {
        System.out.println("Enter Course Code");


        String courseName = input.nextLine();

       // System.out.println(courseName);
        Course thisCourse = new Course(courseName);

        cp.addCourse(thisCourse);

       // cp.addCourse(new Course(courseName));
        System.out.println("Current Courses: " + cp.getCourses());

    }

    private void addTask() {
        System.out.println("Enter task name");
    }

    private void removeTask() {
        System.out.println("Enter task name you want to remove");
    }


}
