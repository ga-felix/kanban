package io.github.gafelix.todo.unit.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import io.github.gafelix.todo.service.TableWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@WebMvcTest(TableWriter.class)
class UserTableWriterImpTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TableRepository tableRepository;

    @Autowired
    private TableWriter tableWriter;

    private final User existentUser = UserTableImpTestParameters.existentUser;

    private final Table table = UserTableImpTestParameters.table;
    @Test
    void givenValidUserIdAndTable_whenWriting_thenUpdateTableForUse() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.of(existentUser));
        when(tableRepository.save(table)).thenReturn(table);
        assertEquals(table, tableWriter.writeTable(table, existentUser.getId()));
    }

    @Test
    void givenValidUserWithMaximumTablesIdAndTable_whenWriting_thenUpdateTableForUse() {
        existentUser.getKnownTablesIds().clear();
        existentUser.getKnownTablesIds().addAll(List.of("", "", "", "", "", "", "", ""));
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.of(existentUser));
        when(tableRepository.save(table)).thenReturn(table);
        assertEquals(table, tableWriter.writeTable(table, existentUser.getId()));
    }

    @Test
    void givenInvalidUserIdAndValidTable_whenWriting_thenThrowNoSuchElementException() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> tableWriter.writeTable(table, existentUser.getId()));
    }

}