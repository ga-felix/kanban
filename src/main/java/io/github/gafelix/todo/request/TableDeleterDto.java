package io.github.gafelix.todo.request;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TableDeleterDto {

    private String userId;
    private String tableId;

}