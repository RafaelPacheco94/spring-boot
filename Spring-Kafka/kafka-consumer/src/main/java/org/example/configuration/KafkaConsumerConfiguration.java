package org.example.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.model.Stock;
import org.example.model.StockList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value(value = "${consumer.kafka.stock.group-name}")
    private String stockGroupID;

    @Bean
    public ConsumerFactory<Integer, StockList> stockListConsumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, stockGroupID);

        JsonDeserializer<StockList> stockListJsonDeserializer = new JsonDeserializer<>(StockList.class);
        stockListJsonDeserializer.setRemoveTypeHeaders(false);
        stockListJsonDeserializer.addTrustedPackages("org.example.model");
        stockListJsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(properties, new IntegerDeserializer(),
                stockListJsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, StockList> stockListKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, StockList> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stockListConsumerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<Integer, Stock> stockConsumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, stockGroupID);

        JsonDeserializer<Stock> stockJsonDeserializer = new JsonDeserializer<>(Stock.class);
        stockJsonDeserializer.setRemoveTypeHeaders(false);
        stockJsonDeserializer.addTrustedPackages("org.example.model.StockList");
        stockJsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(properties, new IntegerDeserializer(),
                stockJsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, Stock> stockKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, Stock> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stockConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> simpleMessageConsumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, stockGroupID);
        return new DefaultKafkaConsumerFactory<>(properties, new IntegerDeserializer(),
                new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, String> simpleMessageKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(simpleMessageConsumerFactory());
        return factory;
    }

}
