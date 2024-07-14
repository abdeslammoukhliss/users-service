package org.test.usersservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.test.usersservice.controllers.UserController;
import org.test.usersservice.entities.User;
import org.test.usersservice.services.UserService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import java.util.Collections;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"john_doe\",\"email\":\"john@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("john_doe"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user = new User(null,"john_doe", "john@example.com");

        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username").value("john_doe"));
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        User user = new User(null,"john_doe", "john@example.com");

        when(userService.findUserByUsername("john_doe")).thenReturn(user);

        mockMvc.perform(get("/api/users/john_doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john_doe"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User(null,"john_doe", "john@example.com");
        user.setId(1L);

        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"john_doe\",\"email\":\"john@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john_doe"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1L);
    }
}
