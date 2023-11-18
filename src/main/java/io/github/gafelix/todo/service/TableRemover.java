package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;

public interface TableRemover {

    Table deleteTable(String tableId, String userId);

}