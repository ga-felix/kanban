package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;

import java.util.List;

public interface TableReader {
    List<Table> getAllTables(String userId);

}