package com.example.studentsspring.controller;

import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.request.student_request.*;
import com.example.studentsspring.response.student_response.AddStudentResponse;
import com.example.studentsspring.response.student_response.GetStudentByIdResponse;
import com.example.studentsspring.service.service_interface.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class StudentController {
    private IStudentService service;

    @PostMapping("/addStudent")
    public AddStudentResponse addStudent(@RequestBody AddStudentRequest addStudentRequest) throws NotFoundServiceException {
        return service.add(addStudentRequest);
    }

    @DeleteMapping("/deleteStudent")
    public void deleteStudent(@RequestBody DeleteStudentRequest deleteStudentRequest) throws NotFoundServiceException{
        service.delete(deleteStudentRequest);
    }


    @PutMapping("/editStudent")
    public void editStudent(@RequestBody EditStudentRequest editStudentRequest) throws NotFoundServiceException {
            service.edit(editStudentRequest);
    }

    @GetMapping("/getStudentById")
    public GetStudentByIdResponse getStudentById( @RequestBody GetStudentByIdRequest getStudentByIdRequest) throws NotFoundServiceException{
        return service.getById(getStudentByIdRequest);
    }

    @GetMapping("getStudentsByGroup")
    public List<GetStudentByIdResponse> getStudentsGroupById( @RequestBody GetStudentsByGroupIdRequest request) throws NotFoundServiceException{
        return service.getByGroupId(request);
    }
}
