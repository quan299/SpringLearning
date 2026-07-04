package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentCreationRequest {
    @NotBlank
    private String name;

    @Min(18)
    private Integer age;

    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentCreationRequest(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    public StudentCreationRequest() {
    }

}
