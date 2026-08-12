package com.example.demo.service;

import com.example.demo.Mapper.StudentMapper;
import com.example.demo.dto.request.StudentCreationRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.StudentRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper StudentMapper;

    private StudentResponse toStudentResponse(Student student) {
        StudentResponse studentResponse = StudentMapper.toStudentResponse(student);
        return studentResponse;
    }
    public Page<StudentResponse> getAllStudents(int page, int size,String sortBy,String direction) {
        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(page, size,sort);

        Page<Student> studentPage =
                studentRepository.findAll(pageable);

        return studentPage
                .map(StudentMapper::toStudentResponse);
    }
    public Page<StudentResponse> searchStudents(
            String keyword,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return studentRepository
                .findByNameContainingIgnoreCase(
                        keyword,
                        pageable
                )
                .map(StudentMapper::toStudentResponse);
    }
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        return StudentMapper.toStudentResponse(student);
    }

    public StudentResponse createStudent(StudentCreationRequest request) {
        Student student = StudentMapper.toStudent(request);

        Student savedStudent =
                studentRepository.save(student);

        return StudentMapper.toStudentResponse(savedStudent);
    }
    public StudentResponse findStudentByName(String name) {
        Student student = studentRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        return toStudentResponse(student);
    }
    public StudentResponse updateStudentById(Long id, StudentUpdateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

       StudentMapper.updateStudent(student, request);

        Student updatedStudent = studentRepository.save(student);

        return toStudentResponse(updatedStudent);


    }

    public void deleteStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        studentRepository.delete(student);
    }
}
