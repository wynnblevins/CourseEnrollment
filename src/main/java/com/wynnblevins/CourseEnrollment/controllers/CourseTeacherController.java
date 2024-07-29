package com.wynnblevins.CourseEnrollment.controllers;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.CourseTeacher;
import com.wynnblevins.CourseEnrollment.services.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseTeacherController {
    @Autowired
    private CourseTeacherService courseTeacherService;

    @GetMapping("/api/courseTeachers/{courseTeacherId}")
    public CourseTeacher getCourseTeacher(@PathVariable Long courseTeacherId) throws NotFoundException {
        return courseTeacherService.getCourseTeacherById(courseTeacherId);
    }

    @GetMapping("/api/courseTeachers")
    public List<CourseTeacher> getCourseTeachers() {
        return courseTeacherService.getAll();
    }

    @PostMapping("/api/courseTeachers/courses/{courseId}/teachers/{teacherId}")
    public CourseTeacher createCourseTeacher(
            @PathVariable Long courseId,
            @PathVariable Long teacherId
    ) throws NotFoundException {
        return courseTeacherService.createCourseTeacher(courseId, teacherId);
    }

    @DeleteMapping("/api/courseTeachers/{courseTeacherId}")
    public void deleteCourseTeacher(@PathVariable Long courseTeacherId) throws NotFoundException {
        courseTeacherService.deleteCourseTeacherById(courseTeacherId);
    }
}
