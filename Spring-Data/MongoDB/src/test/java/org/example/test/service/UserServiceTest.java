package org.example.test.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private static PodamFactory podamFactory;

    private User user;

    @BeforeAll
    static void beforeAll() {
        podamFactory = new PodamFactoryImpl();
    }

    @BeforeEach
    void beforeEach() {
        user = new User();
    }


    @Test
    @DisplayName("Insert User Success")
    public void insertUserSuccessTest() {
        // given
        user = podamFactory.manufacturePojo(user.getClass());
        Mockito.when(userRepository.insertUser(Mockito.any(User.class))).thenReturn(true);

        // when
        Boolean bool = userService.insertUser(user);

        // then
        Mockito.verify(userRepository, Mockito.atMostOnce()).insertUser(Mockito.any(User.class));
        Mockito.verify(userRepository, Mockito.atLeastOnce()).insertUser(Mockito.any(User.class));
        Assertions.assertTrue(bool);
    }

    @Test
    @DisplayName("Insert User Failure")
    public void insertUserFailureTest() {
        // given
        user = podamFactory.manufacturePojo(user.getClass());
        Mockito.when(userRepository.insertUser(Mockito.any(User.class))).thenReturn(false);

        // when
        Boolean bool = userService.insertUser(user);

        // then
        Mockito.verify(userRepository, Mockito.atMostOnce()).insertUser(Mockito.any(User.class));
        Mockito.verify(userRepository, Mockito.atLeastOnce()).insertUser(Mockito.any(User.class));
        Assertions.assertFalse(bool);
    }

}
