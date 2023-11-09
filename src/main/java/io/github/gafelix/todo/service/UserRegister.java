package io.github.gafelix.todo.service;

import io.github.gafelix.todo.exceptions.EntityAlreadyExistsException;
import io.github.gafelix.todo.model.User;

public interface UserRegister {

    User register(User user) throws EntityAlreadyExistsException;

}