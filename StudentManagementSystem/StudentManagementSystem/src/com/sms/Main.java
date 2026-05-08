package com.sms;

import com.sms.ui.ConsoleUI;

/**
 * Student Management System
 * ──────────────────────────
 * A console-based Java application to manage student records.
 *
 * Features:
 *   - Add, View, Update, Delete students (CRUD)
 *   - Search by Name, Course, or ID
 *   - View Top Students by GPA
 *   - Input Validation & Custom Exception Handling
 *   - Layered Architecture: Model → Repository → Service → UI
 *
 * Technologies: Core Java, OOP, Collections, Exception Handling
 *
 * @author  Student Management System Project
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.start();
    }
}
