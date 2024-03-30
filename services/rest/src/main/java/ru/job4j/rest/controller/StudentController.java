package ru.job4j.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.rest.dto.StudentDTO;
import ru.job4j.rest.service.StudentDTOService;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentDTOService studentService;

    @GetMapping("/{number}")
    public ResponseEntity<StudentDTO> student(@PathVariable Integer number) {
        return new ResponseEntity<>(studentService.findStudent(number), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> students(@RequestParam("sort") String sortDirection) {
        return new ResponseEntity<>(studentService.findAllStudents(sortDirection), HttpStatus.OK);
    }
}
