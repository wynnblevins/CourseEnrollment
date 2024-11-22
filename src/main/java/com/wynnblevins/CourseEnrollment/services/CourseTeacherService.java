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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public List<CourseTeacher> getAllCourseTeachersByTeacherId(Long teacherId) {
        return courseTeacherRepository.findCourseTeacherByTeacherId(teacherId);
    }
    
    public void deleteAllByTeacherId(Long teacherId) {
    	courseTeacherRepository.deleteAllByTeacherId(teacherId);
    }

    public CourseTeacher getCourseTeacherById(Long id) throws NotFoundException {
        Optional<CourseTeacher> maybeCourseTeacher = courseTeacherRepository.findById(id);
        if (!maybeCourseTeacher.isPresent()) {
            throw new NotFoundException("Course Teacher Not Found");
        }
        return maybeCourseTeacher.get();
    }

    public CourseTeacher createCourseTeacher(CourseTeacher courseTeacher) throws NotFoundException {
        Long courseId = courseTeacher.getCourse().getId();
        Long teacherId = courseTeacher.getTeacher().getId();
    	Optional<Course> maybeCourse = courseRepository.findById(courseId);
        Optional<Teacher> maybeTeacher = teacherRepository.findById(teacherId);

        if (!maybeCourse.isPresent()) {
            throw new NotFoundException("Course with ID " + courseId + " fot found");
        }

        if (!maybeTeacher.isPresent()) {
            throw new NotFoundException("Teacher with ID " + teacherId + " not found");
        }

        CourseTeacher newCourseTeacher = courseTeacherRepository.save(courseTeacher);

        return newCourseTeacher;
    }

    public void deleteCourseTeacherById(Long id) throws NotFoundException {
        Optional<CourseTeacher> maybeCourseTeacher = courseTeacherRepository.findById(id);
        
        if (!maybeCourseTeacher.isPresent()) {
        	String idStr = Long.toString(id);
        	String notFoundMsg = "Course Teacher with ID ".concat(idStr).concat(" not found");
            throw new NotFoundException(notFoundMsg);
        }
        
        courseTeacherRepository.deleteById(id);
    }
    
    public void deleteAllByCourseId(Long id) throws NotFoundException {
    	Optional<Course> maybeCourse = courseRepository.findById(id);
    	
    	if (!maybeCourse.isPresent()) {
    		String courseIdStr = Long.toString(id);
    		String notFoundMsg = "Course with ID ".concat(courseIdStr).concat(" not found");
    		throw new NotFoundException(notFoundMsg);
    	}
    	
    	courseTeacherRepository.deleteAllByCourseId(id);
    }
}
