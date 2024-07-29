package com.wynnblevins.CourseEnrollment.services;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Course;
import com.wynnblevins.CourseEnrollment.models.CourseTeacher;
import com.wynnblevins.CourseEnrollment.models.Teacher;
import com.wynnblevins.CourseEnrollment.repositories.CourseRepository;
import com.wynnblevins.CourseEnrollment.repositories.CourseTeacherRepository;
import com.wynnblevins.CourseEnrollment.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseTeacherService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseTeacherRepository courseTeacherRepository;

    public List<CourseTeacher> getAll() {
        return (List<CourseTeacher>) courseTeacherRepository.findAll();
    }

    public CourseTeacher getCourseTeacherById(Long id) throws NotFoundException {
        Optional<CourseTeacher> maybeCourseTeacher = courseTeacherRepository.findById(id);
        if (!maybeCourseTeacher.isPresent()) {
            throw new NotFoundException("Course Teacher Not Found");
        }
        return maybeCourseTeacher.get();
    }

    public CourseTeacher createCourseTeacher(Long courseId, Long teacherId) throws NotFoundException {
        Optional<Course> maybeCourse = courseRepository.findById(courseId);
        Optional<Teacher> maybeTeacher = teacherRepository.findById(teacherId);

        if (!maybeCourse.isPresent()) {
            throw new NotFoundException("Course with ID " + courseId + " Not Found");
        }

        if (!maybeTeacher.isPresent()) {
            throw new NotFoundException("Teacher with ID " + teacherId + " Not Found");
        }

        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse(maybeCourse.get());
        courseTeacher.setTeacher(maybeTeacher.get());

        CourseTeacher newCourseTeacher = courseTeacherRepository.save(courseTeacher);

        return newCourseTeacher;
    }

    public void deleteCourseTeacherById(Long id) throws NotFoundException {
        Optional<CourseTeacher> maybeCourseTeacher = courseTeacherRepository.findById(id);
        if (!maybeCourseTeacher.isPresent()) {
            throw new NotFoundException("Course Teacher with ID " + id + " Not Found");
        }
        courseTeacherRepository.deleteById(id);
    }
}
