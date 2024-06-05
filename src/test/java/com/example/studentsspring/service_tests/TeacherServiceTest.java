package com.example.studentsspring.service_tests;

import com.example.studentsspring.entity.Teacher;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.exception.service_exception.ServiceException;
import com.example.studentsspring.repository.ITeacherRepository;
import com.example.studentsspring.request.teacher_request.*;
import com.example.studentsspring.response.teacher_response.*;
import com.example.studentsspring.service.service_realization.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @Mock
    private ITeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @Test
    void testAddTeacher() {
        AddTeacherRequest request = new AddTeacherRequest("John", "Doe", "Smith");
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("Doe");
        teacher.setFirstName("John");
        teacher.setMiddleName("Smith");

        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        AddTeacherResponse response = teacherService.add(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testDeleteTeacher() throws NotFoundServiceException {
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacher));

        DeleteTeacherRequest request = new DeleteTeacherRequest(1L);
        teacherService.delete(request);

        verify(teacherRepository, times(1)).deleteById(request.getId());
    }

    @Test
    void testEditTeacher() throws ServiceException {
        EditTeacherRequest request = new EditTeacherRequest(1L, "Doe","John",  "Smith");
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("Doe");
        teacher.setFirstName("John");
        teacher.setMiddleName("Smith");

        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        teacherService.edit(request);

        verify(teacherRepository, times(1)).save(teacher);
        assertEquals("Doe", teacher.getLastName());
        assertEquals("John", teacher.getFirstName());
        assertEquals("Smith", teacher.getMiddleName());
    }

    @Test
    void testGetTeacherById() throws NotFoundServiceException {
        GetTeacherByIdRequest request = new GetTeacherByIdRequest(1L);
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("Doe");
        teacher.setFirstName("John");
        teacher.setMiddleName("Smith");

        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacher));

        GetTeacherByIdResponse response = teacherService.getById(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Doe", response.getLastName());
        assertEquals("John", response.getFirstName());
        assertEquals("Smith", response.getMiddleName());
    }
}
