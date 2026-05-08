package com.sms.repository;

import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private final List<Student> students = new ArrayList<>();
    private int nextId = 1;

    // Add a student
    public Student save(Student student) {
        student = new Student(nextId++, student.getName(), student.getAge(),
                student.getEmail(), student.getCourse(), student.getGpa());
        students.add(student);
        return student;
    }

    // Find all students
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    // Find by ID
    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    // Find by name (case-insensitive)
    public List<Student> findByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    // Find by course
    public List<Student> findByCourse(String course) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getCourse().equalsIgnoreCase(course)) {
                result.add(s);
            }
        }
        return result;
    }

    // Update student
    public Student update(int id, Student updated) throws StudentNotFoundException {
        Student existing = findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found."));

        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setEmail(updated.getEmail());
        existing.setCourse(updated.getCourse());
        existing.setGpa(updated.getGpa());
        return existing;
    }

    // Delete student
    public void deleteById(int id) throws StudentNotFoundException {
        Student student = findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found."));
        students.remove(student);
    }

    // Total count
    public int count() {
        return students.size();
    }
}
