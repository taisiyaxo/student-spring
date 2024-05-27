package com.example.studentsspring.request.teacher_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class EditTeacherRequest {
    @NotNull
    private final Long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 100)
    private final String lastName;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 100)
    private final String firstName;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 100)
    private final String middleName;

}

