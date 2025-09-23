import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// Student class (Encapsulation)
class Student {
    private int id;
    private String name;
    private int age;

    // Constructor
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age;
    }

    // For file saving
    public String toFileString() {
        return id + "," + name + "," + age;
    }
}

public class StudentManagement {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static final String FILE_NAME = "students.txt";

    // Load students from file
    public static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                students.add(new Student(id, name, age));
            }
        } catch (FileNotFoundException e) {
            // If file doesn't exist, ignore (first run case)
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Save students to file
    public static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // Add student
    public static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        students.add(new Student(id, name, age));
        saveToFile(); // Save after change
        System.out.println("Student added successfully!\n");
    }

    // View students
    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found.\n");
            return;
        }
        System.out.println("\n--- Student Records ---");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println();
    }

    // Update student
    public static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new name: ");
                s.setName(sc.nextLine());
                System.out.print("Enter new age: ");
                s.setAge(sc.nextInt());
                saveToFile();
                System.out.println("Student updated successfully!\n");
                return;
            }
        }
        System.out.println("Student not found!\n");
    }

    // Delete student
    public static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        boolean removed = students.removeIf(s -> s.getId() == id);
        if (removed) {
            saveToFile();
            System.out.println("Student deleted successfully.\n");
        } else {
            System.out.println("Student not found.\n");
        }
    }

    // Sort students
    public static void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.\n");
            return;
        }

        System.out.println("\nSort by:");
        System.out.println("1. Name (A-Z)");
        System.out.println("2. Age (Ascending)");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> {
                Collections.sort(students, Comparator.comparing(Student::getName));
                System.out.println("Students sorted by Name.\n");
            }
            case 2 -> {
                Collections.sort(students, Comparator.comparingInt(Student::getAge));
                System.out.println("Students sorted by Age.\n");
            }
            default -> System.out.println("Invalid choice.\n");
        }

        viewStudents();
    }

    // Main menu
    public static void main(String[] args) {
        // Load data from file at startup
        loadFromFile();

        while (true) {
            System.out.println("===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Sort Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> sortStudents();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option!\n");
            }
        }
    }
}
