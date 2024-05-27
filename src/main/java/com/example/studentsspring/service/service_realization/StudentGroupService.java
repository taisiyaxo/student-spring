package com.example.studentsspring.service.service_realization;

import com.example.studentsspring.entity.StudentGroup;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.repository.IStudentGroupRepository;
import com.example.studentsspring.repository.IStudentRepository;
import com.example.studentsspring.request.student_group_request.AddStudentGroupRequest;
import com.example.studentsspring.request.student_group_request.DeleteStudentGroupRequest;
import com.example.studentsspring.request.student_group_request.EditStudentGroupRequest;
import com.example.studentsspring.request.student_group_request.GetStudentGroupByIdRequest;
import com.example.studentsspring.request.student_request.EditStudentRequest;
import com.example.studentsspring.request.student_request.GetStudentByIdRequest;
import com.example.studentsspring.response.student_group_response.AddStudentGroupResponse;
import com.example.studentsspring.response.student_group_response.GetStudentGroupByIdResponse;
import com.example.studentsspring.service.service_interface.IStudentGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class StudentGroupService implements IStudentGroupService {

    private final IStudentGroupRepository groupRepository;
    private final IStudentRepository studentRepository;

    @Override
    public AddStudentGroupResponse add(AddStudentGroupRequest request){
        StudentGroup group = new StudentGroup(null, request.getName());
        var save = groupRepository.save(group);
        return new AddStudentGroupResponse(save.getId(), save.getName());
    }

    @Override
    public void delete(DeleteStudentGroupRequest request) throws ServiceException {
        StudentGroup group = groupRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("Group not exist"));
        if(!studentRepository.findAllByGroupId(request.getId()).isEmpty()){
            throw new ServiceException("can not delete group with students");
        }
        groupRepository.deleteById(request.getId());
    }

    @Override
    public void edit(EditStudentGroupRequest request) throws NotFoundServiceException {
        StudentGroup group = groupRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("Group not exist"));
        group.setName(request.getName());
        groupRepository.save(group);
    }

    @Override
    public GetStudentGroupByIdResponse getById(GetStudentGroupByIdRequest request) throws NotFoundServiceException {
        StudentGroup group = groupRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundServiceException("Group not exist"));
        return new GetStudentGroupByIdResponse(group);

    }


}
