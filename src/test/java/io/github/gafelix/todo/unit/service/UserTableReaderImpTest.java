package io.github.gafelix.todo.unit.service;

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

    private final User existentUser = UserTableImpTestParameters.existentUser;

    private final List<String> tableIds = UserTableImpTestParameters.tableIds;

    private final List<Table> tables = UserTableImpTestParameters.tables;

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
        existentUser.getKnownTablesIds().clear();
        Assertions.assertThrows(IllegalArgumentException.class, () -> tableReader.getAllTables(tableIds, existentUser.getId()));
    }
    @Test
    void givenInvalidUserIdTableIds_whenReading_thenThrowNoSuchElementException() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> tableReader.getAllTables(tableIds, existentUser.getId()));
    }

}