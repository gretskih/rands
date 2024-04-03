package ru.job4j.soapxml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.soapxml.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByTestBookNumber(Integer testBookNumber);
}
