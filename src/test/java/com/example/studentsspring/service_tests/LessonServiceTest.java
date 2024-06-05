package com.example.studentsspring.service_tests;

import com.example.studentsspring.entity.*;
import com.example.studentsspring.exception.service_exception.*;
import com.example.studentsspring.repository.*;
import com.example.studentsspring.request.lesson_request.*;
import com.example.studentsspring.response.lesson_response.*;
import com.example.studentsspring.service.service_realization.LessonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

    @Mock
    private ILessonRepository lessonRepository;

    @Mock
    private ILessonAttendanceRepository attendanceRepository;

    @Mock
    private IStudentGroupRepository studentGroupRepository;

    @Mock
    private IStudentRepository studentRepository;

    @Mock
    private ITeacherRepository teacherRepository;

    @Mock
    private ISubjectRepository subjectRepository;

    @InjectMocks
    private LessonService lessonService;

    @Test
    void testAddLesson() throws ServiceException {
        AddLessonRequest request = new AddLessonRequest(1L,  "2024-06-05", 1, 1L,  1L, List.of("1"));
        StudentGroup group = new StudentGroup();
        group.setId(1L);
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        Subject subject = new Subject();
        subject.setId(1L);
        Student student = new Student();
        student.setId(1L);
        student.setGroup(group);

        when(studentGroupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacher));
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        Lesson lesson = new Lesson(1L, subject, "2024-06-05", 1, teacher, group);
        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);

        AddLessonResponse response = lessonService.add(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testDeleteLesson() throws NotFoundServiceException {
        Lesson lesson = new Lesson(1L, new Subject(), "2024-06-05", 1, new Teacher(), new StudentGroup());

        when(lessonRepository.findById(anyLong())).thenReturn(Optional.of(lesson));

        DeleteLessonRequest request = new DeleteLessonRequest(1L);
        lessonService.delete(request);

        verify(lessonRepository, times(1)).deleteById(request.getId());
        verify(attendanceRepository, times(1)).deleteAttendanceByLessonId(request.getId());
    }

    @Test
    void testEditLesson() throws ServiceException {
        EditLessonRequest request = new EditLessonRequest(1L, 1L, "2024-06-05", 1, 1L,  1L, List.of("1"));
        Lesson lesson = new Lesson(1L, new Subject(), "2024-06-05", 1, new Teacher(), new StudentGroup());
        StudentGroup group = new StudentGroup();
        group.setId(1L);
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        Subject subject = new Subject();
        subject.setId(1L);
        Student student = new Student();
        student.setId(1L);
        student.setGroup(group);

        when(lessonRepository.findById(anyLong())).thenReturn(Optional.of(lesson));
        when(studentGroupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacher));
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        lessonService.edit(request);

        verify(lessonRepository, times(1)).save(lesson);
    }

    @Test
    void testGetLessonById() throws NotFoundServiceException {
        GetLessonByIdRequest request = new GetLessonByIdRequest(1L);
        Lesson lesson = new Lesson(1L, new Subject(), "2024-06-05", 1, new Teacher(), new StudentGroup());
        LessonAttendance attendance = new LessonAttendance(1L, lesson, Collections.emptyList());

        when(lessonRepository.findById(anyLong())).thenReturn(Optional.of(lesson));
        when(attendanceRepository.findAttendanceByLessonId(anyLong())).thenReturn(attendance);

        GetLessonByIdResponse response = lessonService.getById(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }
}
