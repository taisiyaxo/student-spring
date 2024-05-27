package com.example.studentsspring.service.service_realization;

import com.example.studentsspring.entity.Teacher;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.repository.ITeacherRepository;
import com.example.studentsspring.request.teacher_request.AddTeacherRequest;
import com.example.studentsspring.request.teacher_request.DeleteTeacherRequest;
import com.example.studentsspring.request.teacher_request.EditTeacherRequest;
import com.example.studentsspring.request.teacher_request.GetTeacherByIdRequest;
import com.example.studentsspring.response.teacher_response.AddTeacherResponse;
import com.example.studentsspring.response.teacher_response.GetTeacherByIdResponse;
import com.example.studentsspring.service.service_interface.ITeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;


    @Override
    public AddTeacherResponse add(AddTeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setLastName(request.getLastName());
        teacher.setFirstName(request.getFirstName());
        teacher.setMiddleName(request.getMiddleName());
        var savedTeacher = teacherRepository.save(teacher);
        return new AddTeacherResponse(savedTeacher.getId());
    }

    @Override
    public void delete(DeleteTeacherRequest request) throws NotFoundServiceException {
        teacherRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        teacherRepository.deleteById(request.getId());
    }

    @Override
    public void edit(EditTeacherRequest request) throws ServiceException {
        Teacher teacher = teacherRepository.findById(request.getId()).orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        teacher.setLastName(request.getLastName());
        teacher.setFirstName(request.getFirstName());
        teacher.setMiddleName(request.getMiddleName());
        teacherRepository.save(teacher);
    }

    @Override
    public GetTeacherByIdResponse getById(GetTeacherByIdRequest request) throws NotFoundServiceException {
        Teacher teacher = teacherRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("invalid teacher id"));
        return new GetTeacherByIdResponse(teacher);
    }

    @Override
    public List<GetTeacherByIdResponse> getAll() {
        return teacherRepository.findAll().stream().map(o -> new GetTeacherByIdResponse(o.getId(), o.getLastName(), o.getFirstName(), o.getMiddleName())).toList();
    }
}
