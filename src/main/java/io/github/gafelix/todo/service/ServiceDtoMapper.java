package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.request.UserRegisterDto;

public interface ServiceDtoMapper {

    User toUser(UserRegisterDto request);

}