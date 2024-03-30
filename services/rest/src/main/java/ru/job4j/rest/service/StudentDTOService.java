package ru.job4j.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.rest.cxf.StudentOneRequest;
import ru.job4j.rest.cxf.StudentsRequest;
import ru.job4j.rest.dto.StudentDTO;

import java.util.List;

@Service
public class StudentDTOService {

    private final StudentsStorageService studentsStorageService;

    @Autowired
    public StudentDTOService(StudentService studentService) {
        this.studentsStorageService = studentService.getPort();
    }

    public StudentDTO findStudent(Integer number) {
        var request = new StudentOneRequest();
        request.setNumber(number);
        return studentsStorageService.getOneStudent(request).getStudent();
    }

    public List<StudentDTO> findAllStudents(String directSort) {
        var request = new StudentsRequest();
        request.setSort(directSort);
        return studentsStorageService.getAllStudents(request).getStudent();
    }
}
