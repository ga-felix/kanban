package io.github.gafelix.todo.model;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Card {

    private String title;
    private String description;

}