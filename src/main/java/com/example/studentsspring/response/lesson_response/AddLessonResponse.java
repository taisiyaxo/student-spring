package com.example.studentsspring.response.lesson_response;

import com.example.studentsspring.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddLessonResponse {
    private final Long id;

    public AddLessonResponse(Lesson lesson){
        this(lesson.getId());
    }
}

