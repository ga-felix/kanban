package io.github.gafelix.todo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.gafelix.todo.model.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static io.github.gafelix.todo.config.ModelConstraints.EMAIL_MAX_SIZE;
import static io.github.gafelix.todo.config.ModelConstraints.EMAIL_MIN_SIZE;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class ServiceDto {

    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
    @Email
    private String userId;
    private String tableId;
    private Table table;
    private List<Table> tables;

}