package com.example.studentsspring.service.service_realization;

import com.example.studentsspring.entity.Student;
import com.example.studentsspring.entity.StudentGroup;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.repository.IStudentGroupRepository;
import com.example.studentsspring.repository.IStudentRepository;
import com.example.studentsspring.request.student_request.*;
import com.example.studentsspring.response.student_response.AddStudentResponse;
import com.example.studentsspring.response.student_response.GetStudentByIdResponse;
import com.example.studentsspring.service.service_interface.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;
    private final IStudentGroupRepository studentGroupRepository;

    @Override
    public AddStudentResponse add(AddStudentRequest request) throws NotFoundServiceException {
        //надо ли проверять что группа уже сущ-ет?
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setMiddleName(request.getMiddleName());
        student.setGroup(studentGroupRepository.getReferenceById(request.getGroupId()));
        student.setStatus(request.getStatus());
        return new AddStudentResponse(studentRepository.save(student));

    }

    @Override
    public void delete(DeleteStudentRequest request) throws NotFoundServiceException {
        studentRepository.deleteById(request.getId());
    }

    @Override
    public void edit(EditStudentRequest request) throws NotFoundServiceException {
        Student student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("Student not found"));
        StudentGroup studentGroup = studentGroupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new NotFoundServiceException("Group not found"));
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setMiddleName(request.getMiddleName());
        student.setGroup(studentGroupRepository.getReferenceById(request.getGroupId()));
        student.setStatus(request.getStatus());
        studentRepository.save(student); // or update?
    }

    @Override
    public GetStudentByIdResponse getById(GetStudentByIdRequest request) throws NotFoundServiceException {
        Student student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("Student not found"));
        return new GetStudentByIdResponse(student);
    }

    @Override
    public List<GetStudentByIdResponse> getByGroupId(GetStudentsByGroupIdRequest request) throws NotFoundServiceException {
        StudentGroup studentGroup = studentGroupRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("Group not found"));
        List<Student> students = studentRepository.findAllByGroupId(request.getId());
        if (students.isEmpty()) {
            throw new NotFoundServiceException("No students found for the given group ID");
        }
        return students.stream().map(GetStudentByIdResponse::new).collect(Collectors.toList());
    }

}
