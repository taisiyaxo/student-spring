package com.example.studentsspring.service.service_interface;

import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.request.student_group_request.AddStudentGroupRequest;
import com.example.studentsspring.request.student_group_request.DeleteStudentGroupRequest;
import com.example.studentsspring.request.student_group_request.EditStudentGroupRequest;
import com.example.studentsspring.request.student_group_request.GetStudentGroupByIdRequest;
import com.example.studentsspring.request.student_request.AddStudentRequest;
import com.example.studentsspring.request.student_request.EditStudentRequest;
import com.example.studentsspring.request.student_request.GetStudentByIdRequest;
import com.example.studentsspring.response.student_group_response.AddStudentGroupResponse;
import com.example.studentsspring.response.student_group_response.GetStudentGroupByIdResponse;

public interface IStudentGroupService {

    AddStudentGroupResponse add(AddStudentGroupRequest request);

    void delete(DeleteStudentGroupRequest request) throws ServiceException;

    void edit(EditStudentGroupRequest request) throws NotFoundServiceException;

    GetStudentGroupByIdResponse getById(GetStudentGroupByIdRequest request) throws NotFoundServiceException;

}
