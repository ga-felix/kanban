package io.github.gafelix.todo.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import static io.github.gafelix.todo.config.ModelConstraints.USERNAME_MAX_TABLES;


@Getter
@Builder
public class User {

    @Id
    private String id;
    private List<String> knownTablesIds;

}