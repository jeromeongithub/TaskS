package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        // printData(tasksData);
        System.out.println();
        System.out.println("Printing deadlines before Sorting");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Printing deadlines after sorting");
        printDeadlinesUsingStreams(tasksData);
        ArrayList<Task> filteredList = filterTaskListUsingSteams(tasksData,"11");
        System.out.println("Filtered list of tasks: ");
        printData(filteredList);
    }

    public static ArrayList<Task> filterTaskListUsingSteams(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream() // cast to array list since collect() returns list
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());

        return filteredList;
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks) {
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a,b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))
                .forEach(System.out::println);
    }
}
