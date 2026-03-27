package javaoops;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int snNo;
    private String id;
    private String name;
    private String branch;
    private String section;
    private int year;
    private int present = 0;
    private int absent = 0;

    public Student(int snNo, String id, String name, String branch, String section, int year) {
        this.snNo = snNo;
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.section = section;
        this.year = year;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
    public String getSection() { return section; }
    public int getYear() { return year; }

    public void markAttendance(char value) {
        if (value == 'P' || value == 'p') {
            present++;
        } else if (value == 'A' || value == 'a') {
            absent++;
        } else {
            System.out.println("Invalid input! Use P for Present or A for Absent.");
        }
    }

    public int getPresent() { return present; }
    public int getAbsent() { return absent; }

    public String toFormattedString() {
        return snNo + ". " + id + " , " + name + " , " + branch + " , " + section + " , " + year;
    }
}

public class Attendance {
    private static int workingDays = 0;

    public static void writeToFile(String filename, ArrayList<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                bw.write(s.toFormattedString());
                bw.newLine();
            }
            System.out.println("\n-- Student details successfully saved into the file --");
        } catch (IOException e) {
            System.out.println("Error while writing the file: " + e.getMessage());
        }
    }

    public static void readFromFile(String filename) {
        System.out.println("\nReading student details from the file...");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void markDailyAttendance(ArrayList<Student> students, Scanner sc) {
        workingDays++;
        for (Student s : students) {
            System.out.print("ID: " + s.getId() + " (" + s.getName() + ") [P/A]: ");
            char value = sc.next().charAt(0);
            s.markAttendance(value);
        }
    }

    public static void calculateAttendance(ArrayList<Student> students) {
        System.out.println("\n----- Attendance Report -----");
        for (Student s : students) {
            double percentage = (workingDays == 0) ? 0.0 : (s.getPresent() * 100.0 / workingDays);
            System.out.printf("ID: %s, Name: %s, Present: %d, Branch: %s, Section: %s, Total Days: %d, Attendance: %.2f%%\n",
                    s.getId(), s.getName(), s.getPresent(), s.getBranch(), s.getSection(), workingDays, percentage);
        }
    }

    public static void searchStudent(String filename, ArrayList<Student> students, Scanner sc) {
        System.out.print("Enter the name or ID to search: ");
        String key = sc.nextLine();
        boolean found = false;

        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(key) || s.getName().equalsIgnoreCase(key)) {
                double percentage = (workingDays == 0) ? 0.0 : (s.getPresent() * 100.0 / workingDays);
                System.out.printf("FOUND -> ID: %s, Name: %s, Attendance: %.2f%%\n", s.getId(), s.getName(), percentage);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student found with key: " + key);
        }
    }

    public static void addStudent(ArrayList<Student> students, Scanner sc) {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Branch: ");
        String branch = sc.nextLine();
        System.out.print("Enter Section: ");
        String section = sc.nextLine();
        System.out.print("Enter Year: ");
        int year = sc.nextInt();
        sc.nextLine(); // consume newline

        students.add(new Student(students.size() + 1, id, name, branch, section, year));
        System.out.println("Student added successfully!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter the number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter details of the students:");
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + ":");
            System.out.print("Enter ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Branch: ");
            String branch = sc.nextLine();
            System.out.print("Enter Section: ");
            String section = sc.nextLine();
            System.out.print("Enter Year: ");
            int year = sc.nextInt();
            sc.nextLine();

            students.add(new Student(i + 1, id, name, branch, section, year));
        }

        writeToFile("student.txt", students);
        readFromFile("student.txt");

        while (true) {
            System.out.println("\n----- Student Attendance Menu -----");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Daily Attendance");
            System.out.println("3. Calculate Attendance");
            System.out.println("4. Display All Students");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent(students, sc);
                    writeToFile("student.txt", students);
                    break;
                case 2:
                    markDailyAttendance(students, sc);
                    break;
                case 3:
                    calculateAttendance(students);
                    break;
                case 4:
                    readFromFile("student.txt");
                    break;
                case 5:
                    searchStudent("student.txt", students, sc);
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}