package com.sms.service;

import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.repository.StudentRepository;

import java.util.Comparator;
import java.util.List;

public class StudentService {

    private final StudentRepository repository;

    public StudentService() {
        this.repository = new StudentRepository();
        loadSampleData();
    }

    // Load initial sample data
    private void loadSampleData() {
        repository.save(new Student(0, "Ravi Kumar",     21, "ravi@email.com",   "Computer Science", 8.5));
        repository.save(new Student(0, "Priya Sharma",   20, "priya@email.com",  "Electronics",      7.8));
        repository.save(new Student(0, "Arjun Reddy",    22, "arjun@email.com",  "Computer Science", 9.1));
        repository.save(new Student(0, "Sneha Patel",    21, "sneha@email.com",  "Mechanical",       7.2));
        repository.save(new Student(0, "Kiran Babu",     23, "kiran@email.com",  "Civil",            6.9));
    }

    // Add student with validation
    public Student addStudent(String name, int age, String email, String course, double gpa)
            throws IllegalArgumentException {

        validateName(name);
        validateAge(age);
        validateEmail(email);
        validateCourse(course);
        validateGpa(gpa);

        Student student = new Student(0, name.trim(), age, email.trim(), course.trim(), gpa);
        return repository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Get student by ID
    public Student getStudentById(int id) throws StudentNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found."));
    }

    // Search by name
    public List<Student> searchByName(String name) {
        return repository.findByName(name);
    }

    // Search by course
    public List<Student> searchByCourse(String course) {
        return repository.findByCourse(course);
    }

    // Update student
    public Student updateStudent(int id, String name, int age, String email, String course, double gpa)
            throws StudentNotFoundException, IllegalArgumentException {

        validateName(name);
        validateAge(age);
        validateEmail(email);
        validateCourse(course);
        validateGpa(gpa);

        Student updated = new Student(id, name.trim(), age, email.trim(), course.trim(), gpa);
        return repository.update(id, updated);
    }

    // Delete student
    public void deleteStudent(int id) throws StudentNotFoundException {
        repository.deleteById(id);
    }

    // Get top students by GPA
    public List<Student> getTopStudents(int count) {
        List<Student> all = repository.findAll();
        all.sort(Comparator.comparingDouble(Student::getGpa).reversed());
        return all.subList(0, Math.min(count, all.size()));
    }

    // Get total student count
    public int getTotalCount() {
        return repository.count();
    }

    // Get average GPA
    public double getAverageGpa() {
        List<Student> all = repository.findAll();
        if (all.isEmpty()) return 0.0;
        return all.stream().mapToDouble(Student::getGpa).average().orElse(0.0);
    }

    // ─── Validators ────────────────────────────────────────────────────────────

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        if (name.trim().length() < 2)
            throw new IllegalArgumentException("Name must be at least 2 characters.");
    }

    private void validateAge(int age) {
        if (age < 16 || age > 100)
            throw new IllegalArgumentException("Age must be between 16 and 100.");
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains("."))
            throw new IllegalArgumentException("Please enter a valid email address.");
    }

    private void validateCourse(String course) {
        if (course == null || course.trim().isEmpty())
            throw new IllegalArgumentException("Course cannot be empty.");
    }

    private void validateGpa(double gpa) {
        if (gpa < 0.0 || gpa > 10.0)
            throw new IllegalArgumentException("GPA must be between 0.0 and 10.0.");
    }
}
