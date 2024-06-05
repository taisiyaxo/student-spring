package com.example.studentsspring.repository;

import com.example.studentsspring.entity.LessonAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILessonAttendanceRepository extends JpaRepository<LessonAttendance, Long> {
    LessonAttendance findAttendanceByLessonId(Long lessonId);

    void deleteAttendanceByLessonId(Long lessonId);
}
