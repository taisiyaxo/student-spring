package com.example.studentsspring.controller;

import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.request.teacher_request.*;
import com.example.studentsspring.response.teacher_response.*;
import com.example.studentsspring.service.service_interface.ITeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class TeacherController {
    private ITeacherService teacherService;
    
    @PostMapping("/addTeacher")
    public AddTeacherResponse addTeacher(@RequestBody AddTeacherRequest request){
        return teacherService.add(request);
    }
    
    @DeleteMapping("/deleteTeacher")
    public void deleteTeacher(@RequestBody DeleteTeacherRequest request) throws NotFoundServiceException {
        teacherService.delete(request);
    }
    
    @PutMapping("/editTeacher")
    public void editTeacher(@RequestBody EditTeacherRequest request) throws ServiceException {
        teacherService.edit(request);
    }

    @GetMapping("/getTeacher")
    public GetTeacherByIdResponse getTeacher(@RequestBody GetTeacherByIdRequest request) throws NotFoundServiceException {
        return teacherService.getById(request);
    }
    
    @GetMapping("/getTeachers")
   public ResponseEntity<?> getTeachers(){
        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
    }
}
