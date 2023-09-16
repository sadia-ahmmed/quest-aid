package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.services.interfaces.IStudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private IStudentService studentService;

    @GetMapping("/public/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("/by/{email}/email")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        return new ResponseEntity<>(studentService.getStudentByEmail(email), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }


}
