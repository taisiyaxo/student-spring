package com.example.studentsspring.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "teacher")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "middleName", nullable = false)
    private String middleName;

}
