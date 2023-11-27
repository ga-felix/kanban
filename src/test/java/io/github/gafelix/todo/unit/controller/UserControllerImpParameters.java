package io.github.gafelix.todo.unit.controller;

import io.github.gafelix.todo.model.Card;
import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.request.ServiceDto;
import io.github.gafelix.todo.request.TableWriterDto;

import java.util.List;

public class UserControllerImpParameters {
    protected static final String tableBody = "{\"table\": {\"columns\": [{\"label\": \"testColumn\",\"cards\": [{\"title\": \"whateverTask\",\"description\": \"haveYouRestedToday?\"}]}]}}";
    protected static final String emptyBody = "";
    protected static final TableWriterDto response =
            TableWriterDto.builder()
                    .table(
                            Table.builder()
                                    .id("1")
                                    .columns(List.of(
                                            Table.Column.builder()
                                                    .label("testColumn")
                                                    .cards(List.of(Card.builder()
                                                            .title("whateverTask")
                                                            .description("haveYouRestedToday?")
                                                            .build()))
                                                    .build()))
                                    .build()
                    ).build();

    protected static final ServiceDto emptyResponse = ServiceDto.builder().build();

}