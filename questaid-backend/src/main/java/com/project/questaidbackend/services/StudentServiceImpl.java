package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.repository.StudentRepository;
import com.project.questaidbackend.services.interfaces.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private StudentRepository studentRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Student createStudent(Student student) {
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student, id);
    }

    @Override
    public Student getStudentByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        return unwrapStudent(student, 404L);
    }

    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Student.class);
    }
}
