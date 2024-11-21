package com.wynnblevins.CourseEnrollment.services;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Course;
import com.wynnblevins.CourseEnrollment.models.Teacher;
import com.wynnblevins.CourseEnrollment.repositories.CourseRepository;
import com.wynnblevins.CourseEnrollment.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseTeacherService courseTeacherService;

    public Course getCourse(Long courseId) throws NotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            throw new NotFoundException("Course does not exist");
        }
        return course.get();
    }

    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long courseId, Course course) throws NotFoundException {
        Optional<Course> foundCourse = courseRepository.findById(courseId);
        if (!foundCourse.isPresent()) {
            throw new NotFoundException("Course does not exist");
        }
        Course courseToUpdate = foundCourse.get();
        courseToUpdate.setName(course.getName());
        courseToUpdate.setEnrollments(course.getEnrollments());
        return courseRepository.save(courseToUpdate);
    }

    public void deleteCourse(Long courseId) throws NotFoundException {
        Optional<Course> foundCourse = courseRepository.findById(courseId);
        if (!foundCourse.isPresent()) {
            throw new NotFoundException("Course does not exist");
        }
        courseRepository.delete(foundCourse.get());
    }
}
