package com.sms.ui;

import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final StudentService service;
    private final Scanner scanner;

    private static final String DIVIDER =
            "+------+----------------------+-----+---------------------------+-----------------+------+";
    private static final String HEADER  =
            "| ID   | Name                 | Age | Email                     | Course          | GPA  |";

    public ConsoleUI() {
        this.service = new StudentService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printBanner();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1  -> addStudent();
                case 2  -> viewAllStudents();
                case 3  -> searchStudent();
                case 4  -> updateStudent();
                case 5  -> deleteStudent();
                case 6  -> viewTopStudents();
                case 7  -> viewStatistics();
                case 0  -> { running = false; goodbye(); }
                default -> System.out.println("\n  [!] Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }

    // ─── Menu Options ───────────────────────────────────────────────────────────

    private void addStudent() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     ADD NEW STUDENT      ║");
        System.out.println("  ╚══════════════════════════╝\n");

        try {
            String name   = readString("  Enter Name   : ");
            int    age    = readInt   ("  Enter Age    : ");
            String email  = readString("  Enter Email  : ");
            String course = readString("  Enter Course : ");
            double gpa    = readDouble ("  Enter GPA    : ");

            Student student = service.addStudent(name, age, email, course, gpa);
            System.out.println("\n  ✔  Student added successfully! (ID: " + student.getId() + ")\n");

        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Error: " + e.getMessage() + "\n");
        }
    }

    private void viewAllStudents() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     ALL STUDENTS         ║");
        System.out.println("  ╚══════════════════════════╝\n");

        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("  No students found.\n");
            return;
        }
        printTable(students);
    }

    private void searchStudent() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     SEARCH STUDENT       ║");
        System.out.println("  ╚══════════════════════════╝");
        System.out.println("\n  1. Search by Name");
        System.out.println("  2. Search by Course");
        System.out.println("  3. Search by ID\n");

        int choice = readInt("  Enter choice: ");
        List<Student> results;

        switch (choice) {
            case 1 -> {
                String name = readString("  Enter name to search: ");
                results = service.searchByName(name);
                printTable(results);
            }
            case 2 -> {
                String course = readString("  Enter course to search: ");
                results = service.searchByCourse(course);
                printTable(results);
            }
            case 3 -> {
                int id = readInt("  Enter student ID: ");
                try {
                    Student s = service.getStudentById(id);
                    printTable(List.of(s));
                } catch (StudentNotFoundException e) {
                    System.out.println("\n  [!] " + e.getMessage() + "\n");
                }
            }
            default -> System.out.println("\n  [!] Invalid choice.\n");
        }
    }

    private void updateStudent() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     UPDATE STUDENT       ║");
        System.out.println("  ╚══════════════════════════╝\n");

        int id = readInt("  Enter Student ID to update: ");

        try {
            Student existing = service.getStudentById(id);
            System.out.println("\n  Current details:");
            printTable(List.of(existing));
            System.out.println("\n  Enter new details (press Enter to keep current):\n");

            String name   = readStringWithDefault("  Name   [" + existing.getName()   + "]: ", existing.getName());
            String ageStr = readStringWithDefault("  Age    [" + existing.getAge()    + "]: ", String.valueOf(existing.getAge()));
            String email  = readStringWithDefault("  Email  [" + existing.getEmail()  + "]: ", existing.getEmail());
            String course = readStringWithDefault("  Course [" + existing.getCourse() + "]: ", existing.getCourse());
            String gpaStr = readStringWithDefault("  GPA    [" + existing.getGpa()    + "]: ", String.valueOf(existing.getGpa()));

            int    age = Integer.parseInt(ageStr);
            double gpa = Double.parseDouble(gpaStr);

            service.updateStudent(id, name, age, email, course, gpa);
            System.out.println("\n  ✔  Student updated successfully!\n");

        } catch (StudentNotFoundException e) {
            System.out.println("\n  [!] " + e.getMessage() + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Validation Error: " + e.getMessage() + "\n");
        }
    }

    private void deleteStudent() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     DELETE STUDENT       ║");
        System.out.println("  ╚══════════════════════════╝\n");

        int id = readInt("  Enter Student ID to delete: ");

        try {
            Student student = service.getStudentById(id);
            System.out.println("\n  Student found: " + student.getName());
            String confirm = readString("  Are you sure? (yes/no): ");

            if (confirm.equalsIgnoreCase("yes")) {
                service.deleteStudent(id);
                System.out.println("  ✔  Student deleted successfully!\n");
            } else {
                System.out.println("  Deletion cancelled.\n");
            }
        } catch (StudentNotFoundException e) {
            System.out.println("\n  [!] " + e.getMessage() + "\n");
        }
    }

    private void viewTopStudents() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     TOP STUDENTS BY GPA  ║");
        System.out.println("  ╚══════════════════════════╝\n");

        int count = readInt("  How many top students to display? ");
        List<Student> top = service.getTopStudents(count);

        if (top.isEmpty()) {
            System.out.println("  No students found.\n");
        } else {
            printTable(top);
        }
    }

    private void viewStatistics() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     STATISTICS           ║");
        System.out.println("  ╚══════════════════════════╝\n");
        System.out.printf("  Total Students  : %d%n",   service.getTotalCount());
        System.out.printf("  Average GPA     : %.2f%n", service.getAverageGpa());
        System.out.println();
    }

    // ─── Helpers ────────────────────────────────────────────────────────────────

    private void printTable(List<Student> students) {
        System.out.println();
        System.out.println("  " + DIVIDER);
        System.out.println("  " + HEADER);
        System.out.println("  " + DIVIDER);
        for (Student s : students) {
            System.out.println("  " + s);
        }
        System.out.println("  " + DIVIDER);
        System.out.println();
    }

    private void printBanner() {
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║       STUDENT MANAGEMENT SYSTEM  v1.0        ║");
        System.out.println("  ║          Built with Java  |  2025            ║");
        System.out.println("  ╚══════════════════════════════════════════════╝");
        System.out.println();
    }

    private void printMenu() {
        System.out.println("  ┌──────────────────────────────┐");
        System.out.println("  │           MAIN MENU          │");
        System.out.println("  ├──────────────────────────────┤");
        System.out.println("  │  1. Add Student              │");
        System.out.println("  │  2. View All Students        │");
        System.out.println("  │  3. Search Student           │");
        System.out.println("  │  4. Update Student           │");
        System.out.println("  │  5. Delete Student           │");
        System.out.println("  │  6. Top Students by GPA      │");
        System.out.println("  │  7. Statistics               │");
        System.out.println("  │  0. Exit                     │");
        System.out.println("  └──────────────────────────────┘");
    }

    private void goodbye() {
        System.out.println("\n  Thank you for using Student Management System!");
        System.out.println("  Goodbye!\n");
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String readStringWithDefault(String prompt, String defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Please enter a valid number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Please enter a valid decimal number.");
            }
        }
    }
}
