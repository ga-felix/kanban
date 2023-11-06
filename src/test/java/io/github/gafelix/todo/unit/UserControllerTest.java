package io.github.gafelix.todo.unit;

import io.github.gafelix.todo.controller.UserController;
import io.github.gafelix.todo.exceptions.UserAlreadyExistsException;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.service.UserRegister;
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
    private UserRegister userRegisterImp;

    @Test
    void shouldReturnCreatedCodeAndLocationHeaderAfterCreatingNewUser() throws Exception {
        var payload = format(
                "{\"username\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}",
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
    void shouldReturnBadRequestWhenCreatingUserWithMissingFields() throws Exception {
        var payload = format(
                "{\"username\": \"%s\",\"email\": \"%s\"}",
                "banana", "cyborg24@email.com");
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingUserWithInvalidFields() throws Exception {
        var payload = format(
                "{\"username\": \"%s\",\"email\": \"%s\",\"password\": \"abc\"}",
                "banana", "cyborg24@email.com");
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingAlreadyExistingUser() throws Exception {
        var payload = format(
                "{\"username\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}",
                "banana", "cyborg24@email.com", "abc1234");
        when(userRegisterImp.register(Mockito.any(User.class))).thenThrow(new UserAlreadyExistsException());
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}