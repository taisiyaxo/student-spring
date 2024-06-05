package com.example.studentsspring.service.service_interface;

import com.example.studentsspring.exception.service_exception.*;
import com.example.studentsspring.request.lesson_request.*;
import com.example.studentsspring.response.lesson_response.*;

import java.util.List;

public interface ILessonService {
    AddLessonResponse add(AddLessonRequest request) throws ServiceException;

    void delete(DeleteLessonRequest request) throws NotFoundServiceException;

    void edit(EditLessonRequest request) throws ServiceException;

    GetLessonByIdResponse getById(GetLessonByIdRequest request) throws NotFoundServiceException;

    List<GetLessonByIdResponse> getByGroup(GetLessonsByGroupRequest request) throws ServiceException;

    List<GetLessonByIdResponse> getByTeacher(GetLessonsByTeacherRequest request) throws ServiceException;

}
