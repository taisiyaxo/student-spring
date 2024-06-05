package com.example.studentsspring.service.service_realization;

import com.example.studentsspring.entity.*;
import com.example.studentsspring.exception.service_exception.*;
import com.example.studentsspring.repository.*;
import com.example.studentsspring.request.lesson_request.*;
import com.example.studentsspring.response.lesson_response.AddLessonResponse;
import com.example.studentsspring.response.lesson_response.GetLessonByIdResponse;
import com.example.studentsspring.service.service_interface.ILessonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LessonService implements ILessonService {
    @Autowired
    private ILessonRepository lessonRepository;
    @Autowired
    private ILessonAttendanceRepository attendanceRepository;
    @Autowired
    private IStudentGroupRepository studentGroupRepository;
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private ITeacherRepository teacherRepository;
    @Autowired
    private ISubjectRepository subjectRepository;

    @Override
    @Transactional
    public AddLessonResponse add(AddLessonRequest request) throws ServiceException {
            // Проверка на существование группы, учителя и предмета
            studentGroupRepository.findById(request.getGroupId())
                    .orElseThrow(() -> new NotFoundServiceException("Invalid group id"));
            teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() -> new NotFoundServiceException("Invalid teacher id"));
            subjectRepository.findById(request.getSubjectId())
                    .orElseThrow(() -> new NotFoundServiceException("Invalid subject id"));

            // Проверка списка посещаемости, если он не пуст
            List<Student> students = new ArrayList<>();
            if (request.getAttendanceList() != null) {
                List<String> attendanceList = request.getAttendanceList();
                for (String id : attendanceList) {
                    Student student = studentRepository.findById(Long.valueOf(id))
                            .orElseThrow(() -> new ServiceException("Invalid student id"));
                    Long groupId = student.getGroup().getId();
                    if (!groupId.equals(request.getGroupId())) {
                        throw new ServiceException("All students from attendance list should be from group with id " + request.getGroupId());
                    }
                    students.add(student);
                }
            }

            // Создание и сохранение урока
            Lesson lesson = new Lesson(
                    null,
                    subjectRepository.findById(request.getSubjectId()).orElse(null),
                    request.getDate(),
                    request.getNumber(),
                    teacherRepository.findById(request.getTeacherId()).orElse(null),
                    studentGroupRepository.findById(request.getGroupId()).orElse(null)
            );
            Lesson savedLesson = lessonRepository.save(lesson);

            // Создание и сохранение посещаемости, если список не пуст
            if (!students.isEmpty()) {
                LessonAttendance lessonAttendance = new LessonAttendance(
                        null,
                        savedLesson,
                        students
                );
                attendanceRepository.save(lessonAttendance);
            }

            return new AddLessonResponse(savedLesson);
    }



    @Override
    @Transactional
    public void delete(DeleteLessonRequest request) throws NotFoundServiceException {
        lessonRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid lesson id"));
        attendanceRepository.deleteAttendanceByLessonId(request.getId());
        lessonRepository.deleteById(request.getId());
    }

    @Override
    @Transactional
    public void edit(EditLessonRequest request) throws ServiceException {
        Lesson lesson = lessonRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid lesson id"));
        studentGroupRepository.findById(request.getGroupId()).orElseThrow(() -> new NotFoundServiceException("invalid group id"));
        teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        subjectRepository.findById(request.getSubjectId()).orElseThrow(() -> new NotFoundServiceException("invalid subject id"));
        if (request.getAttendanceList() != null) {
            List<String> attendanceList = request.getAttendanceList();
            for (var id : attendanceList) {
                Student student = studentRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ServiceException("invalid student id"));
                Long groupId = student.getGroup().getId();
                if (!groupId.equals(request.getGroupId())) {
                    throw new ServiceException("all students from attendance list should be from group with id " + request.getGroupId());
                }
            }
        }
        lessonRepository.save(lesson);
        lesson = lessonRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid lesson id"));
        if (attendanceRepository.findAttendanceByLessonId(request.getId()) != null) {
            attendanceRepository.deleteAttendanceByLessonId(request.getId());
        }
        if (request.getAttendanceList() != null) {
            LessonAttendance lessonAttendance = new LessonAttendance(
                    null,
                    lesson,
                    request.getAttendanceList().isEmpty()
                            ? new ArrayList<>()
                            : request.getAttendanceList().stream()
                            .map(o -> new Student())
                            .toList()
            );
            attendanceRepository.save(lessonAttendance);
        }
    }

    @Override
    @Transactional
    public GetLessonByIdResponse getById(GetLessonByIdRequest request) throws NotFoundServiceException {
        return new GetLessonByIdResponse(
                lessonRepository
                        .findById(request.getId())
                        .orElseThrow(() -> new NotFoundServiceException("invalid lesson id")),
                attendanceRepository.findAttendanceByLessonId(request.getId()));
    }

    @Override
    @Transactional
    public List<GetLessonByIdResponse> getByGroup(GetLessonsByGroupRequest request) throws ServiceException {
        studentGroupRepository.findById(request.getGroupId()).orElseThrow(() -> new ServiceException("invalid group id"));
        return lessonRepository.findLessonByGroupId(request.getGroupId(), request.getStartDate(), request.getEndDate()).stream()
                .map(o -> new GetLessonByIdResponse(o, attendanceRepository.findAttendanceByLessonId(o.getId())))
                .toList();
    }

    @Override
    @Transactional
    public List<GetLessonByIdResponse> getByTeacher(GetLessonsByTeacherRequest request) throws ServiceException {
        teacherRepository.findById(request.getTeacherId()).orElseThrow(() -> new ServiceException("invalid teacher id"));
        return lessonRepository.findLessonByTeacherId(request.getTeacherId(), request.getStartDate(), request.getEndDate()).stream()
                .map(o -> new GetLessonByIdResponse(o, attendanceRepository.findAttendanceByLessonId(o.getId())))
                .toList();
    }
}
