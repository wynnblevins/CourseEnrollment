package com.wynnblevins.CourseEnrollment.repositories;

import com.wynnblevins.CourseEnrollment.models.CourseTeacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTeacherRepository extends CrudRepository<CourseTeacher, Long> {
    List<CourseTeacher> findCourseTeacherByTeacherId(Long teacherId);
    void deleteAllByTeacherId(Long teacherId);
}
