package com.example.studentsspring.service_tests;


import com.example.studentsspring.entity.Student;
import com.example.studentsspring.entity.StudentGroup;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.repository.IStudentGroupRepository;
import com.example.studentsspring.repository.IStudentRepository;
import com.example.studentsspring.request.student_request.*;
import com.example.studentsspring.response.student_response.AddStudentResponse;
import com.example.studentsspring.response.student_response.GetStudentByIdResponse;
import com.example.studentsspring.service.service_realization.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private IStudentRepository studentRepository;

    @Mock
    private IStudentGroupRepository studentGroupRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testAddStudent() throws NotFoundServiceException {
        AddStudentRequest request = new AddStudentRequest("John", "Doe", "M", "ACTIVE", 1L);
        StudentGroup group = new StudentGroup();
        group.setId(1L);
        when(studentGroupRepository.getReferenceById(anyLong())).thenReturn(group);

        Student student = new Student();
        student.setId(1L);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        AddStudentResponse response = studentService.add(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testDeleteStudent() throws NotFoundServiceException {
        doNothing().when(studentRepository).deleteById(anyLong());

        DeleteStudentRequest request = new DeleteStudentRequest(1L);
        studentService.delete(request);

        verify(studentRepository, times(1)).deleteById(request.getId());
    }

    @Test
    void testEditStudent() throws NotFoundServiceException {
        EditStudentRequest request = new EditStudentRequest(1L, "Doe", "John", "M", "ACTIVE", 1L);
        Student student = new Student();
        student.setId(1L);
        StudentGroup group = new StudentGroup();
        group.setId(1L);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentGroupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        studentService.edit(request);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentById() throws NotFoundServiceException {
        GetStudentByIdRequest request = new GetStudentByIdRequest(1L);
        Student student = new Student();
        student.setGroup(new StudentGroup());
        student.setId(1L);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        GetStudentByIdResponse response = studentService.getById(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testGetByGroupId() throws NotFoundServiceException {
        StudentGroup group = new StudentGroup();
        group.setId(1L);
        group.setName("Group 1");
        GetStudentsByGroupIdRequest request = new GetStudentsByGroupIdRequest(1L);
        Student student1 = new Student();
        student1.setId(1L);
        student1.setGroup(group);
        Student student2 = new Student();
        student2.setId(2L);
        student2.setGroup(group);
        Student student3 = new Student();
        student3.setId(3L);
        student3.setGroup(group);


        when(studentGroupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        when(studentRepository.findAllByGroupId(anyLong())).thenReturn(Arrays.asList(student1, student2, student3));

        List<GetStudentByIdResponse> responses = studentService.getByGroupId(request);
        assertNotNull(responses);
        assertFalse(responses.isEmpty());
        assertEquals(3, responses.size());
    }

    @Test
    void testGetByGroupId_GroupNotFound() {
        GetStudentsByGroupIdRequest request = new GetStudentsByGroupIdRequest(1L);

        when(studentGroupRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundServiceException exception = assertThrows(NotFoundServiceException.class, () -> {
            studentService.getByGroupId(request);
        });

        assertEquals("Group not found", exception.getMessage());
    }

}
