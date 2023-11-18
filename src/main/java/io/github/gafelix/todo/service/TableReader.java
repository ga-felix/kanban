package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;

import java.util.List;

public interface TableReader {

    List<Table> getAllTables(List<String> tableIds, String userId);

}