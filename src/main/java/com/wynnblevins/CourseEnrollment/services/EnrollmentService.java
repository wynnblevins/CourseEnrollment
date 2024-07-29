package com.wynnblevins.CourseEnrollment.services;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Course;
import com.wynnblevins.CourseEnrollment.models.Enrollment;
import com.wynnblevins.CourseEnrollment.models.Student;
import com.wynnblevins.CourseEnrollment.repositories.CourseRepository;
import com.wynnblevins.CourseEnrollment.repositories.EnrollmentRepository;
import com.wynnblevins.CourseEnrollment.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<Enrollment> getAllEnrollments() {
        return (List<Enrollment>) enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) throws NotFoundException {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);

        if (!enrollment.isPresent()) {
            throw new NotFoundException("Enrollment not found");
        }

        return enrollment.get();
    }

    public Enrollment createEnrollment(Long studentId, Long courseId) throws NotFoundException {
        Optional<Course> maybeCourse = courseRepository.findById(courseId);
        Optional<Student> maybeStudent = studentRepository.findById(studentId);

        if (!maybeCourse.isPresent()) {
            throw new NotFoundException("Course for ID " + courseId + " not found");
        }

        if (!maybeStudent.isPresent()) {
            throw new NotFoundException("Student for ID " + studentId + " not found");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(maybeStudent.get());
        enrollment.setCourse(maybeCourse.get());

        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(@PathVariable Long id) throws NotFoundException {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(id);
        if (!enrollmentOptional.isPresent()) {
            throw new NotFoundException("Employee with ID " + id + " not found");
        }
        enrollmentRepository.deleteById(id);
    }
}
