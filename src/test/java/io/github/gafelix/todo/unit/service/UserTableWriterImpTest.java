package io.github.gafelix.todo.unit.service;

import io.github.gafelix.todo.model.Card;
import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import io.github.gafelix.todo.service.TableWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(TableWriter.class)
class UserTableWriterImpTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TableRepository tableRepository;

    @Autowired
    private TableWriter tableWriter;

    private final User existentUser = User.builder()
            .id("iLoveBarnacles@email.com")
            .build();

    private final Table table = Table.builder()
            .id("1")
            .columns(List.of(
                    Table.Column.builder()
                            .label("Backlog")
                            .cards(List.of(Card.builder()
                                    .title("WhatEverTask")
                                    .description("Do whatever you want!")
                                    .build()))
                            .build(),
                    Table.Column.builder()
                            .label("In Progress")
                            .cards(List.of(Card.builder()
                                    .title("Watch what we do in the shadows")
                                    .description("just do it!").build()))
                            .build()))
            .build();
    @Test
    void givenValidUserIdAndTable_whenWriting_thenUpdateTableForUse() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.of(existentUser));
        when(tableRepository.save(table)).thenReturn(table);
        var outputTable = tableWriter.writeTable(table, existentUser.getId());
        Assertions.assertEquals(table, outputTable);
    }

    @Test
    void givenInvalidUserIdAndValidTable_whenWriting_thenThrowNoSuchElementException() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.empty());
        when(tableRepository.save(table)).thenReturn(table);
        Assertions.assertThrows(NoSuchElementException.class, () -> tableWriter.writeTable(table, existentUser.getId()));
    }

}