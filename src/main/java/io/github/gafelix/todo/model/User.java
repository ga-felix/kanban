package io.github.gafelix.todo.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;


@Getter
@Builder
public class User {

    @Id
    private String id;
    private List<String> knownTablesIds;

}