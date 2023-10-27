package io.github.gafelix.todo.model;


import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.util.List;

import static io.github.gafelix.todo.model.Constraints.*;

public class User {

    @Id
    @Size(min= USERNAME_MIN_SIZE, max= USERNAME_MAX_SIZE)
    private String username;

    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
    private String email;

    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE)
    private String encodedPassword;

    private List<String> knownTablesIds;

}