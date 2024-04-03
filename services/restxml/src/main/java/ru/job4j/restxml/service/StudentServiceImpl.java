package ru.job4j.restxml.service;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.job4j.restxml.dto.StudentDTO;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private ReplyingKafkaTemplate<String, String, StudentDTO> replyingKafkaTemplate;

    @Override
    public StudentDTO findOneStudent(Integer number) {
        try {
            String studentId = UUID.randomUUID().toString();
            ProducerRecord<String, String> record = new ProducerRecord<>("kRequests", studentId, String.valueOf(number));
            RequestReplyFuture<String, String, StudentDTO> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
            SendResult<String, String> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
            ConsumerRecord<String, StudentDTO> consumerRecord;
            consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
            return consumerRecord.value();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentDTO> findAllStudents(String sortDirection) {
        return null;
    }
}
