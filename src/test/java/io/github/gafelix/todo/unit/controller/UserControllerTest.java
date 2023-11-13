package io.github.gafelix.todo.unit.controller;

import io.github.gafelix.todo.controller.UserController;
import io.github.gafelix.todo.exceptions.EntityAlreadyExistsException;
import io.github.gafelix.todo.request.ServiceDTO;
import io.github.gafelix.todo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import static java.lang.String.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void givenValidUser_whenRegistering_thenReturnLocationAndCreated() throws Exception {
        var payload = format(
                "{\"username\": \"%s\", \"userId\": \"%s\", \"password\": \"%s\"}",
                "banana", "cyborg24@email.com", "abc1234");
        var resourceUri = UriComponentsBuilder
                .fromPath("/user/{id}")
                .buildAndExpand("cyborg24@email.com")
                .toUriString();
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", resourceUri));
    }

    @Test
    void givenUserWithMissingFields_whenRegistering_thenReturnBadRequest() throws Exception {
        var payload = format(
                "{\"username\": \"%s\",\"userId\": \"%s\"}",
                "banana", "cyborg24@email.com");
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenUserWithInvalidFields_whenRegistering_thenReturnBadRequest() throws Exception {
        var payload = format(
                "{\"username\": \"%s\",\"userId\": \"%s\",\"password\": \"abc\"}",
                "banana", "cyborg24@email.com");
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenAlreadyExistingUser_whenRegistering_thenReturnBadRequest() throws Exception {
        var payload = format(
                "{\"username\": \"%s\", \"userId\": \"%s\", \"password\": \"%s\"}",
                "banana", "cyborg24@email.com", "abc1234");
        when(userService.register(Mockito.any(ServiceDTO.class))).thenThrow(new EntityAlreadyExistsException());
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}