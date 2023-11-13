package io.github.gafelix.todo.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.LinkedList;
import java.util.List;


@Getter
@Builder
public class Table {

    @Id
    private String id;
    private final List<Column> columns = new LinkedList<>();

    @Getter
    @Builder
    private static class Column {

        private String label;
        private final List<Card> cards = new LinkedList<>();

    }

}