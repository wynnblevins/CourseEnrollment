package com.wynnblevins.CourseEnrollment.repositories;

import com.wynnblevins.CourseEnrollment.models.Enrollment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    Enrollment findByStudentIdAndCourseId(long studentId, long courseId);
    void deleteAllByStudentId(Long studentId);
    void deleteAllByCourseId(Long courseId);
    List<Enrollment> findByStudentId(long studentId);
    List<Enrollment> findByCourseId(long courseId);
}
