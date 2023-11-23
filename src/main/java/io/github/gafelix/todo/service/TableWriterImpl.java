package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TableWriterImpl implements TableWriter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public Table writeTable(Table newTable, String userId) {
        var table = addNewTable(newTable);
        addTableReferenceToUser(newTable, userId);
        return table;
    }

    private void addTableReferenceToUser(Table newTable, String userId) {
        var existentUser = userRepository.findById(userId).orElseThrow();
        existentUser.getKnownTablesIds().add(newTable.getId());
        userRepository.save(existentUser);
    }

    private Table addNewTable(Table newTable) {
        return tableRepository.save(newTable);
    }
}