package com.example.students.service;

import com.example.students.domain.Student;
import com.example.students.repository.Repository;
import lombok.AllArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;

    @Override
    public Student create(Student student) {
        return repository.save(student);
    }

    @Override
    public Student updateById(Integer id, Student student) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(student.getName());
                    entity.setSurname(student.getSurname());
                    entity.setYear(student.getYear());
                    entity.setMonth(student.getMonth());
                    entity.setDay(student.getDay());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        Student student = repository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Student not found with id = " + id));

        repository.delete(student);
    }

    @Override
    // Getting the student by id.
    public Student getById(Integer id) {
        var student = repository.findStudentById(id);

        return student;
    }

    @Override
    public int getYears(Integer id) {
        Student student = getById(id);
        return LocalDate.now().getYear() - student.getYear();
    }

}
