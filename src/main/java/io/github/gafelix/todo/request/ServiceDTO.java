package io.github.gafelix.todo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import static io.github.gafelix.todo.config.ModelConstraints.*;

@Getter
@Builder
public class ServiceDTO {

    @Size(min= USERNAME_MIN_SIZE, max= USERNAME_MAX_SIZE)
    @NotNull
    private String username;

    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
    @NotNull
    @Email
    private String userId;

    @Size(max = TABLE_MAX_ID_SIZE)
    private String tableId;

}