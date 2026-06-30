package com.example.demo.service;

import com.example.demo.dto.request.StudentCreationRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student getStudentById(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with this id " + id);
        }

        return optionalStudent.get();
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
    public Student findStudentByName(String name){
        Student student = studentRepository.findStudentByName(name);
        if(student == null){
            throw new RuntimeException("student not found");
        }
        else return student;
    }
    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);

    }
    public Student updateStudentById(Long id, Student student){
         Student student2 = studentRepository.getById(id);
        student2.setName(student.getName());
        student2.setAge(student.getAge());
        student2.setEmail(student.getEmail());
        return studentRepository.save(student2);
    }
}
