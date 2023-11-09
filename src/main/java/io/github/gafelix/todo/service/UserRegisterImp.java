package io.github.gafelix.todo.service;

import io.github.gafelix.todo.exceptions.EntityAlreadyExistsException;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterImp implements UserRegister {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User register (User user) throws EntityAlreadyExistsException {
        if(userRepository.findById(user.getEmail()) == null) return userRepository.save(user);
        throw new EntityAlreadyExistsException();
    }
}