package io.github.gafelix.todo.model;

import jakarta.validation.constraints.Size;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static io.github.gafelix.todo.config.ModelConstraints.*;

@RequiredArgsConstructor
public class Card {

    @NonNull
    @Size(min = CARD_TITLE_MIN_LENGTH, max = CARD_TITLE_MAX_LENGTH)
    private String title;

    @NonNull
    @Size(min = CARD_DESCRIPTION_MIN_LENGTH, max = CARD_DESCRIPTION_MAX_LENGTH)
    private String description;

    private final List<String> assignees = new ArrayList<>(CARD_MAX_ASSIGNEES);

}