package io.github.gafelix.todo.unit.service;

import io.github.gafelix.todo.model.Card;
import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import io.github.gafelix.todo.service.TableReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(TableReader.class)
class UserTableReaderImpTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TableRepository tableRepository;

    @Autowired
    private TableReader tableReader;

    private final User existentUser = User.builder()
            .id("iLoveBarnacles@email.com")
            .build();

    private final List<String> tableIds = List.of("1");

    private final List<Table> tables = List.of(
            Table.builder()
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
                    .build());

    @Test
    void givenValidUserIdAndTableIds_whenReading_thenReturnAllTables() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.of(existentUser));
        when(tableRepository.findAllById(tableIds)).thenReturn(tables);
        existentUser.getKnownTablesIds().addAll(tableIds);
        Assertions.assertEquals(tables, tableReader.getAllTables(tableIds, existentUser.getId()));
    }

    @Test
    void givenValidUserIdAndNotKnownTableIds_whenReading_thenThrowIllegalArgumentException() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.of(existentUser));
        when(tableRepository.findAllById(tableIds)).thenReturn(tables);
        Assertions.assertThrows(IllegalArgumentException.class, () -> tableReader.getAllTables(tableIds, existentUser.getId()));
    }
    @Test
    void givenInvalidUserIdTableIds_whenReading_thenThrowNoSuchElementException() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.empty());
        when(tableRepository.findAllById(tableIds)).thenReturn(tables);
        Assertions.assertThrows(NoSuchElementException.class, () -> tableReader.getAllTables(tableIds, existentUser.getId()));
    }

}