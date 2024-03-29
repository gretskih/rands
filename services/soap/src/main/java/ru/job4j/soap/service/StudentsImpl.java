package ru.job4j.soap.service;

import jakarta.jws.WebService;
import jakarta.xml.ws.soap.MTOM;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.job4j.soap.dto.StudentDTO;
import ru.job4j.soap.model.Student;
import ru.job4j.soap.repository.StudentRepository;

import java.lang.reflect.Type;
import java.util.List;

@Service
@WebService(serviceName = "StudentService", portName = "Port",
        targetNamespace = "http://",
        endpointInterface = "ru.job4j.soap.service.Students")
@MTOM(enabled = true, threshold = 10240)
public class StudentsImpl implements Students {

    private final StudentRepository studentRepository;
    private final Minio minio;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentsImpl(StudentRepository studentRepository, MinioImpl minio, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.minio = minio;
        this.modelMapper = modelMapper;
    }

    public List<StudentDTO> getAllStudents(String sortDirection) {
        List<Student> list;
        try {
            if ("DESC".equals(sortDirection)) {
                list = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "lastName"));
            } else {
                list = studentRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
            }
            Type listType = new TypeToken<List<StudentDTO>>() { }.getType();
            List<StudentDTO> listDTO = modelMapper.map(list, listType);
            for (int i = 0; i < listDTO.size(); i++) {
                var arr = minio.getObject(list.get(i).getPhotoTitle());
                listDTO.get(i).setPhoto(arr);
            }
            return listDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public StudentDTO getOneStudent(Integer testBookNumber) {
        try {
            var student = studentRepository.findByTestBookNumber(testBookNumber);
            var studentDTO = modelMapper.map(student, StudentDTO.class);
            var arr = minio.getObject(student.getPhotoTitle());
            studentDTO.setPhoto(arr);
            return studentDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
