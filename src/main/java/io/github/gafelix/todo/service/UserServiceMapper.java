package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.request.ServiceDTO;
import org.springframework.stereotype.Service;


@Service
public interface UserServiceMapper {

    User map(ServiceDTO request);

}