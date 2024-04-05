package ru.job4j.restxml.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;
import ru.job4j.restxml.dto.SortDirection;
import ru.job4j.restxml.dto.Student;
import ru.job4j.restxml.dto.TestNumberBook;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class StudentServiceImpl implements StudentService {

    private ReplyingKafkaTemplate<String, String, String> oneReplyingKafkaTemplate;

    private ReplyingKafkaTemplate<String, String, String> allReplyingKafkaTemplate;
    private XmlMapper xmlMapper = new XmlMapper();

    public StudentServiceImpl(@Qualifier("oneReplyingTemplate") ReplyingKafkaTemplate<String, String, String> oneReplyingKafkaTemplate,
                              @Qualifier("allReplyingTemplate")ReplyingKafkaTemplate<String, String, String> allReplyingKafkaTemplate) {
        this.oneReplyingKafkaTemplate = oneReplyingKafkaTemplate;
        this.allReplyingKafkaTemplate = allReplyingKafkaTemplate;
    }

    @Override
    public Student findOneStudent(Integer number) {
        try {
            String studentId = UUID.randomUUID().toString();
            String xmlMsg = xmlMapper.writeValueAsString(new TestNumberBook(number));
            ProducerRecord<String, String> record = new ProducerRecord<>("oneRequests", studentId, xmlMsg);
            RequestReplyFuture<String, String, String> replyFuture = oneReplyingKafkaTemplate.sendAndReceive(record);
            ConsumerRecord<String, String> consumerRecord;
            consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
            String xmlReqMsg = consumerRecord.value();
            Student studentDTO = xmlMapper.readValue(xmlReqMsg, Student.class);
            Image image = new ImageIcon(studentDTO.getPhoto()).getImage();
            return studentDTO;
        } catch (InterruptedException | ExecutionException | JsonProcessingException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAllStudents(String sortDirection) {
        try {
            String studentsId = UUID.randomUUID().toString();
            String xmlMsg = xmlMapper.writeValueAsString(new SortDirection(sortDirection));
            ProducerRecord<String, String> record = new ProducerRecord<>("allRequests", studentsId, xmlMsg);
            RequestReplyFuture<String, String, String> replyFuture = allReplyingKafkaTemplate.sendAndReceive(record);
            ConsumerRecord<String, String> consumerRecord;
            consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
            String xmlReqMsg = consumerRecord.value();
            return xmlMapper.readValue(xmlReqMsg, new TypeReference<>() { });
        } catch (InterruptedException | ExecutionException | TimeoutException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
