package org.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Value("consumer.kafka.stock.topic-name-stock-list")
    private String stockListTopic;
    @Value("consumer.kafka.stock.topic-name-stock")
    private String stockTopic;
    @Value(value = "consumer.kafka.stock.topic-name-simple-message")
    private String simpleMessageTopic;

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @Bean
    public ApplicationRunner runner(KafkaAdmin kafkaAdmin) {
        return args -> {
            AdminClient admin = AdminClient.create(kafkaAdmin.getConfigurationProperties());
            Collection<NewTopic> collection = new ArrayList<>();
            List<String> topics = new ArrayList<>();
            topics.add(stockListTopic);
            topics.add(stockTopic);
            topics.add(simpleMessageTopic);
            topics.forEach(s -> {
                if (s != null) {
                    try {
                        if (!admin.listTopics().listings().get().stream().anyMatch(topicListing -> topicListing.name().equals(s))) {
                            collection.add(TopicBuilder.name(s).partitions(2).replicas(1).build());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
            admin.createTopics(collection).all().get();
        };
    }
}