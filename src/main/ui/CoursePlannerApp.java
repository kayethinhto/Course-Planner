package ui;

import model.Course;
import model.CoursePlanner;
import model.Date;
import model.Task;

import java.util.Scanner;

//Represents the the ui for the Course Planner App
public class CoursePlannerApp {

    private CoursePlanner cp;
    private Scanner input;
    private boolean keepRunning;

    private static final String addCourseCommand = "add course";
    private static final String addTaskCommandExisting = "add task";
    private static final String removeTaskCommand = "remove";
    private static final String getTaskInfo = "get info";
    private static final String quitCommand = "quit";

    //EFFECTS: 
    //Constructor
    public CoursePlannerApp() {
        cp = new CoursePlanner();
        input = new Scanner(System.in);
        keepRunning = true;
        userInput();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void userInput() {
        System.out.println("Welcome to the Course Planner!");
        printInstructions();
        String str;

        while (keepRunning) {
            if (input.hasNext()) {
                str = input.nextLine().toLowerCase();
                parseInput(str);
            }
        }
        System.out.println("Thanks for using the course planner!");
    }

    //EFFECTS: executes a given method based on a command given by the user. Prints
    //          "Try another command" if user input is invalid.
    private void parseInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case addCourseCommand:
                    addNewCourse();
                    break;

                case removeTaskCommand:
                    removeTask();
                    break;

                case addTaskCommandExisting:
                    addTaskExisting();
                    break;

                case getTaskInfo:
                    getTaskInfo();
                    break;

                case quitCommand:
                    keepRunning = false;
                    break;

                default:
                    System.out.println("Try another command");
                    break;
            }
        }
    }

    //EFFECTS: displays starting menu with available comands
    private void printInstructions() {
        System.out.println("\nEnter '" + addCourseCommand + "' to add a course to your planner");
        System.out.println("Enter '" + addTaskCommandExisting + "' to add a task to a course");
        System.out.println("Enter '" + removeTaskCommand + "' to remove a task in a course");
        System.out.println("Enter '" + getTaskInfo + "' to view a specific task");
        System.out.println("\nTo quit, enter '" + quitCommand + "' ");
    }

    //MODIFIES: this
    //EFFECTS: Creates a new course object and assigns user input as the name of said course
    private void addNewCourse() {
        System.out.println("Enter Course Code");
        String courseName = input.next();

        if (!cp.getCourses().contains(courseName)) {


            Course thisCourse = new Course(courseName);

            cp.addCourse(thisCourse);

            System.out.println("Current Courses: " + cp.getCourses());

        } else {
            System.out.println("Course planner already contains this course");
        }
        printInstructions();
    }

    //MODIFIES: this
    //REQUIRES: courses is not null
    //EFFECTS: Creates a new task, assigns user input as the name of the task and adds this task to the task list
    //         in a given course (also specified by the user). Course specified by user needs to exist.
    private void addTaskExisting() {
        System.out.println("What course would you like to add a task to? (choose from: " + cp.getCourses() + ")");
        String thisCourse = input.next();
        if (!(cp.getCourses().isEmpty()) && cp.getCourses().contains(thisCourse)) {

            System.out.println("Enter task name");
            String taskName = input.next();

            if (!(cp.getCourseObj(thisCourse).getTaskNames().contains(taskName))) {

                System.out.println("How much does the task weight?");
                int taskWeight = input.nextInt();

                Task thisTask = new Task(taskName, addTaskDate(), taskWeight);

                cp.getCourseObj(thisCourse).addTask(thisTask);
                System.out.println("Current Tasks in " + thisCourse + ": "
                        + cp.getCourseObj(thisCourse).getTaskNames());
            } else {
                System.out.println("This task already exists, try a new name");
            }
        } else {
            System.out.println("This course doesn't exist");
        }
        printInstructions();
    }

    private Date addTaskDate() {
        System.out.println("What year is this due? (____/mm/dd)");
        int taskDateYear = input.nextInt();

        System.out.println("What month is this due? (yyyy/__/dd)");
        int taskDateMonth = input.nextInt();

        System.out.println("What day is this due? (yyyy/mm/__)");
        int taskDateDay = input.nextInt();

        System.out.println("What time (hour) is this due? (__:mm)");
        int taskDateHour = input.nextInt();

        System.out.println("What time (minute) is this due? (hh:__)");
        int taskDateMin = input.nextInt();

        Date thisDate = new Date(taskDateYear, taskDateMonth, taskDateDay, taskDateHour, taskDateMin);
        return thisDate;
    }

    //EFFECTS: returns true if user inputs a course that is in the system
    private boolean getTask(String course) {
        return (!(cp.getCourses().isEmpty()) && cp.getCourses().contains(course));
    }

    //MODIFIES: this
    //EFFECTS: removes a task specified by the user. The course and the task being removed must exist
    private void removeTask() {
        System.out.println("What course's tasks would you like to remove? (choose from: " + cp.getCourses() + ")");
        String course = input.next();

        if (getTask(course)) {
            System.out.println("Enter the task you want to remove (choose from: "
                    + cp.getCourseObj(course).getTaskNames() + ")");

            String task = input.next();

            Task t = cp.getCourseObj(course).getTaskObj(task);
            cp.getCourseObj(course).removeTask(t);

            System.out.println("Your task list now: " + cp.getCourseObj(course).getTaskNames());

        } else {
            System.out.println("This course or task does not exist");
        }
        printInstructions();
    }

    //EFFECTS: return true if the course specified contains a given task (also specified by the user)
    public boolean checkCourseContainsTask(String course, String task) {
        return (cp.getCourseObj(course).getTaskNames().contains(task) && cp.getCourses().contains(course));
    }

    //EFFECTS: returns task-specific information specified by the user
    private void getTaskInfo() {
        System.out.println("What course's tasks would you like to look at? (choose from: " + cp.getCourses() + ")");
        String course = input.next();

        System.out.println("What task would you like to look at? (choose from: "
                + cp.getCourseObj(course).getTaskNames() + ")");
        String task = input.next();
        //add to model package
        if (checkCourseContainsTask(course, task)) {
            //
            String date = cp.getCourseObj(course).getTaskObj(task).getTaskDueDate();
            String time = cp.getCourseObj(course).getTaskObj(task).getTaskTime();

            String taskName = cp.getCourseObj(course).getTaskObj(task).getTaskName();
            int taskWeight = cp.getCourseObj(course).getTaskObj(task).getTaskGradeWeight();

            //move to model package

            System.out.println("Assignment Type: " + taskName);
            System.out.println("Assignment weighting: " + taskWeight + "%");
            System.out.println("Due Date: " + date);
            System.out.println("Time Due: " + time);
        } else {
            System.out.println("Course or task does not exist");
        }
        printInstructions();
    }

}
