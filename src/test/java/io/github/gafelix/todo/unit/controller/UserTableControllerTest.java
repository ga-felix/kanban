package io.github.gafelix.todo.unit.controller;

import io.github.gafelix.todo.controller.UserController;
import io.github.gafelix.todo.repository.UserRepository;
import io.github.gafelix.todo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.NoSuchElementException;

import static java.lang.String.format;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserTableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void givenValidUserAndTable_whenPutting_thenReturnCreatedAndTable() throws Exception {
        var body = "{\"id\": \"1\", \"columns\": [{\"label\": \"testColumn\",\"cards\": [{\"title\": \"whateverTask\",\"description\": \"haveYouRestedToday?\"}]}]}";
        var resourceUri = UriComponentsBuilder
                .fromPath("/user/{userId}/table/1")
                .buildAndExpand("cyborg24@email.com")
                .toUriString();
        this.mockMvc.perform(put(format("/user/%s/table", "cyborg24@email.com"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", resourceUri));
    }
    @Test
    void givenInvalidUser_whenPutting_thenReturnNotFound() throws Exception {
        var body = "{\"id\": \"1\", \"columns\": [{\"label\": \"testColumn\",\"cards\": [{\"title\": \"whateverTask\",\"description\": \"haveYouRestedToday?\"}]}]}";
        when(userService.writeTable(Mockito.any())).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(put(format("/user/%s/table", "cyborg24@email.com"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void givenBodyMissingFields_whenPutting_thenReturnBadRequest() throws Exception {
        this.mockMvc.perform(put(format("/user/%s/table", "cyborg24@email.com"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}