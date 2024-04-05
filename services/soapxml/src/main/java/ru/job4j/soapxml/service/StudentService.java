package ru.job4j.soapxml.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import ru.job4j.soapxml.dto.SortDirection;
import ru.job4j.soapxml.dto.StudentDTO;
import ru.job4j.soapxml.dto.TestNumberRequestDto;
import ru.job4j.soapxml.exception.ServiceException;
import ru.job4j.soapxml.model.Student;
import ru.job4j.soapxml.repository.StudentRepository;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final Minio minio;
    private final ModelMapper modelMapper;
    private XmlMapper xmlMapper;

    @KafkaListener(id = "server2", topics = "allRequests", concurrency = "10")
    @SendTo
    public String getAllStudents(String sort) {
        List<Student> list;
        try {
            SortDirection sortDirection = xmlMapper.readValue(sort, SortDirection.class);
            log.info("Find all students sort {}", sortDirection.getValue());
            if ("DESC".equals(sortDirection.getValue())) {
                list = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "lastName"));
            } else {
                list = studentRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
            }
            Type listType = new TypeToken<List<StudentDTO>>() {
            }.getType();
            List<StudentDTO> listDTO = modelMapper.map(list, listType);
            for (int i = 0; i < listDTO.size(); i++) {
                var arr = minio.getObject(list.get(i).getPhotoTitle());
                listDTO.get(i).setPhoto(arr);
            }
            return xmlMapper.writeValueAsString(listDTO);
        } catch (ServiceException | JsonProcessingException e) {
            log.error(e.getCause().getCause().toString(), e);
        }
        return null;
    }

    @KafkaListener(id = "server1", topics = "oneRequests", concurrency = "10")
    @SendTo
    public String getOneStudent(String testBookNumber) {
        try {
            TestNumberRequestDto testNumberRequestDto = xmlMapper.readValue(testBookNumber, TestNumberRequestDto.class);
            log.info("Find a student by testbook number {}", testNumberRequestDto.getValue());
            var student = studentRepository.findByTestBookNumber(testNumberRequestDto.getValue());
            var studentDTO = modelMapper.map(student, StudentDTO.class);
            var arr = minio.getObject(student.getPhotoTitle());
            studentDTO.setPhoto(arr);
            return xmlMapper.writeValueAsString(studentDTO);
        } catch (ServiceException | JsonProcessingException e) {
            log.error(e.getCause().getCause().toString(), e);
        }
        return null;
    }
}