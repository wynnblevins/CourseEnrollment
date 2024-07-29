package com.wynnblevins.CourseEnrollment.controllers;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Course;
import com.wynnblevins.CourseEnrollment.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    
    @GetMapping("/api/courses/{courseId}")
    public Course getCourse(@PathVariable Long courseId) throws NotFoundException {
        return courseService.getCourse(courseId);
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
