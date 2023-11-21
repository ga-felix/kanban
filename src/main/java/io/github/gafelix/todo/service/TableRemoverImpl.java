package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class TableRemoverImpl implements TableRemover {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public Table deleteTable(String tableId, String userId) {
        var existentUser = userRepository.findById(userId).orElseThrow();
        if(!belongsToUser(tableId, existentUser)) throw new IllegalArgumentException();
        var table = tableRepository.findById(tableId).orElseThrow();
        deleteTableReferenceFromUser(table, existentUser);
        tableRepository.delete(table);
        return table;
    }

    private void deleteTableReferenceFromUser(Table table, User existentUser) {
        existentUser.getKnownTablesIds().remove(table.getId());
        userRepository.save(existentUser);
    }
    private boolean belongsToUser(String tableId, User user) {
        return (new HashSet<>(user.getKnownTablesIds()).contains(tableId));
    }

}