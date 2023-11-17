package io.github.gafelix.todo.controller;

import io.github.gafelix.todo.request.ServiceDTO;
import io.github.gafelix.todo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/user/{userId}/tables")
    public ResponseEntity<ServiceDTO> listUserTables(@PathVariable(value = "userId") String userId) {
        return null;
    }

    @PutMapping("/user/{userId}/table")
    public ResponseEntity<ServiceDTO> writeUserTable(@PathVariable(value = "userId") String userId,
                                               @Valid @RequestBody ServiceDTO request) {
        var uri = UriComponentsBuilder
                .fromPath("/user/{id}/table")
                .buildAndExpand(request.getUserId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(userService.addTables(request));
    }

    @DeleteMapping("/user/{userId}/table/{tableId}")
    public ResponseEntity<ServiceDTO> deleteUserTable(@PathVariable(value = "userId") String userId,
                                                     @PathVariable(value = "tableId") String tableId) {
        return null;
    }

}