package com.example.studentsspring.response.lesson_response;

import com.example.studentsspring.entity.Lesson;
import com.example.studentsspring.entity.LessonAttendance;
import com.example.studentsspring.response.student_group_response.GetStudentGroupByIdResponse;
import com.example.studentsspring.response.student_response.GetStudentByIdResponse;
import com.example.studentsspring.response.subject_response.GetSubjectByIdResponse;
import com.example.studentsspring.response.teacher_response.GetTeacherByIdResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetLessonByIdResponse {
    private final Long id;
    private final GetSubjectByIdResponse subject;
    private final String date;
    private final int number;
    private final GetTeacherByIdResponse teacher;
    private final GetStudentGroupByIdResponse group;
    private List<GetStudentByIdResponse> students = new ArrayList<>();

    public GetLessonByIdResponse(Long id, GetSubjectByIdResponse subject, String date, int number, GetTeacherByIdResponse teacher, GetStudentGroupByIdResponse group, List<GetStudentByIdResponse> list) {
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.number = number;
        this.teacher = teacher;
        this.group = group;
        if (list != null) {
            students = null;
        } else {
            students.addAll(list);
        }
    }

    public GetLessonByIdResponse(Lesson lesson, LessonAttendance attendance) {
        this(lesson.getId(), new GetSubjectByIdResponse(lesson.getSubject()),
                lesson.getDate(), lesson.getNumber(),
                new GetTeacherByIdResponse(lesson.getTeacher()),
                new GetStudentGroupByIdResponse(lesson.getGroup()),
                attendance == null ? null : attendance.getStudents().stream().map(GetStudentByIdResponse::new).toList());
    }
}
