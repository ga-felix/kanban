package io.github.gafelix.todo.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static io.github.gafelix.todo.config.ModelConstraints.CARD_MAX_ASSIGNEES;


@Getter
@Builder
public class Card {

    private String title;
    private String description;
    private final List<String> assignees = new ArrayList<>(CARD_MAX_ASSIGNEES);

}