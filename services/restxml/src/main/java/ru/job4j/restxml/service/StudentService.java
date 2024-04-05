package ru.job4j.restxml.service;

import ru.job4j.restxml.domain.Student;
import ru.job4j.restxml.exception.ServiceException;

import java.util.List;

public interface StudentService {
    Student findOneStudent(Integer number) throws ServiceException;

    List<Student> findAllStudents(String sortDirection) throws ServiceException;
}
