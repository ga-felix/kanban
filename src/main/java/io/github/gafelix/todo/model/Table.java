package io.github.gafelix.todo.model;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.LinkedList;
import java.util.List;

import static io.github.gafelix.todo.config.ModelConstraints.TABLE_LABEL_MAX_LENGTH;

public class Table {

    @Id
    private String id;
    private final List<Column> columns = new LinkedList<>();

    @Getter
    @RequiredArgsConstructor
    private static class Column {

        @Setter
        @NonNull
        @Size(max = TABLE_LABEL_MAX_LENGTH)
        private String label;
        private final List<Card> cards = new LinkedList<>();

    }
}