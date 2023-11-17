package io.github.gafelix.todo.service;

import io.github.gafelix.todo.exceptions.EntityAlreadyExistsException;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRegisterImpl implements UserRegister {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        if(userAlreadyExists(user)) throw new EntityAlreadyExistsException();
        return userRepository.save(user);
    }

    private boolean userAlreadyExists(User user) {
        return (userRepository.findById(user.getId()).isPresent());
    }

}