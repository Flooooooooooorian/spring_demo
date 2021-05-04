package com.example.demo.database;

import com.example.demo.model.Student;

import java.util.*;

public class StudentDatabase {
    private final Map<String, Student> students = new HashMap<>();

    public List<Student> getStudents() {
        return List.copyOf(this.students.values());
    }


    public Student getStudentById(String id) {
        return students.get(id);
    }

    public Student addStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> students = new ArrayList<>();

        for (Student student : this.students.values()) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                students.add(student);
            }
        }
        return students;
    }
}
