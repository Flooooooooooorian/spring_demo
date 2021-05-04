package com.example.demo.services;

import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public List<Student> getStudents() {
        return List.copyOf(this.students.values());
    }

    public Optional<Student> getStudentById(String id) {
        Student student = this.students.get(id);
        if (student != null) {
            return Optional.of(student);
        }
        return Optional.empty();
    }

    public Optional<Student> addStudent(Student student) {
        if (!this.students.containsKey(student.getId())) {
            this.students.put(student.getId(), student);
            return Optional.of(student);
        }
        return Optional.empty();
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> students = new ArrayList<>();

        for (Student student : this.students.values()) {
            if (student.getName().equalsIgnoreCase(name)) {
                students.add(student);
            }
        }
        return students;
    }

    public Optional<Student> updateStudent(Student student) {
        if (this.students.containsKey(student.getId())) {
            this.students.put(student.getId(), student);
            return Optional.of(student);
        }
        else {
            return Optional.empty();
        }
    }
}
