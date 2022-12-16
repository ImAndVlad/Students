package com.example.students;


import com.example.students.domain.Student;
import com.example.students.repository.Repository;
import com.example.students.service.ServiceBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    @Mock
    private Repository repository;

    @InjectMocks
    private ServiceBean service;

    @Test
    public void whenSaveStudent_shouldReturnEmployee() {
        Student student = new Student();
        student.setName("Mark");

        when(repository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);

        Student created = service.create(student);

        assertThat(created.getName()).isSameAs(student.getName());
        verify(repository).save(student);
    }

    @Test
    public void whenGivenId_shouldReturnStudent_ifFound() {
        Student student = new Student();
        student.setId(88);

        when(repository.findStudentById(student.getId())).thenReturn(student);

        Student expected = service.getById(student.getId());

        assertThat(expected).isSameAs(student);
        verify(repository).findStudentById(student.getId());
    }

}
