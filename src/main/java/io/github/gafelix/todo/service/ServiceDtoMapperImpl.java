package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.request.ServiceDto;
import org.springframework.stereotype.Service;


@Service
public class ServiceDtoMapperImpl implements ServiceDtoMapper {

    @Override
    public User toUser(ServiceDto request) {
        return User.builder()
                .id(request.getUserId())
                .build();
    }

}