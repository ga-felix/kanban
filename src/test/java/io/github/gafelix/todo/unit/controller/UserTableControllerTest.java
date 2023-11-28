package io.github.gafelix.todo.unit.controller;

import io.github.gafelix.todo.controller.UserController;
import io.github.gafelix.todo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.NoSuchElementException;

import static io.github.gafelix.todo.unit.controller.UserControllerImpParameters.*;
import static java.lang.String.format;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        when(userService.writeTable(Mockito.any())).thenReturn(response);
        var resourceUri = UriComponentsBuilder
                .fromPath("/user/{userId}/table/1")
                .buildAndExpand(userId)
                .toUriString();
        this.mockMvc.perform(put(format("/user/%s/table", userId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tableBody))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", resourceUri));
    }
    @Test
    void givenInvalidUser_whenPutting_thenReturnNotFound() throws Exception {
        when(userService.writeTable(Mockito.any())).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(put(format("/user/%s/table", userId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tableBody))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void givenBodyMissingFields_whenPutting_thenReturnBadRequest() throws Exception {
        this.mockMvc.perform(put(format("/user/%s/table", userId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidUserIdAndValidTableId_whenDeletion_thenReturnNoContent() throws Exception {
        when(userService.deleteTable(Mockito.any())).thenReturn(emptyResponse);
        this.mockMvc.perform(delete(format("/user/%s/table/%s", userId, "1")))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    void givenInvalidUserIdAndValidTableId_whenDeletion_thenReturnNotFound() throws Exception {
        when(userService.deleteTable(Mockito.any())).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(delete(format("/user/%s/table/%s", "Idontexist@email.com", "1")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void givenValidUserIdAndInvalidTableId_whenDeletion_thenReturnBadRequest() throws Exception {
        when(userService.deleteTable(Mockito.any())).thenThrow(new IllegalArgumentException());
        this.mockMvc.perform(delete(format("/user/%s/table/%s", userId, "0")))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenInvalidUserId_whenGetting_thenReturnNotFound() throws Exception {
        when(userService.getTables(Mockito.any())).thenThrow(new NoSuchElementException());
        this.mockMvc.perform(get(format("/user/%s/tables", userId)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void givenValidUserId_whenGetting_thenReturnOk() throws Exception {
        when(userService.getTables(Mockito.any())).thenReturn(readerResponse);
        this.mockMvc.perform(get(format("/user/%s/tables", userId)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}