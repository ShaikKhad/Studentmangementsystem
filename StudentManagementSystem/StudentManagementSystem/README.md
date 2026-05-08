# 🎓 Student Management System — Java Project

A **console-based Java application** for managing student records, built using core Java concepts ideal for a fresher resume.

---

## 📌 Project Overview

| Field       | Details                         |
|-------------|---------------------------------|
| Language    | Java (JDK 11+)                  |
| Type        | Console Application             |
| Architecture| 3-Layer (Repository/Service/UI) |
| Pattern     | OOP + MVC-inspired              |

---

## ✨ Features

- ✅ **Add** new student records
- ✅ **View** all students in a formatted table
- ✅ **Search** by Name, Course, or ID
- ✅ **Update** existing student details
- ✅ **Delete** student records with confirmation
- ✅ **Top Students** ranked by GPA
- ✅ **Statistics** — total count & average GPA
- ✅ **Input Validation** with meaningful error messages
- ✅ **Custom Exception Handling** (`StudentNotFoundException`)

---

## 🏗️ Project Structure

```
StudentManagementSystem/
└── src/
    └── com/
        └── sms/
            ├── Main.java                          ← Entry point
            ├── model/
            │   └── Student.java                   ← Student entity
            ├── exception/
            │   └── StudentNotFoundException.java  ← Custom exception
            ├── repository/
            │   └── StudentRepository.java         ← Data layer (CRUD)
            ├── service/
            │   └── StudentService.java            ← Business logic
            └── ui/
                └── ConsoleUI.java                 ← Menu & user interaction
```

---

## 🚀 How to Run

### Option 1 — Command Line

```bash
# Step 1: Navigate to project root
cd StudentManagementSystem

# Step 2: Compile all Java files
javac -d out src/com/sms/model/*.java \
             src/com/sms/exception/*.java \
             src/com/sms/repository/*.java \
             src/com/sms/service/*.java \
             src/com/sms/ui/*.java \
             src/com/sms/Main.java

# Step 3: Run the application
java -cp out com.sms.Main
```

### Option 2 — IntelliJ IDEA / Eclipse

1. Open the project folder in your IDE
2. Mark `src` as **Sources Root**
3. Run `Main.java`

---

## 🧩 Java Concepts Used

| Concept                  | Where Used                        |
|--------------------------|-----------------------------------|
| OOP (Encapsulation)      | `Student` model with getters/setters |
| Inheritance              | `StudentNotFoundException` extends `Exception` |
| Collections (ArrayList)  | `StudentRepository` data storage  |
| Streams & Lambda         | Filtering and sorting students    |
| Exception Handling       | Try-catch, custom exceptions      |
| Scanner (I/O)            | Console input in `ConsoleUI`      |
| String formatting        | Table display with `String.format`|
| Optional                 | Safe null handling in repository  |

---

## 📝 Resume Description (Copy-Paste Ready)

> **Student Management System** | Java | Console Application  
> Developed a full-featured student record management system using Core Java.  
> Implemented CRUD operations with a 3-layer architecture (Repository, Service, UI).  
> Applied OOP principles, custom exception handling, Java Collections, and Stream API.  
> Features include student search, GPA ranking, input validation, and statistics dashboard.

---

## 👨‍💻 Author

Built as a fresher resume project demonstrating Core Java skills.
