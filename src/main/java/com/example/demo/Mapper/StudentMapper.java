package com.example.demo.Mapper;

import com.example.demo.dto.request.StudentCreationRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(StudentCreationRequest request);
    StudentResponse toStudentResponse(Student student);

    void updateStudent(@MappingTarget Student student, StudentUpdateRequest request);
}
