package org.example;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class App {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User("1", "email@email.com", passwordEncoder.encode(CharBuffer.wrap("12345")), "John", "Doe", "Client", true, true, true, false),
                new User("2", "email@gmail.com", passwordEncoder.encode(CharBuffer.wrap("54321")), "John", "Toe", "Client", true, true, true, false)
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }
}
