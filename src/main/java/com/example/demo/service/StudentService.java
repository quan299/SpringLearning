package com.example.demo.service;

import com.example.demo.dto.request.StudentCreationRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private StudentResponse toStudentResponse(Student student){
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getEmail()
        );
    }
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        return toStudentResponse(student);
    }

    public StudentResponse createStudent(StudentCreationRequest request) {
        Student student = new Student();

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());

        studentRepository.save(student);
        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setAge(student.getAge());
        response.setEmail(student.getEmail());

        return response;
    }
    public StudentResponse findStudentByName(String name) {
        Student student = studentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Student not found with name: " + name));

        return toStudentResponse(student);
    }
    public StudentResponse updateStudentById(Long id, StudentUpdateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());

        Student updatedStudent = studentRepository.save(student);

        return toStudentResponse(updatedStudent);
    }

    public void deleteStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        studentRepository.delete(student);
    }
}
