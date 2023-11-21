package io.github.gafelix.todo.unit.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import io.github.gafelix.todo.service.TableRemover;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@WebMvcTest(TableRemover.class)
class UserTableRemoverImpTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TableRepository tableRepository;

    @Autowired
    private TableRemover tableRemover;

    private final User existentUser = UserTableImpTestParameters.existentUser;

    private final Table table = UserTableImpTestParameters.table;
    @Test
    void givenValidUserIdAndKnownTableId_whenRemoving_thenRemoveTableFromUserAndDB() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.of(existentUser));
        when(tableRepository.findById(table.getId())).thenReturn(Optional.of(table));
        existentUser.getKnownTablesIds().add(table.getId());
        assertEquals(table, tableRemover.deleteTable(table.getId(), existentUser.getId()));
    }

    @Test
    void givenValidUserIdAndNotKnownTableId_whenRemoving_thenThrowIllegalArgumentException() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.of(existentUser));
        when(tableRepository.findById(table.getId())).thenReturn(Optional.of(table));
        existentUser.getKnownTablesIds().clear();
        assertThrows(IllegalArgumentException.class, () -> tableRemover.deleteTable(table.getId(), existentUser.getId()));
    }

    @Test
    void givenInvalidUserId_whenRemoving_thenThrowIllegalArgumentException() {
        when(userRepository.findById(existentUser.getId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> tableRemover.deleteTable(table.getId(), existentUser.getId()));
    }

}