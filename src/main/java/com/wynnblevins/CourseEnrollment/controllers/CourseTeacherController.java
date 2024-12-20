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
    	courseTeacherService.deleteAllByTeacherId(teacherId);
    }
    
    @GetMapping("/api/courseTeachers/teachers/{teacherId}")
    public List<CourseTeacher> getCourseTeachersByTeacherId(@PathVariable Long teacherId) {
    	return courseTeacherService.getAllCourseTeachersByTeacherId(teacherId);
    }
    
    @DeleteMapping("/api/courseTeachers/courses/{courseId}")
    public void deleteCourseTeachersByCourseId(@PathVariable Long courseId) 
    		throws NotFoundException {
    	courseTeacherService.deleteAllByCourseId(courseId);
    }
    
    @GetMapping("/api/courseTeachers/courses/{courseId}")
    public void deleteCourseTeachersForCourseId(@PathVariable Long courseId) 
    		throws NotFoundException {
    	courseTeacherService.deleteAllByCourseId(courseId);
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
