package com.example.studentsspring.request.subject_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class AddSubjectRequest {
    @NotNull
    @NotBlank
    @Length(min = 1, max = 100)
    private final String name;
}
