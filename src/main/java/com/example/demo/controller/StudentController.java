package com.example.demo.controller;

import com.example.demo.Mapper.PageMapper;
import com.example.demo.dto.request.StudentCreationRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final PageMapper pageMapper;

    @GetMapping
    public ApiResponse<PageResponse<StudentResponse>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Page<StudentResponse> studentPage =
                studentService.getAllStudents(
                        page,
                        size,
                        sortBy,
                        direction
                );

        PageResponse<StudentResponse> pageResponse =
                pageMapper.toPageResponse(studentPage);

        return new ApiResponse<>(
                1000,
                "Success",
                pageResponse
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

    @GetMapping("/search")
    public ApiResponse<PageResponse<StudentResponse>> searchStudents(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
       Page<StudentResponse> studentPage = studentService.searchStudents(  keyword,
               page,
               size,
               sortBy,
               direction);
        PageResponse<StudentResponse> pageResponse =
                pageMapper.toPageResponse(studentPage);
        return new ApiResponse<>(
                1000,
                "Success",
              pageResponse
        );
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


}
