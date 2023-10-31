package io.github.gafelix.todo.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.LinkedList;
import java.util.List;

import static io.github.gafelix.todo.config.ModelConstraints.TABLE_LABEL_MAX_LENGTH;
import static io.github.gafelix.todo.config.ModelConstraints.TABLE_MAX_COLUMNS;

public class Table {

    @Id
    @NotNull
    private String id;
    private final List<Column> columns = new LinkedList<>();

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class Column {

        @NonNull
        @Size(max = TABLE_LABEL_MAX_LENGTH)
        private String label;

        @Size(max = TABLE_MAX_COLUMNS)
        private List<Card> cards = new LinkedList<>();

    }
}