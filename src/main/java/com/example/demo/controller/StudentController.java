package com.example.demo.controller;

import com.example.demo.dto.request.StudentCreationRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentResponse createStudent(@RequestBody StudentCreationRequest request) {
        return studentService.createStudent(request);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudentById(
            @PathVariable Long id,
            @RequestBody StudentUpdateRequest request
    ) {
        return studentService.updateStudentById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }
}
