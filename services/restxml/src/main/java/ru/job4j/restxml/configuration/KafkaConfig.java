package ru.job4j.restxml.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@EnableAspectJAutoProxy
@Configuration
public class KafkaConfig {

    @Value("${topic.name.one-requests}")
    private String oneRequests;
    @Value("${topic.name.one-replies}")
    private String oneReplies;
    @Value("${topic.name.all-requests}")
    private String allRequests;
    @Value("${topic.name.all-replies}")
    private String allReplies;
    @Value("${group.name.one-replies}")
    private String oneRepliesGroup;
    @Value("${group.name.all-replies}")
    private String allRepliesGroup;

    @Bean
    public ReplyingKafkaTemplate<String, String, String> oneReplyingTemplate(
        ProducerFactory<String, String> pf,
        ConcurrentMessageListenerContainer<String, String> oneRepliesContainer) {
        return new ReplyingKafkaTemplate<>(pf, oneRepliesContainer);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, String> oneRepliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
        containerFactory.setConcurrency(10);
        ConcurrentMessageListenerContainer<String, String> repliesContainer =
                containerFactory.createContainer(oneReplies);
        repliesContainer.getContainerProperties().setGroupId(oneRepliesGroup);
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    @Bean
    public NewTopic oneRequests() {
        return TopicBuilder.name(oneRequests)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic oneReplies() {
        return TopicBuilder.name(oneReplies)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public ReplyingKafkaTemplate<String, String, String> allReplyingTemplate(
            ProducerFactory<String, String> pf,
            ConcurrentMessageListenerContainer<String, String> allRepliesContainer) {
        return new ReplyingKafkaTemplate<>(pf, allRepliesContainer);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, String> allRepliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
        containerFactory.setConcurrency(10);
        ConcurrentMessageListenerContainer<String, String> repliesContainer =
                containerFactory.createContainer(allReplies);
        repliesContainer.getContainerProperties().setGroupId(allRepliesGroup);
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    @Bean
    public NewTopic allRequests() {
        return TopicBuilder.name(allRequests)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic allReplies() {
        return TopicBuilder.name(allReplies)
                .partitions(10)
                .replicas(1)
                .build();
    }
}
