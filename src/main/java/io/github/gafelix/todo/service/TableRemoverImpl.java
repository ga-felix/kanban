package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TableRemoverImpl implements TableRemover {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public Table deleteTable(String tableId, String userId) {
        var table = tableRepository.findById(tableId).orElseThrow();
        deleteTableReferenceFromUser(table, userId);
        tableRepository.delete(table);
        return table;
    }

    private void deleteTableReferenceFromUser(Table table, String userId) {
        var existentUser = userRepository.findById(userId).orElseThrow();
        existentUser.getKnownTablesIds().remove(table.getId());
        userRepository.save(existentUser);
    }
}