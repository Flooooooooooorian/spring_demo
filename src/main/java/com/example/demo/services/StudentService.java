package com.example.demo.services;

import com.example.demo.database.StudentDatabase;
import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private StudentDatabase studentDb = new StudentDatabase();

    public List<Student> getStudents() {
        return studentDb.getStudents();
    }

    public Optional<Student> getStudentById(String id) {
        Student student = this.studentDb.getStudentById(id);
        if (student != null) {
            return Optional.of(student);
        }
        return Optional.empty();
    }

    public Optional<Student> addStudent(Student student) {

        if (getStudentById(student.getId()).isEmpty()) {
            this.studentDb.addStudent(student);
            return Optional.of(student);
        }
        return Optional.empty();
    }

    public List<Student> getStudentsByName(String name) {
        return studentDb.getStudentsByName(name);
    }

    public Student updateStudent(Student student) {
        return studentDb.addStudent(student);
    }
}
