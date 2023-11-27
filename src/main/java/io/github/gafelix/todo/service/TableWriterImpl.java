package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.SizeLimitExceededException;

import static io.github.gafelix.todo.config.ModelConstraints.TABLE_MAXIMUM;


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

    @SneakyThrows
    private void addTableReferenceToUser(Table newTable, String userId) {
        var existentUser = userRepository.findById(userId).orElseThrow();
        if (existentUser.getKnownTablesIds().size() >= TABLE_MAXIMUM) throw new SizeLimitExceededException();
        existentUser.getKnownTablesIds().add(newTable.getId());
        userRepository.save(existentUser);
    }

    private Table addNewTable(Table newTable) {
        return tableRepository.save(newTable);
    }
}