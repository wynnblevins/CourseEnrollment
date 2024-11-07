package com.wynnblevins.CourseEnrollment.controllers;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.CourseTeacher;
import com.wynnblevins.CourseEnrollment.models.Enrollment;
import com.wynnblevins.CourseEnrollment.services.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CourseTeacherController {
    @Autowired
    private CourseTeacherService courseTeacherService;

    @GetMapping("/api/courseTeachers/{courseTeacherId}")
    public CourseTeacher getCourseTeacher(@PathVariable Long courseTeacherId) throws NotFoundException {
        return courseTeacherService.getCourseTeacherById(courseTeacherId);
    }

    @DeleteMapping("/api/courseTeachers/teachers/{teacherId}")
    public void deleteCourseTeacherByTeacherId(@PathVariable Long teacherId) {
    	System.out.println("within endpoint");
    	courseTeacherService.deleteAllByTeacherId(teacherId);
    }
    
    @GetMapping("/api/courseTeachers")
    public List<CourseTeacher> getCourseTeachers() {
        return courseTeacherService.getAll();
    }
    
    @PostMapping("/api/courseTeachers")
    public CourseTeacher createCourseTeacher(
    		@RequestBody CourseTeacher courseTeacher
    ) throws NotFoundException {
        return courseTeacherService.createCourseTeacher(courseTeacher);
    }

    @DeleteMapping("/api/courseTeachers/{courseTeacherId}")
    public void deleteCourseTeacher(@PathVariable Long courseTeacherId) throws NotFoundException {
        courseTeacherService.deleteCourseTeacherById(courseTeacherId);
    }
}
