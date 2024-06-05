package com.example.studentsspring.request.student_group_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteStudentGroupRequest {
    @NotNull
    private  Long id;
}
