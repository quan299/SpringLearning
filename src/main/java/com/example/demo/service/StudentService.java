package com.example.demo.service;

import com.example.demo.dto.request.StudentCreationRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.StudentRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private StudentResponse toStudentResponse(Student student) {

        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .email(student.getEmail())
                .build();
    }
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentResponse> responses = new ArrayList<>();

        for (Student student : students) {
            StudentResponse response = toStudentResponse(student);
            responses.add(response);
        }

        return responses;
    }
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        return toStudentResponse(student);
    }

    public StudentResponse createStudent(StudentCreationRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .build();

        Student savedStudent =
                studentRepository.save(student);

        return toStudentResponse(savedStudent);
    }
    public StudentResponse findStudentByName(String name) {
        Student student = studentRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        return toStudentResponse(student);
    }
    public StudentResponse updateStudentById(Long id, StudentUpdateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());

        Student updatedStudent = studentRepository.save(student);

        return toStudentResponse(updatedStudent);


    }

    public void deleteStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STUDENT_NOT_FOUND));

        studentRepository.delete(student);
    }
}
