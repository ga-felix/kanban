package io.github.gafelix.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;


@Getter
@Builder
@EqualsAndHashCode
public class Table {

    @Id
    private String id;
    private List<Column> columns;

    @Getter
    @Builder
    public static class Column {

        private String label;
        private List<Card> cards;

    }

}