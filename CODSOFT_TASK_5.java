import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Student class representing individual students
class Student {
    private String name;
    private String rollNumber;
    private String grade;

    // Constructor
    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Display student information
    public void displayStudentInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Grade: " + grade);
    }

    @Override
    public String toString() {
        return name + " (" + rollNumber + ")";
    }
}

// StudentManagementSystem class to manage student data
public class CODSOFT_TASK_5{
    private List<Student> students;
    private Scanner scanner;

    public CODSOFT_TASK_5() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Add a new student
    public void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        students.add(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully!");
    }

    // Remove a student
    public void removeStudent() {
        System.out.print("Enter roll number of student to remove: ");
        String rollNumber = scanner.nextLine();
        
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                studentToRemove = student;
                break;
            }
        }
        
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search for a student
    public void searchStudent() {
        System.out.print("Enter roll number to search: ");
        String rollNumber = scanner.nextLine();
        
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                student.displayStudentInfo();
                return;
            }
        }
        
        System.out.println("Student not found.");
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        
        for (Student student : students) {
            student.displayStudentInfo();
            System.out.println();
        }
    }

    // Save students to a file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
            System.out.println("Students saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Load students from a file
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("Students loaded from file successfully!");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // Main menu
    public void displayMenu() {
        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Students to File");
            System.out.println("6. Load Students from File");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.out.print("Enter filename to save to: ");
                    String saveFilename = scanner.nextLine();
                    saveToFile(saveFilename);
                    break;
                case 6:
                    System.out.print("Enter filename to load from: ");
                    String loadFilename = scanner.nextLine();
                    loadFromFile(loadFilename);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        CODSOFT_TASK_5 sms = new CODSOFT_TASK_5();
        sms.displayMenu();
    }
}
