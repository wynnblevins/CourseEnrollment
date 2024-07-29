package com.wynnblevins.CourseEnrollment.controllers;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Student;
import com.wynnblevins.CourseEnrollment.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/api/students")
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/api/students/{id}")
    public Student getStudentById(@PathVariable("id") Long id) throws NotFoundException {
        return studentService.findStudentById(id);
    }

    @PostMapping("/api/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/api/students/{id}")
    public Student updateStudent(
            @PathVariable("id") Long id,
            @RequestBody Student student
    ) throws NotFoundException {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/api/students/{id}")
    public void deleteStudent(@PathVariable("id") Long id) throws NotFoundException {
        studentService.deleteStudent(id);
    }
}
