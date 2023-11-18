package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;

public interface TableWriter {

    Table writeTable(Table newTable, String userId);

}