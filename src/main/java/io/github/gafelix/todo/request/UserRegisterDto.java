package io.github.gafelix.todo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import static io.github.gafelix.todo.config.ModelConstraints.EMAIL_MAX_SIZE;
import static io.github.gafelix.todo.config.ModelConstraints.EMAIL_MIN_SIZE;

@Getter
@Builder
public class UserRegisterDto {

    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
    @Email
    private String userId;

}