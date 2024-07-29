package com.wynnblevins.CourseEnrollment.controllers;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Teacher;
import com.wynnblevins.CourseEnrollment.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/api/teachers")
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/api/teachers/{id}")
    public Teacher getTeacher(@PathVariable("id") Long id) throws NotFoundException {
        return teacherService.getTeacherById(id);
    }

    @PostMapping("/api/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("/api/teachers/{id}")
    public Teacher updateTeacher(
            @RequestBody Teacher teacher,
            @PathVariable Long id
    ) throws NotFoundException {
        return teacherService.updateTeacher(id, teacher);
    }

    @DeleteMapping("/api/teachers/{id}")
    public Teacher deleteTeacher(@PathVariable Long id) throws NotFoundException {
        return teacherService.deleteTeacher(id);
    }
}
