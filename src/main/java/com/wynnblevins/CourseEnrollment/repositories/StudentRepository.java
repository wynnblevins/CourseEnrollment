package com.wynnblevins.CourseEnrollment.repositories;

import com.wynnblevins.CourseEnrollment.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
