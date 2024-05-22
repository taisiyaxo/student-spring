package com.example.studentsspring.request.student_request;

import com.example.studentsspring.annotation.IsEnum;
import com.example.studentsspring.entity.StudentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class EditStudentRequest {
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
    @IsEnum(enumClass = StudentStatus.class)
    private final String status;
    @NotNull
    private final Long groupId;
}

