package com.example.studentsspring.response.subject_response;

import com.example.studentsspring.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSubjectByIdResponse {
    private final Long id;
    private final String name;

    // to do: ?
    public GetSubjectByIdResponse(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
    }
}
