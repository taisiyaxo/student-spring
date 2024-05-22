package com.example.studentsspring.controller;

import com.example.studentsspring.entity.Student;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.request.student_request.*;
import com.example.studentsspring.response.student_response.AddStudentResponse;
import com.example.studentsspring.response.student_response.GetStudentByIdResponse;
import com.example.studentsspring.service.service_interface.IStudentService;
import com.example.studentsspring.service.service_realization.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/student")
@Validated
public class StudentController {
    private IStudentService service;

    @PostMapping
    public AddStudentResponse addStudent(@RequestBody AddStudentRequest addStudentRequest) throws NotFoundServiceException {
        return service.add(addStudentRequest);
    }

    @DeleteMapping
    public void deleteStudent(@RequestBody DeleteStudentRequest deleteStudentRequest) throws NotFoundServiceException{
        service.delete(deleteStudentRequest);
    }


    @PutMapping("/{id}")
    public void editStudent(@PathVariable Long id, @RequestBody EditStudentRequest editStudentRequest) throws NotFoundServiceException {
            service.edit(editStudentRequest);
    }

    @GetMapping("/{id}")
    public GetStudentByIdResponse getStudentById(@PathVariable Long id,@RequestBody GetStudentByIdRequest getStudentByIdRequest) throws NotFoundServiceException{
        return service.getById(getStudentByIdRequest);
    }

    @GetMapping("/group/{groupId}")
    public List<GetStudentByIdResponse> getStudentsGroupById(@PathVariable Long groupId, @RequestBody GetStudentsByGroupIdRequest request) throws NotFoundServiceException{
        return service.getByGroupId(request);
    }
}
