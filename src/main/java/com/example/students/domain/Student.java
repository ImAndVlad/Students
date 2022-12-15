package com.example.students.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String date;
}
