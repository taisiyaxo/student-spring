package com.example.studentsspring.controller;

import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.request.subject_request.*;
import com.example.studentsspring.response.subject_response.*;
import com.example.studentsspring.service.service_interface.ISubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class SubjectController {
    private ISubjectService service;

    @PostMapping("/addSubject")
    public AddSubjectResponse add(@RequestBody AddSubjectRequest addSubjectRequest) throws NotFoundServiceException {
        return service.add(addSubjectRequest);
    }

    @DeleteMapping("/deleteSubject")
    public void delete(@RequestBody DeleteSubjectRequest deleteSubjectRequest) throws NotFoundServiceException {
            service.delete(deleteSubjectRequest);
    }

    @PutMapping("/editSubject")
    public void edit(@RequestBody EditSubjectRequest editSubjectRequest) throws NotFoundServiceException {
        service.edit(editSubjectRequest);
    }

    @GetMapping("/getSubject")
    public GetSubjectByIdResponse get(@RequestBody GetSubjectByIdRequest getSubjectByIdRequest) throws NotFoundServiceException {
        return service.getById(getSubjectByIdRequest);
    }

    @GetMapping("/getSubjects")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
