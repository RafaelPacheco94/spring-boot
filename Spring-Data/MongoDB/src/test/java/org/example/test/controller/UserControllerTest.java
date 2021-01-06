package org.example.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.UserController;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest
public class UserControllerTest {

    public MockMvc mockMvc;

    public static ObjectMapper objectMapper;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    private static PodamFactory podamFactory;

    private UserDTO userDTO;

    @Autowired
    private UserMapper userMapper;

    @BeforeAll
    static void beforeAll() {
        podamFactory = new PodamFactoryImpl();
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userDTO = new UserDTO();
    }


    @Test
    @DisplayName("Insert User Success")
    public void insertUserSuccessTest() throws Exception {
        // given
        userDTO = podamFactory.manufacturePojo(userDTO.getClass());

        // when
        Mockito.when(userService.insertUser(Mockito.any(User.class))).thenReturn(true);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/insertUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userDTO)))
                .andExpect(MockMvcResultMatchers.status().is(200));

        // verify
        Mockito.verify(userService, Mockito.atLeastOnce()).insertUser(Mockito.any(User.class));
        Mockito.verify(userService, Mockito.atMostOnce()).insertUser(Mockito.any(User.class));
    }


    @Test
    @DisplayName("Insert User Failure")
    public void insertUserFailureTest() throws Exception {
        // given
        userDTO = podamFactory.manufacturePojo(userDTO.getClass());

        // when
        Mockito.when(userService.insertUser(userMapper.userDtoToUser(userDTO))).thenReturn(false);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/insertUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userDTO)))
                .andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\"error_message\": 'User already exists!'}"));

        // verify
        Mockito.verify(userService, Mockito.atLeastOnce()).insertUser(Mockito.any(User.class));
        Mockito.verify(userService, Mockito.atMostOnce()).insertUser(Mockito.any(User.class));
    }

    @Test
    @DisplayName("Insert User Failure - No Body As Parameter")
    public void insertUserFailureNoBodyParameterTest() throws Exception {
        // given

        // when
        Mockito.when(userService.insertUser(Mockito.any(User.class))).thenReturn(true);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/insertUser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

        // verify
        Mockito.verify(userService, Mockito.never()).insertUser(Mockito.any(User.class));
    }

    @Test
    @DisplayName("Insert User Failure - Invalid Body As Parameter")
    public void insertUserFailureInvalidBodyParameterTest() throws Exception {
        // given - userDTO is null

        // when
        Mockito.when(userService.insertUser(Mockito.any(User.class))).thenReturn(true);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/insertUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userDTO)))
                .andExpect(MockMvcResultMatchers.status().is(400));

        // verify
        Mockito.verify(userService, Mockito.never()).insertUser(Mockito.any(User.class));
    }


    // Repeat for /deleteUser
    // Repeat for /getUser
    // Repeat for /updateUser


}
