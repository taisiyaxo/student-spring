package com.example.studentsspring.repository;

import com.example.studentsspring.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILessonRepository extends JpaRepository<Lesson, Long> {
    @Query("SELECT l FROM Lesson l WHERE l.group.id = :groupId AND l.date BETWEEN :startDate AND :endDate")
    List<Lesson> findLessonByGroupId(@Param("groupId") Long groupId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT l FROM Lesson l WHERE l.teacher.id = :teacherId AND l.date BETWEEN :startDate AND :endDate")
    List<Lesson> findLessonByTeacherId(@Param("teacherId") Long teacherId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}

