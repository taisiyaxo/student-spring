package com.example.studentsspring.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "lesson_attendance")
public class LessonAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id", unique = true)
    private Lesson lesson;

    @ManyToMany
    @JoinTable(name = "lesson_attendance_record",
            joinColumns = @JoinColumn(name = "lesson_attendance_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();

    public LessonAttendance() {
    }

    public LessonAttendance(Long id, Lesson lesson, List<Student> student) {
        this.id = id;
        this.lesson = lesson;
        this.students.addAll(student);
    }
}

