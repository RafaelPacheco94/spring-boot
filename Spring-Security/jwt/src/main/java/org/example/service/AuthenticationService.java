package org.example.service;

import org.example.dto.AuthenticationRequestDto;
import org.example.dto.RegistrationDto;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.util.InvalidPasswordException;
import org.example.util.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found!"));
    }

    public User authenticate(AuthenticationRequestDto authenticationRequestDto) {
        User user = userRepository.findByEmail(authenticationRequestDto.getEmail()).orElseThrow(() -> new UserNotFoundException("User Not Found!"));
        if (passwordEncoder.matches(CharBuffer.wrap(authenticationRequestDto.getPassword()), user.getPassword())) {
            return user;
        } else {
            throw new InvalidPasswordException("Invalid Password");
        }
    }

    public boolean register(RegistrationDto registrationDto) {
        registrationDto.setPassword(passwordEncoder.encode(CharBuffer.wrap(registrationDto.getPassword())));
        User user = userMapper.mapUserFromRegistrationDto(registrationDto);
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);
        return true;
    }


}
