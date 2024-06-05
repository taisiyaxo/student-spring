package com.example.studentsspring.service_tests;

import com.example.studentsspring.entity.Subject;
import com.example.studentsspring.exception.service_exception.NotFoundServiceException;
import com.example.studentsspring.repository.ISubjectRepository;
import com.example.studentsspring.request.subject_request.*;
import com.example.studentsspring.response.subject_response.*;
import com.example.studentsspring.service.service_realization.SubjectService;
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
public class SubjectServiceTest {

    @Mock
    private ISubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    @Test
    void testAddSubject() throws NotFoundServiceException{
        AddSubjectRequest request = new AddSubjectRequest("Math");
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");

        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        AddSubjectResponse response = subjectService.add(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testDeleteSubject() throws NotFoundServiceException {
        Subject subject = new Subject();
        subject.setId(1L);

        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));

        DeleteSubjectRequest request = new DeleteSubjectRequest(1L);
        subjectService.delete(request);

        verify(subjectRepository, times(1)).deleteById(request.getId());
    }

    @Test
    void testEditSubject() throws NotFoundServiceException {
        EditSubjectRequest request = new EditSubjectRequest( "Physics", 1L);
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");

        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        subjectService.edit(request);

        verify(subjectRepository, times(1)).save(subject);
        assertEquals("Physics", subject.getName());
    }

    @Test
    void testGetSubjectById() throws NotFoundServiceException {
        GetSubjectByIdRequest request = new GetSubjectByIdRequest(1L);
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");

        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));

        GetSubjectByIdResponse response = subjectService.getById(request);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Math", response.getName());
    }
}
