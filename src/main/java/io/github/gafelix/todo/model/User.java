package io.github.gafelix.todo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import static io.github.gafelix.todo.config.ModelConstraints.*;

@RequiredArgsConstructor
public class User {

    @Size(min= USERNAME_MIN_SIZE, max= USERNAME_MAX_SIZE)
    @NonNull @NotNull
    @Getter
    private String username;

    @Id
    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
    @NonNull @NotNull
    @Getter
    private String id;

    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE)
    @NonNull @NotNull
    private String password;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Getter
    @Setter
    private List<String> knownTablesIds = new ArrayList<>(USERNAME_MAX_TABLES);

}