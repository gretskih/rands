package ru.job4j.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.rest.model.Student;
import ru.job4j.rest.service.StudentService;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{number}")
    public ResponseEntity<List<Student>> students(@RequestParam Integer number) {
        return new ResponseEntity<>(studentService.findStudent(number), HttpStatus.ACCEPTED);
    }

}
