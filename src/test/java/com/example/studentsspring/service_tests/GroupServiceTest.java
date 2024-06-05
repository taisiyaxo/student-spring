package com.example.studentsspring.service_tests;

import com.example.studentsspring.entity.StudentGroup;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.repository.IStudentGroupRepository;
import com.example.studentsspring.repository.IStudentRepository;
import com.example.studentsspring.request.student_group_request.*;
import com.example.studentsspring.response.student_group_response.*;
import com.example.studentsspring.service.service_realization.StudentGroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    private IStudentGroupRepository groupRepository;

    @Mock
    private IStudentRepository studentRepository;

    @InjectMocks
    private StudentGroupService studentGroupService;

    @Test
    void testAddStudentGroup() {
        AddStudentGroupRequest request = new AddStudentGroupRequest("Group 1");
        StudentGroup group = new StudentGroup(null, "Group 1");
        StudentGroup savedGroup = new StudentGroup(1L, "Group 1");

        when(groupRepository.save(any(StudentGroup.class))).thenReturn(savedGroup);

        AddStudentGroupResponse response = studentGroupService.add(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Group 1", response.getName());
    }

    @Test
    void testDeleteStudentGroup() throws ServiceException {
        StudentGroup group = new StudentGroup(1L, "Group 1");

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        when(studentRepository.findAllByGroupId(anyLong())).thenReturn(Collections.emptyList());

        DeleteStudentGroupRequest request = new DeleteStudentGroupRequest(1L);
        studentGroupService.delete(request);

        verify(groupRepository, times(1)).deleteById(request.getId());
    }

    @Test
    void testEditStudentGroup() throws NotFoundServiceException {
        EditStudentGroupRequest request = new EditStudentGroupRequest(1L, "Group 1");
        StudentGroup group = new StudentGroup(1L, "Old Group");

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        when(groupRepository.save(any(StudentGroup.class))).thenReturn(group);

        studentGroupService.edit(request);

        verify(groupRepository, times(1)).save(group);
        assertEquals("Group 1", group.getName());
    }

    @Test
    void testGetStudentGroupById() throws NotFoundServiceException {
        GetStudentGroupByIdRequest request = new GetStudentGroupByIdRequest(1L);
        StudentGroup group = new StudentGroup(1L, "Group 1");

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));

        GetStudentGroupByIdResponse response = studentGroupService.getById(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Group 1", response.getName());
    }
}
