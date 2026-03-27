# Attendance Management System

**Simple Java student attendance system using file storage**

## Overview

This project is a console-based **Student Attendance Management System** implemented in Java.  
It demonstrates object-oriented design, file input/output, and basic user interaction. The system lets you add students, mark daily attendance, calculate attendance percentage, search students, and persist student details to a text file.

## Features
- Add and store student records
- Mark daily attendance (Present / Absent)
- Calculate and display attendance percentage per student
- Search students by ID or name
- Persist student details to `data/student.txt`

## Prerequisites
- **Java JDK 8** or later installed
- Basic familiarity with running Java programs from the command line
## Project structure
attendance-management-system/
├─ src/
│  └─ javaoops/
│     └─ Attendance.java
├─ data/
│  └─ student.txt        # sample data (optional)
├─ README.md
└─ .gitignore

## How to compile and run
From the repository root:
```bash
javac -d out src/javaoops/Attendance.java
java -cp out javaoops.Attendance
```

## License
This project is licensed under the [MIT License](LICENSE) — you are free to use, modify, and distribute this software with proper attribution.

## Future Improvements
This project is a simple console-based system, but it can be extended in many ways:

- **Database Integration**: Replace text file storage with a relational database (e.g., MySQL, PostgreSQL) for better scalability and reliability.
- **Graphical User Interface (GUI)**: Add a Swing/JavaFX or web-based frontend for easier interaction.
- **Reporting Features**: Generate attendance reports (daily, monthly, per student) in formats like PDF or Excel.
- **Authentication & Roles**: Add login functionality for teachers/admins with role-based access.
- **Notifications**: Integrate email or SMS alerts for low attendance.
- **Web Application**: Migrate to Spring Boot + REST APIs to make it accessible via browser or mobile app.
- **Cloud Deployment**: Host the system on platforms like AWS or Azure for real-world usage.
