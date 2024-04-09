package ru.job4j.restxml.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.restxml.domain.Student;
import ru.job4j.restxml.exception.ControllerException;
import ru.job4j.restxml.exception.ServiceException;
import ru.job4j.restxml.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{number}")
    public ResponseEntity<Student> student(@PathVariable Integer number) throws ControllerException {
        Student student;
        try {
            student = studentService.findOneStudent(number);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> students(@RequestParam("sort") String sortDirection) throws ControllerException {
        List<Student> students;
        try {
            students = studentService.findAllStudents(sortDirection);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(students);
    }
}
