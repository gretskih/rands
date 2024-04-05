package ru.job4j.restxml.service;

import ru.job4j.restxml.dto.Student;

import java.util.List;

public interface StudentService {
    Student findOneStudent(Integer number);

    List<Student> findAllStudents(String sortDirection);
}
