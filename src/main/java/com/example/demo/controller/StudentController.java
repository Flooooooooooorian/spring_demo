package com.example.demo.controller;
import com.example.demo.model.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        Optional<Student> optionalStudent = this.studentService.getStudentById(id);
        return optionalStudent.orElse(null);
    }

    @RequestMapping()
    public List<Student> getStudents(@RequestParam Optional<String> id, @RequestParam Optional<String> name) {
        if (id.isPresent()) {
            return List.of(getStudent(id.get()));
        }
        else if(name.isPresent()) {
            return this.studentService.getStudentsByName(name.get());
        }
        else{
            return this.studentService.getStudents();
        }
    }

    @PostMapping()
    public Student addStudent(@Valid @RequestBody Student student) {
        Optional<Student> optionalStudent = this.studentService.addStudent(student);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not valid");
        }
    }

    @PutMapping("{id}")
    public Student updateStudent(@PathVariable String id, @Valid @RequestBody Student student) {
        if (!student.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not valid");
        }
        return this.studentService.updateStudent(student);
    }

    @DeleteMapping("{id}")
    public boolean deleteStudent(@PathVariable String id){
        return studentService.deleteStudent(id);
    }
}