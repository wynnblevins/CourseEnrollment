package com.wynnblevins.CourseEnrollment.controllers;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Enrollment;
import com.wynnblevins.CourseEnrollment.models.Teacher;
import com.wynnblevins.CourseEnrollment.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/api/enrollments")
    public List<Enrollment> getEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/api/enrollments/{enrollmentId}")
    public Enrollment getEnrollment(Long enrollmentId) throws NotFoundException {
        return enrollmentService.getEnrollmentById(enrollmentId);
    }

    @PostMapping("/api/enrollments")
    public Enrollment createEnrollment(
    		@RequestBody Enrollment enrollment
    ) throws NotFoundException {
        return enrollmentService.createEnrollment(enrollment);
    }

    @DeleteMapping("/api/enrollments/{id}")
    public void deleteEnrollment(@PathVariable Long id) throws NotFoundException {
        enrollmentService.deleteEnrollment(id);
    }
}
