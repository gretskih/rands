package ru.job4j.restxml.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.restxml.dto.Student;
import ru.job4j.restxml.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{number}")
    public ResponseEntity<Student> student(@PathVariable Integer number) {
        var student = studentService.findOneStudent(number);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> students(@RequestParam("sort") String sortDirection) {
        var students = studentService.findAllStudents(sortDirection);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(students);
    }
}