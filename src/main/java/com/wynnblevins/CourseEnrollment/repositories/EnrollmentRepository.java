package com.wynnblevins.CourseEnrollment.repositories;

import com.wynnblevins.CourseEnrollment.models.Enrollment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    public Enrollment findByStudentIdAndCourseId(long studentId, long courseId);
}
