package com.example.studentsspring.service.service_interface;

import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.request.subject_request.*;
import com.example.studentsspring.response.subject_response.*;

import java.util.List;

public interface ISubjectService {
    AddSubjectResponse add(AddSubjectRequest addSubjectRequest) throws NotFoundServiceException;

    void delete(DeleteSubjectRequest deleteSubjectRequest) throws NotFoundServiceException;

    void edit(EditSubjectRequest editSubjectRequest) throws NotFoundServiceException;

    GetSubjectByIdResponse getById(GetSubjectByIdRequest getSubjectRequest) throws NotFoundServiceException;

    List<GetSubjectByIdResponse> getAll();
}
