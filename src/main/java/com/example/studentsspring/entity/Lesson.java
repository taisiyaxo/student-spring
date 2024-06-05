package com.example.studentsspring.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="lesson")
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="subject_id", referencedColumnName="id")
    private Subject subject;
    @Column(name="date", nullable=false)
    private String date;
    @Column(name = "number", nullable=false)
    private int number;
    @ManyToOne
    @JoinColumn(name="teacher_id", referencedColumnName="id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name="student_group_id", referencedColumnName="id")
    private StudentGroup group;

    public  Lesson(Lesson lesson){
        this(lesson.getId(),lesson.getSubject(),lesson.getDate(),lesson.getNumber(),lesson.getTeacher(),lesson.getGroup());
    }
}
