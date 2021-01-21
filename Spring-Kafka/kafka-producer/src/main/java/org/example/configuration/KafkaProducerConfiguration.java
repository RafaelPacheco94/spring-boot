package org.example.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.Stock;
import org.example.model.StockList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfiguration {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value(value = "${consumer.kafka.stock.group-name}")
    private String stockGroupID;

    @Bean
    public ProducerFactory<Integer, StockList> stockListProducerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaProducerFactory<>(properties, new IntegerSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<Integer, StockList> stockListKafkaTemplate() {
        return new KafkaTemplate<>(stockListProducerFactory());
    }

    @Bean
    public ProducerFactory<Integer, Stock> stockProducerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaProducerFactory<>(properties, new IntegerSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<Integer, Stock> stockKafkaTemplate() {
        return new KafkaTemplate<>(stockProducerFactory());
    }

    @Bean
    public ProducerFactory<Integer, String> simpleMessageProducerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaProducerFactory<>(properties, new IntegerSerializer(), new StringSerializer());
    }

    @Bean
    public KafkaTemplate<Integer, String> simpleMessageKafkaTemplate() {
        return new KafkaTemplate<>(simpleMessageProducerFactory());
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

}
