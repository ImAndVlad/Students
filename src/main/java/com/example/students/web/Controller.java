package com.example.students.web;

import com.example.students.domain.Student;
import com.example.students.dto.SaveDto;
import com.example.students.dto.StudentDto;
import com.example.students.service.Service;
import com.example.students.util.mapper.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Student", description = "Student API")
public class Controller {

    private final Service service;
    private final Mapper mapper;

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new student.",
            description = "Create request to add a new student.", tags = {"Student"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new student is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified student request not found."),
            @ApiResponse(responseCode = "409", description = "Student already exists")})
    public Student saveStudent(@RequestBody @Valid SaveDto requestForSave) {
        log.debug("saveStudent() Controller - start");
        var student = mapper.saveDto(requestForSave);
        log.debug("saveStudent() Controller - end");
        return student;
    }

    @PutMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student refreshStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        return service.updateById(id, student);
    }

    @PatchMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeStudentById(@PathVariable Integer id) {
        service.removeById(id);
    }

    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a student by his id.",
            description = "Create request to read a student by id", tags = {"Student"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified student request not found."),
            @ApiResponse(responseCode = "409", description = "Student already exists")})
    // A method that returns a student by his id.
    public StudentDto getStudentById(@PathVariable Integer id) {
        log.debug(" getStudentById() Controller - start: id = {}", id);
        var student = service.getById(id);
        var dto = mapper.toDto(student);
        dto.years = service.getYears(id);
        log.debug(" getStudentById() Controller - end: name = {}", dto.name);
        return dto;
    }


}
