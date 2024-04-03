package ru.job4j.restxml.service;

import ru.job4j.restxml.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO findOneStudent(Integer number);

    List<StudentDTO> findAllStudents(String sortDirection);
}
