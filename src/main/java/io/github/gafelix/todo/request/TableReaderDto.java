package io.github.gafelix.todo.request;


import io.github.gafelix.todo.model.Table;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TableReaderDto {

    private String userId;
    private List<Table> tables;

}