package com.wynnblevins.CourseEnrollment.controllers;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Course;
import com.wynnblevins.CourseEnrollment.models.CourseTeacher;
import com.wynnblevins.CourseEnrollment.services.CourseService;
import com.wynnblevins.CourseEnrollment.services.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTeacherService courseTeacherService;
    
    @GetMapping("/api/courses/{courseId}")
    public Course getCourse(@PathVariable Long courseId) throws NotFoundException {
        return courseService.getCourse(courseId);
    }

    @GetMapping("/api/courses")
    public List<Course> getAllCoursesForTeacher(@RequestParam(required = false) Long teacherId) {
        if (teacherId != null) {
            List<Course> courses = new ArrayList<>();
            List<CourseTeacher> courseTeachers = courseTeacherService.getAllCourseTeachersByTeacherId(teacherId);
            courseTeachers.forEach(courseTeacher -> {
                courses.add(courseTeacher.getCourse());
            });
            return courses;
        }

        return courseService.getAllCourses();
    }

    @PostMapping("/api/courses")
    public Course addCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/api/courses/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course course
    ) throws NotFoundException {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/api/courses/{id}")
    public void deleteCourse(@PathVariable Long id) throws NotFoundException {
        courseService.deleteCourse(id);
    }
}
