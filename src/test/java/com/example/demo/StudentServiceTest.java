package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.services.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class StudentServiceTest {

    @Test
    void getStudents() {
        StudentService studentService = new StudentService();
        Student darth_vader = new Student("1", "Darth Vader");
        Student harry_potter = new Student("123", "Harry Potter");

        List<Student> studentList = studentService.getStudents();

        assertThat(studentList, containsInAnyOrder(harry_potter, darth_vader));
    }

    @Test
    void getStudentById() {
        StudentService studentService = new StudentService();
        Student darth_vader = new Student("1", "Darth Vader");
        Student harry_potter = new Student("123", "Harry Potter");

        Optional<Student> optionalHarry = studentService.getStudentById(harry_potter.getId());
        Optional<Student> optionalDarthVader = studentService.getStudentById(darth_vader.getId());

        assertThat(optionalHarry.isPresent(), equalTo(true));
        assertThat(optionalHarry.get(), equalTo(harry_potter));
        assertThat(optionalDarthVader.isPresent(), equalTo(true));
        assertThat(optionalDarthVader.get(), equalTo(darth_vader));
    }

    @ParameterizedTest
    @CsvSource({"2, Tony Stark, true",
                "3, Tony, false",
                "1, Yoda, false"})
    void addStudent(String id, String name, boolean result) {
        StudentService studentService = new StudentService();
        Student student = new Student(id, name);

        Optional<Student> optionalStudent = studentService.addStudent(student);

        assertThat(optionalStudent.isPresent(), equalTo(result));
    }

    @Test
    void getStudentsByName() {
    }

    @Test
    void updateStudent() {
        StudentService studentService = new StudentService();
        Student student = new Student("123", "Harry Propper");

        Optional<Student> optionalStudent = studentService.updateStudent(student);

        assertThat(optionalStudent.isPresent(), equalTo(true));
        assertThat(optionalStudent.get(), equalTo(student));
    }
}