package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.request.ServiceDTO;

public class ServiceDtoMapperImpl implements ServiceDtoMapper {

    @Override
    public User toUser(ServiceDTO request) {
        return User.builder()
                .id(request.getUserId())
                .username(request.getUsername())
                .build();
    }

    @Override
    public Table toTable(ServiceDTO request) {
        return null;
    }
}