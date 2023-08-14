package com.example.mmanalog.controllers;

import com.example.mmanalog.models.User;
import com.example.mmanalog.services.UserService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setUsername("testUser200");
    }

    @Test
    void retrieveUser() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/users/testUser200"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}