package org.example.test.mapper;

import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.mapper.UserMapperImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private static UserMapper userMapper;

    private static PodamFactory podamFactory;

    private User user;

    private UserDTO userDTO;

    @BeforeAll
    static void beforeAll() {
        podamFactory = new PodamFactoryImpl();
        userMapper = new UserMapperImpl();
    }

    @BeforeEach
    void beforeEach() {
        user = new User();
        userDTO = new UserDTO();
    }

    @Test
    @DisplayName("UserDTO to User Mapper")
    public void userDtoToUserTest() {
        // given
        userDTO = podamFactory.manufacturePojo(userDTO.getClass());

        // when
        user = userMapper.userDtoToUser(userDTO);

        // then
        Assertions.assertAll(() -> {
            Assertions.assertEquals(userDTO.getName(), user.getName());
            Assertions.assertEquals(userDTO.getAge(), user.getAge());
        });
    }

    @Test
    @DisplayName("User to UserDTO Mapper")
    public void userToUserDTOTest() {
        // given
        user = podamFactory.manufacturePojo(user.getClass());

        // when
        userDTO = userMapper.userToUserDto(user);

        // then
        Assertions.assertAll(() -> {
            Assertions.assertEquals(user.getName(), userDTO.getName());
            Assertions.assertEquals(user.getAge(), userDTO.getAge());
        });
    }

}
