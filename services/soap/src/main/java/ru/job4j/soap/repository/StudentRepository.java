package ru.job4j.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.soap.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByTestBookNumber(Integer testBookNumber);
}
