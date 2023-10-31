package io.github.gafelix.todo.model;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import static io.github.gafelix.todo.config.ModelConstraints.*;

@RequiredArgsConstructor
@Getter
public class User {

    @Size(min= USERNAME_MIN_SIZE, max= USERNAME_MAX_SIZE)
    @NonNull
    private String username;

    @Id
    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
    @NonNull
    private String email;

    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE)
    @NonNull
    private String password;

    private final List<String> knownTablesIds = new ArrayList<>(USERNAME_MAX_TABLES);

}