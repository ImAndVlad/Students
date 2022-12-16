package com.example.students.util.mapper;

import com.example.students.domain.Student;
import com.example.students.dto.SaveDto;
import com.example.students.dto.StudentDto;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    Student saveDto(SaveDto studentDto);

    StudentDto toDto(Student student);

}
