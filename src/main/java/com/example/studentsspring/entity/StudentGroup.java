package com.example.studentsspring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_group")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="name", nullable = false, unique = true)
    private String name;

    public StudentGroup() {
    }

    public StudentGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
