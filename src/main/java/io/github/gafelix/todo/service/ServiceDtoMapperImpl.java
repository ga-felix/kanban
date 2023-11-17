package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.request.ServiceDTO;
import org.springframework.stereotype.Service;


@Service
public class ServiceDtoMapperImpl implements ServiceDtoMapper {

    @Override
    public User toUser(ServiceDTO request) {
        return User.builder()
                .id(request.getUserId())
                .username(request.getUsername())
                .build();
    }

}