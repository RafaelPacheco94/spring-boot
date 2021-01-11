package org.example.configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.Arrays;

@Configuration
public class MongoTemplateConfiguration {

    @Value("${spring.data.mongo.host}")
    private String host;

    @Value("${spring.data.mongo.port}")
    private int port;

    @Value("${spring.data.mongo.database}")
    private String database;

    @Value("${spring.data.mongo.username}")
    private String username;

    @Value("${spring.data.mongo.password}")
    private String password;

    @Bean
    public MongoClient mongoClient() {
        ServerAddress serverAddress = new ServerAddress(host, port);

        MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(mongoCredential)
                .applyToSslSettings(builder -> builder.enabled(false))
                .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(serverAddress)))
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient(), database);
        return factory;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate template = new MongoTemplate(mongoDbFactory());
        return template;
    }
}
