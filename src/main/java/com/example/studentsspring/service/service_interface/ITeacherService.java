package com.example.studentsspring.service.service_interface;

import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.request.teacher_request.*;
import com.example.studentsspring.response.teacher_response.*;

import java.util.List;

public interface ITeacherService {
    AddTeacherResponse add(AddTeacherRequest request);

    void delete(DeleteTeacherRequest request) throws NotFoundServiceException;

    void edit(EditTeacherRequest request) throws ServiceException;

    GetTeacherByIdResponse getById(GetTeacherByIdRequest request) throws NotFoundServiceException;

    List<GetTeacherByIdResponse> getAll();
}
