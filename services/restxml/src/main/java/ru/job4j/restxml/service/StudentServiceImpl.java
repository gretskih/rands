package ru.job4j.restxml.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;
import ru.job4j.restxml.dto.SortDirection;
import ru.job4j.restxml.domain.Student;
import ru.job4j.restxml.dto.TestNumberBook;
import ru.job4j.restxml.exception.ServiceException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Value("${kafka.receive.timeout}")
    private long timeoutReceive;
    @Value("${topic.name.one-requests}")
    private String oneRequests;
    @Value("${topic.name.all-requests}")
    private String allRequests;
    private final ReplyingKafkaTemplate<String, String, String> oneReplyingKafkaTemplate;
    private final ReplyingKafkaTemplate<String, String, String> allReplyingKafkaTemplate;
    private final XmlMapper xmlMapper;

    public StudentServiceImpl(@Qualifier("oneReplyingTemplate") ReplyingKafkaTemplate<String, String, String> oneReplyingKafkaTemplate,
                              @Qualifier("allReplyingTemplate") ReplyingKafkaTemplate<String, String, String> allReplyingKafkaTemplate) {
        this.oneReplyingKafkaTemplate = oneReplyingKafkaTemplate;
        this.allReplyingKafkaTemplate = allReplyingKafkaTemplate;
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public Student findOneStudent(Integer number) throws ServiceException {
        try {
            String msgId = UUID.randomUUID().toString();
            ProducerRecord<String, String> record = new ProducerRecord<>(oneRequests, msgId, toXml(new TestNumberBook(number)));
            String xmlReqMsg = sendAndReceive(oneReplyingKafkaTemplate.sendAndReceive(record), timeoutReceive, TimeUnit.SECONDS);
            return xmlMapper.readValue(xmlReqMsg, Student.class);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new ServiceException("Ошибка при выполнении запроса", e);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Ошибка при конвертации запроса, ответа", e);
        }
    }

    @Override
    public List<Student> findAllStudents(String sortDirection) throws ServiceException {
        try {
            String msgId = UUID.randomUUID().toString();
            ProducerRecord<String, String> record = new ProducerRecord<>(allRequests, msgId, toXml(new SortDirection(sortDirection)));
            String xmlReqMsg = sendAndReceive(allReplyingKafkaTemplate.sendAndReceive(record), timeoutReceive, TimeUnit.SECONDS);
            return xmlMapper.readValue(xmlReqMsg, new TypeReference<>() { });
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new ServiceException("Ошибка при выполнении запроса", e);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Ошибка при конвертации запроса, ответа", e);
        }
    }

    private String sendAndReceive(RequestReplyFuture<String, String, String> replyFuture, long timeout, TimeUnit timeUnit)
            throws ExecutionException, InterruptedException, TimeoutException {
        ConsumerRecord<String, String> consumerRecord = replyFuture.get(timeout, timeUnit);
        return consumerRecord.value();
    }

    private String toXml(Object obj) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(obj);
    }
}
