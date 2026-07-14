package com.example.demo.controller;

import com.example.demo.dto.request.StudentCreationRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;



    @GetMapping
    public ApiResponse<List<StudentResponse>> getAllStudents() {
        return new ApiResponse<>(
                1000,
                "Success",
                studentService.getAllStudents()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(
            @Valid @RequestBody StudentCreationRequest request
    ) {
        StudentResponse student = studentService.createStudent(request);

        ApiResponse<StudentResponse> response = new ApiResponse<>(
                1000,
                "Success",
                student
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<StudentResponse> getStudentById(@PathVariable Long id) {
        return new ApiResponse<>(
                1000,
                "Success",
                studentService.getStudentById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<StudentResponse> updateStudentById(
            @PathVariable Long id,
            @Valid @RequestBody StudentUpdateRequest request
    ) {
        return new ApiResponse<>(
                1000,
                "Success",
                studentService.updateStudentById(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);

        ApiResponse<Void> response = new ApiResponse<>(
                1000,
                "Success",
                null
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/search")
    public ApiResponse<StudentResponse> findStudentByName(@RequestParam String name) {
        return new ApiResponse<>(
                1000,
                "Success",
                studentService.findStudentByName(name)
        );
    }
}
