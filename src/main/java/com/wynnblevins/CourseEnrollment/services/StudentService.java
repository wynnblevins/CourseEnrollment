package com.wynnblevins.CourseEnrollment.services;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Student;
import com.wynnblevins.CourseEnrollment.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student findStudentById(Long id) throws NotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new NotFoundException("Student with id " + id + " not found");
        }
        return student.get();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(long id, Student student) throws NotFoundException {
        Optional<Student> maybeStudent = studentRepository.findById(id);
        if (!maybeStudent.isPresent()) {
            throw new NotFoundException("Student with id " + id + " not found");
        }

        Student updatedStudent = maybeStudent.get();
        updatedStudent.setName(student.getName());

        return studentRepository.save(updatedStudent);
    }

    public void deleteStudent(long id) throws NotFoundException {
        Optional<Student> maybeStudent = studentRepository.findById(id);
        if (!maybeStudent.isPresent()) {
            throw new NotFoundException("Student with id " + id + " not found");
        }
        studentRepository.delete(maybeStudent.get());
    }
}
