package com.example.students.repository;

import com.example.students.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Student, Integer>  {

    @Query("select s from Student s where s.id = ?1")
    // A method that is used to find a student by their id.
    Student findStudentById(Integer id);

}
