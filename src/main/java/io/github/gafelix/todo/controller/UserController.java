package io.github.gafelix.todo.controller;

import io.github.gafelix.todo.service.UserRegister;
import io.github.gafelix.todo.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {

    @Autowired
    private UserRegister userRegisterServiceImp;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        var uri = UriComponentsBuilder
                .fromPath("/user/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(userRegisterServiceImp.register(user));
    }

}