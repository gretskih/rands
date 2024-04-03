package ru.job4j.restxml.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import ru.job4j.restxml.dto.StudentDTO;

@Configuration
public class KafkaConfig {

    @Bean
    public ReplyingKafkaTemplate<String, String, StudentDTO> replyingTemplate(
        ProducerFactory<String, String> pf,
        ConcurrentMessageListenerContainer<String, StudentDTO> repliesContainer) {

        return new ReplyingKafkaTemplate<>(pf, repliesContainer);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, StudentDTO> repliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, StudentDTO> containerFactory) {

        ConcurrentMessageListenerContainer<String, StudentDTO> repliesContainer =
                containerFactory.createContainer("kReplies");
        repliesContainer.getContainerProperties().setGroupId("repliesGroup");
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    @Bean
    public NewTopic kRequests() {
        return TopicBuilder.name("kRequests")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic kReplies() {
        return TopicBuilder.name("kReplies")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
