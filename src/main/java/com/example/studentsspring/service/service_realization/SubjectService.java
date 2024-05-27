package com.example.studentsspring.service.service_realization;

import com.example.studentsspring.entity.Subject;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.repository.ISubjectRepository;
import com.example.studentsspring.request.subject_request.AddSubjectRequest;
import com.example.studentsspring.request.subject_request.DeleteSubjectRequest;
import com.example.studentsspring.request.subject_request.EditSubjectRequest;
import com.example.studentsspring.request.subject_request.GetSubjectByIdRequest;
import com.example.studentsspring.response.subject_response.AddSubjectResponse;
import com.example.studentsspring.response.subject_response.GetSubjectByIdResponse;
import com.example.studentsspring.service.service_interface.ISubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SubjectService implements ISubjectService {

    private ISubjectRepository subjectRepository;


    @Override
    public AddSubjectResponse add(AddSubjectRequest addSubjectRequest) throws NotFoundServiceException {
        Subject subject = new Subject();
        subject.setName(addSubjectRequest.getName());
        var savedSubject = subjectRepository.save(subject);
        return new AddSubjectResponse(savedSubject.getId());
    }

    @Override
    public void delete(DeleteSubjectRequest deleteSubjectRequest) throws NotFoundServiceException {
            subjectRepository.findById(deleteSubjectRequest.getId())
                            .orElseThrow(() -> new NotFoundServiceException("Subject with invalid id "));
            subjectRepository.deleteById(deleteSubjectRequest.getId());
    }

    @Override
    public void edit(EditSubjectRequest editSubjectRequest) throws NotFoundServiceException {
        Subject subject = subjectRepository.findById(editSubjectRequest.getId())
                .orElseThrow(() -> new NotFoundServiceException("Subject with invalid id "));
        subject.setName(editSubjectRequest.getName());
        subjectRepository.save(subject);
    }

    @Override
    public GetSubjectByIdResponse getById(GetSubjectByIdRequest getSubjectRequest) throws NotFoundServiceException {
        Subject subject = subjectRepository.findById(getSubjectRequest.getId())
                .orElseThrow(() -> new NotFoundServiceException("Subject with invalid id "));
        return new GetSubjectByIdResponse(subject);
    }

    @Override
    public List<GetSubjectByIdResponse> getAll() {
        return subjectRepository.findAll().stream()
                .map(subject -> new GetSubjectByIdResponse(subject.getId(), subject.getName())).toList();
    }
}
