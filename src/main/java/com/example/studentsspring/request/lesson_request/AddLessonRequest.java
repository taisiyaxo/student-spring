package com.example.studentsspring.request.lesson_request;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddLessonRequest {
    @NotNull
    private final Long subjectId;
    @NotNull
    @NotBlank
    @Length(min=1,max=100)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private final String date;
    @NotNull
    @PositiveOrZero
    private final int number;
    @NotNull
    private final Long teacherId;
    @NotNull
    private final Long groupId;
    private final List<String> attendanceList;

    @JsonCreator
    public AddLessonRequest(Long subjectId, String date, int number, Long teacherId, Long groupId, List<String> attendanceList) {
        this.subjectId = subjectId;
        this.date = date;
        this.number = number;
        this.teacherId = teacherId;
        this.groupId = groupId;
        if(attendanceList==null){
            this.attendanceList = null;
        }
        else {
            this.attendanceList = new ArrayList<>(attendanceList.size());
            this.attendanceList.addAll(attendanceList);
        }
    }

}
