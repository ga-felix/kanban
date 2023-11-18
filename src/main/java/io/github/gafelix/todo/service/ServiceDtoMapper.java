package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.request.ServiceDto;

public interface ServiceDtoMapper {

    User toUser(ServiceDto request);

}