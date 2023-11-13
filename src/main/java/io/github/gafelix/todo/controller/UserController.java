package io.github.gafelix.todo.controller;

import io.github.gafelix.todo.request.ServiceDTO;
import io.github.gafelix.todo.service.UserService;
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
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ServiceDTO> createUser(@Valid @RequestBody ServiceDTO request) {
        var uri = UriComponentsBuilder
                .fromPath("/user/{id}")
                .buildAndExpand(request.getUserId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(userService.register(request));
    }

}