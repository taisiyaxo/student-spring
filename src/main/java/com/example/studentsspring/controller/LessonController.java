package com.example.studentsspring.controller;

import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.request.lesson_request.*;
import com.example.studentsspring.response.lesson_response.AddLessonResponse;
import com.example.studentsspring.response.lesson_response.GetLessonByIdResponse;
import com.example.studentsspring.service.service_interface.ILessonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class LessonController {
    @Autowired
    private final ILessonService lessonService;

    @PostMapping("/addLesson")
    public AddLessonResponse addLesson(@Valid @RequestBody AddLessonRequest request) throws ServiceException {
        return lessonService.add(request);
    }

    @DeleteMapping("/deleteLesson")
    public void deleteLesson(@Valid @RequestBody DeleteLessonRequest request) throws NotFoundServiceException {
        lessonService.delete(request);
    }

    @PutMapping("/editLesson")
    public void editLesson(@Valid  @RequestBody EditLessonRequest request) throws ServiceException  {
        lessonService.edit(request);
    }

    @GetMapping("/getLessonById")
    public GetLessonByIdResponse getLessonById(@Valid  @RequestBody GetLessonByIdRequest request) throws NotFoundServiceException  {
        return lessonService.getById(request);
    }

    @GetMapping("/getLessonByGroup")
    public List<GetLessonByIdResponse> getByGroup(GetLessonsByGroupRequest request) throws ServiceException {
        return lessonService.getByGroup(request);
    }

    @GetMapping("/getLessonsByTeacher")
    public List<GetLessonByIdResponse> getByTeacher(GetLessonsByTeacherRequest request) throws ServiceException {
        return lessonService.getByTeacher(request);
    }

}
