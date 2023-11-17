package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.request.ServiceDTO;

public interface ServiceDtoMapper {

    User toUser(ServiceDTO request);

}