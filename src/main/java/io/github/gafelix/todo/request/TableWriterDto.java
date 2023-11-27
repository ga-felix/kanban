package io.github.gafelix.todo.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.gafelix.todo.model.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class TableWriterDto {

    @JsonIgnore
    private String userId;

    @NotNull
    private Table table;
}