package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class TableReaderImpl implements TableReader {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Table> getAllTables(List<String> tableIds, String userId) {
        var user = userRepository.findById(userId).orElseThrow();
        if(!belongsToUser(tableIds, user)) throw new IllegalArgumentException();
        return tableRepository.findAllById(tableIds);
    }

    @Override
    public List<Table> getAllTables(String userId) {
        var user = userRepository.findById(userId).orElseThrow();
        return tableRepository.findAllById(user.getKnownTablesIds());
    }

    private boolean belongsToUser(List<String> tableIds, User user) {
        return (new HashSet<>(user.getKnownTablesIds()).containsAll(tableIds));
    }
}