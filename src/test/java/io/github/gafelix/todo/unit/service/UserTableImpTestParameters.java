package io.github.gafelix.todo.unit.service;

import io.github.gafelix.todo.model.Card;
import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserTableImpTestParameters {

    protected static final User existentUser = User.builder()
            .id("iLoveBarnacles@email.com")
            .knownTablesIds(new ArrayList<>())
            .build();

    protected static final List<String> tableIds = List.of("1");

    protected static final List<Table> tables = List.of(
            Table.builder()
                    .id("1")
                    .columns(List.of(
                            Table.Column.builder()
                                    .label("Backlog")
                                    .cards(List.of(Card.builder()
                                            .title("WhatEverTask")
                                            .description("Do whatever you want!")
                                            .build()))
                                    .build(),
                            Table.Column.builder()
                                    .label("In Progress")
                                    .cards(List.of(Card.builder()
                                            .title("Watch what we do in the shadows")
                                            .description("just do it!").build()))
                                    .build()))
                    .build());

    protected static final Table table = Table.builder()
            .id("1")
            .columns(List.of(
                    Table.Column.builder()
                            .label("Backlog")
                            .cards(List.of(Card.builder()
                                    .title("WhatEverTask")
                                    .description("Do whatever you want!")
                                    .build()))
                            .build(),
                    Table.Column.builder()
                            .label("In Progress")
                            .cards(List.of(Card.builder()
                                    .title("Watch what we do in the shadows")
                                    .description("just do it!").build()))
                            .build()))
            .build();
}