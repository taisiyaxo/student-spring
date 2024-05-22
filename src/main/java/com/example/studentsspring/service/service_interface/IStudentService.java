package com.example.studentsspring.service.service_interface;

import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.request.student_request.*;
import com.example.studentsspring.response.student_response.AddStudentResponse;
import com.example.studentsspring.response.student_response.GetStudentByIdResponse;

import java.util.List;


public interface IStudentService {
    AddStudentResponse add(AddStudentRequest request) throws NotFoundServiceException;

    void delete(DeleteStudentRequest request) throws NotFoundServiceException;

    void edit(EditStudentRequest request) throws NotFoundServiceException;

    GetStudentByIdResponse getById(GetStudentByIdRequest request) throws NotFoundServiceException;

    List<GetStudentByIdResponse> getByGroupId(GetStudentsByGroupIdRequest request) throws NotFoundServiceException;
}
