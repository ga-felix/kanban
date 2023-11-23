package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;

public interface TableRemover {

    void deleteTable(String tableId, String userId);

}