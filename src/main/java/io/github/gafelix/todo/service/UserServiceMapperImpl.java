package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.request.ServiceDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceMapperImpl implements UserServiceMapper {

    @Override
    public User map(ServiceDTO request) {
        return User.builder()
                .id(request.getUserId())
                .username(request.getUsername())
                .build();
    }

}