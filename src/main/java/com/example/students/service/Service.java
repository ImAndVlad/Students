package com.example.students.service;

import com.example.students.domain.Student;

public interface Service {

    Student create(Student student);

    Student getById(Integer id);

    Student updateById(Integer id, Student plane);

    void removeById(Integer id);

    int getYears(Integer id);

}
