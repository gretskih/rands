package ru.job4j.soap.service;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.job4j.soap.model.Student;
import ru.job4j.soap.repository.StudentRepository;

import java.util.List;

@Service
@WebService(serviceName = "StudentService", portName = "Port",
        targetNamespace = "http://",
        endpointInterface = "ru.job4j.soap.service.Students")
public class StudentsImpl implements Students {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentsImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(String sortDirection) {
        try {
            if ("DESC".equals(sortDirection)) {
                return studentRepository.findAll(Sort.by(Sort.Direction.DESC, "lastName"));
            }
            return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Student getOneStudent(Integer testBookNumber) {
        try {
            return studentRepository.findByTestBookNumber(testBookNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
