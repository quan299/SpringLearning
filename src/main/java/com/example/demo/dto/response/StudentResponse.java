package com.example.demo.dto.response;

import jdk.jshell.Snippet;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class StudentResponse {
    Long id;
    String name;
    Integer age;
   String email;


}