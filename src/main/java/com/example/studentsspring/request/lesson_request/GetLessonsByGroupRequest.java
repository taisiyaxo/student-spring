package com.example.studentsspring.request.lesson_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class GetLessonsByGroupRequest {
    @NotNull
    @NotBlank
    @Length(min=1,max=100)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private final String startDate;
    @NotNull
    @NotBlank
    @Length(min=1,max=100)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private final String endDate;
    @NotNull
    private final Long groupId;
}
