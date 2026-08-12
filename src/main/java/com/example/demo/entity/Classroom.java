package com.example.demo.entity;

import com.example.demo.dto.response.StudentResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name ="classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column( nullable = false)
    private String name;
    @OneToMany(mappedBy = "classroom")
    private List<Student> student = new ArrayList<>();
}
