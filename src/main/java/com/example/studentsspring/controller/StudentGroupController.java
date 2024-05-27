package com.example.studentsspring.controller;


import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.request.student_group_request.*;
import com.example.studentsspring.response.student_group_response.*;
import com.example.studentsspring.service.service_interface.IStudentGroupService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class StudentGroupController {
    private final IStudentGroupService service;

    @PostMapping("/addStudentGroup")
    public AddStudentGroupResponse addStudentGroup(@RequestBody AddStudentGroupRequest request) throws ServiceException {
        try {
            return service.add(request);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("This group already exists", e);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while adding the group", e);
        }
    }

    @DeleteMapping("/deleteStudentGroup")
    public void deleteStudentGroup(@RequestBody DeleteStudentGroupRequest request) throws ServiceException {
        service.delete(request);
    }

    @GetMapping("/getStudentGroupById")
    public GetStudentGroupByIdResponse getStudentGroupById(@RequestBody GetStudentGroupByIdRequest request) throws NotFoundServiceException {
        return service.getById(request);
    }

    @PutMapping("/editStudentGroup")
    public void editStudentGroup(@RequestBody EditStudentGroupRequest request) throws NotFoundServiceException {
        service.edit(request);
    }
}
