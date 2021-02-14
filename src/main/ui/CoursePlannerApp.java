package ui;

import model.Course;
import model.CoursePlanner;
import model.Date;
import model.Task;

import java.util.Scanner;

public class CoursePlannerApp {

    private CoursePlanner cp;
    private Scanner input;
    private boolean keepRunning;

    private static final String addCourseCommand = "add course";
    private static final String addTaskCommandExisting = "add task";
    private static final String removeTaskCommand = "remove";
    private static final String getTaskInfo = "get info";
    private static final String quitCommand = "quit";


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

    private void printInstructions() {
        System.out.println("\nEnter '" + addCourseCommand + "' to add a course to your planner");
        System.out.println("Enter '" + addTaskCommandExisting + "' to add a task to a course");
        System.out.println("Enter '" + removeTaskCommand + "' to remove a task in a course");
        System.out.println("Enter '" + getTaskInfo + "' to view a specific task");
        System.out.println("\nTo quit, enter '" + quitCommand + "' ");
    }

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
        System.out.println("What year is this due? (yyyy/mm/dd)");
        int taskDateYear = input.nextInt();

        System.out.println("What month is this due? (yyyy/mm/dd)");
        int taskDateMonth = input.nextInt();

        System.out.println("What day is this due? (yyyy/mm/dd)");
        int taskDateDay = input.nextInt();

        System.out.println("What time (hour) is this due? (hh:mm)");
        int taskDateHour = input.nextInt();

        System.out.println("What time (minute) is this due? (hh:mm)");
        int taskDateMin = input.nextInt();

        Date thisDate = new Date(taskDateYear, taskDateMonth, taskDateDay, taskDateHour, taskDateMin);
        return thisDate;
    }

    private void removeTask() {
        System.out.println("What course's tasks would you like to remove? (choose from: " + cp.getCourses() + ")");
        String course = input.next();

        if (!(cp.getCourses().isEmpty()) && cp.getCourses().contains(course)) {
            System.out.println("Enter the task you want to remove (choose from: "
                    + cp.getCourseObj(course).getTaskNames() + ")");

            String task = input.next();

            Task t = cp.getCourseObj(course).getTaskObj(task);
            cp.getCourseObj(course).removeTask(t);

            if (!(cp.getCourseObj(course).getTaskNames().contains(task))) {
                System.out.println("Task deleted successfully!");
                System.out.println("Your task list now: " + cp.getCourseObj(course).getTaskNames());
            }
        } else {
            System.out.println("This course or task does not exist");
        }
        printInstructions();
    }

    private void getTaskInfo() {
        System.out.println("What course's tasks would you like to look at? (choose from: " + cp.getCourses() + ")");
        String course = input.next();

        System.out.println("What task would you like to look at? (choose from: "
                + cp.getCourseObj(course).getTaskNames() + ")");
        String task = input.next();

        if (cp.getCourseObj(course).getTaskNames().contains(task) && cp.getCourses().contains(course)) {
            String date = cp.getCourseObj(course).getTaskObj(task).getTaskDueDate();
            String time = cp.getCourseObj(course).getTaskObj(task).getTaskTime();

            String taskName = cp.getCourseObj(course).getTaskObj(task).getTaskName();
            int taskWeight = cp.getCourseObj(course).getTaskObj(task).getTaskGradeWeight();

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
