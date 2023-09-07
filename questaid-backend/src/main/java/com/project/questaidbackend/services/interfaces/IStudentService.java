package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Student;

public interface IStudentService {
    Student createStudent(Student student);
    Student getStudentById(Long id);
    Student getStudentByEmail(String email);
}
