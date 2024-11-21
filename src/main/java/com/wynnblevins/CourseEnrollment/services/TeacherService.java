package com.wynnblevins.CourseEnrollment.services;

import com.wynnblevins.CourseEnrollment.exceptions.NotFoundException;
import com.wynnblevins.CourseEnrollment.models.Teacher;
import com.wynnblevins.CourseEnrollment.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher getTeacherById(Long teacherId) throws NotFoundException {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (!teacher.isPresent()) {
            throw new NotFoundException("Teacher with id " + teacherId + " not found");
        }
        return teacher.get();
    }

    public List<Teacher> getAllTeachers() {
        return (List<Teacher>) teacherRepository.findAll();
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long teacherId, Teacher teacher) throws NotFoundException {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (!teacherOptional.isPresent()) {
            throw new NotFoundException("teacher with provided ID not found");
        }

        Teacher foundTeacher = teacherOptional.get();
        foundTeacher.setName(teacher.getName());

        teacherRepository.save(foundTeacher);
        return foundTeacher;
    }

    public Teacher deleteTeacher(Long teacherId) throws NotFoundException {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (!teacherOptional.isPresent()) {
            throw new NotFoundException("teacher with provided ID not found");
        }

        Teacher foundTeacher = teacherOptional.get();
        teacherRepository.delete(foundTeacher);

        return foundTeacher;
    }
}
