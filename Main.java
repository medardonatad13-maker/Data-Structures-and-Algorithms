import java.util.HashMap;
import java.util.Scanner;

// ===== CLASS 1: Student (blueprint of one student) =====
class Student {
    private int id;        // Student ID (Key)
    private String name;   // Student Name (Value)

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " = " + name;
    }
}

// ===== CLASS 2: StudentManager (holds the HashMap and all operations) =====
class StudentManager {
    private HashMap<Integer, Student> students = new HashMap<Integer, Student>();

    // ADD ELEMENT (a duplicate ID replaces the old student)
    public void addStudent(int id, String name) {
        if (students.containsKey(id)) {
            System.out.println("Duplicate key! ID " + id + " already exists, so it was replaced.");
        }
        students.put(id, new Student(id, name));
        System.out.println("Student saved.");
    }

    // DISPLAY ALL STUDENTS
    public void displayAll() {
        System.out.println("All Students:");
        if (students.isEmpty()) {
            System.out.println("(no students)");
        } else {
            for (Student s : students.values()) {
                System.out.println(s);
            }
        }
    }

    // DISPLAY A SPECIFIC STUDENT
    public void displaySpecific(int id) {
        Student s = students.get(id);
        System.out.println("Student with ID " + id + ":");
        if (s == null) {
            System.out.println("Not found.");
        } else {
            System.out.println(s.getName());
        }
    }

    // UPDATE A STUDENT'S NAME
    public void updateStudent(int id, String newName) {
        Student s = students.get(id);
        if (s == null) {
            System.out.println("Student ID " + id + " not found.");
        } else {
            s.setName(newName);
            System.out.println("After Updating Student ID " + id + ":");
            displayAll();
        }
    }

    // REMOVE A STUDENT
    public void removeStudent(int id) {
        if (students.remove(id) == null) {
            System.out.println("Student ID " + id + " not found.");
        } else {
            System.out.println("After Removing Student ID " + id + ":");
            displayAll();
        }
    }

    // CHECK IF A KEY EXISTS
    public boolean exists(int id) {
        return students.containsKey(id);
    }

    // DISPLAY NUMBER OF STUDENTS
    public int getTotal() {
        return students.size();
    }

    // CLEAR ALL ELEMENTS
    public void clearAll() {
        students.clear();
        System.out.println("After Clearing All Students:");
        displayAll();
    }
}

// ===== CLASS 3: Main (user input and menu) =====
public class Main {
    static Scanner scanner = new Scanner(System.in);

    // Reads a whole number safely
    static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Reads text
    static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        int choice;

        do {
            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. Add student");
            System.out.println("2. Display all students");
            System.out.println("3. Display a specific student");
            System.out.println("4. Update a student's name");
            System.out.println("5. Remove a student");
            System.out.println("6. Check if a student ID exists");
            System.out.println("7. Display number of students");
            System.out.println("8. Clear all students");
            System.out.println("0. Exit");
            choice = readInt("Choose: ");
            System.out.println();

            switch (choice) {
                case 1:
                    int id = readInt("Enter Student ID: ");
                    String name = readText("Enter Student Name: ");
                    manager.addStudent(id, name);
                    break;
                case 2:
                    manager.displayAll();
                    break;
                case 3:
                    manager.displaySpecific(readInt("Enter Student ID: "));
                    break;
                case 4:
                    int uid = readInt("Enter Student ID to update: ");
                    String newName = readText("Enter new name: ");
                    manager.updateStudent(uid, newName);
                    break;
                case 5:
                    manager.removeStudent(readInt("Enter Student ID to remove: "));
                    break;
                case 6:
                    int cid = readInt("Enter Student ID to check: ");
                    System.out.println("Is Student ID " + cid + " existing?");
                    System.out.println(manager.exists(cid));
                    break;
                case 7:
                    System.out.println("Total Students:");
                    System.out.println(manager.getTotal());
                    break;
                case 8:
                    manager.clearAll();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
