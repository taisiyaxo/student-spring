package com.example.studentsspring.request.student_group_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class AddStudentGroupRequest {
    @NotNull
    @NotBlank
    @Length(min = 1, max = 100)
    private final  String name;
}
