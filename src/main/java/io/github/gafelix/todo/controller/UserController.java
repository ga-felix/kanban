package io.github.gafelix.todo.controller;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.request.ServiceDto;
import io.github.gafelix.todo.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static io.github.gafelix.todo.config.ModelConstraints.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ServiceDto> createUser(@Valid @RequestBody ServiceDto request) {
        var uri = UriComponentsBuilder
                .fromPath("/user/{userId}")
                .buildAndExpand(request.getUserId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(userService.register(request));
    }

    @GetMapping("/user/{userId}/tables")
    public ResponseEntity<ServiceDto> listUserTables(
            @PathVariable(value = "userId")
            @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
            @Email String userId) {
        var request = ServiceDto.builder()
                .userId(userId)
                .build();
        return ResponseEntity
                .ok(userService.getTables(request));
    }

    @PutMapping("/user/{userId}/table")
    public ResponseEntity<ServiceDto> writeUserTable(
            @PathVariable(value = "userId")
            @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
            @Email String userId,
            @Valid @RequestBody Table table) {
        var request = ServiceDto.builder()
                .userId(userId)
                .table(table)
                .build();
        var uri = UriComponentsBuilder
                .fromPath("/user/{userId}/table")
                .buildAndExpand(request.getUserId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(userService.writeTable(request));
    }

    @DeleteMapping("/user/{userId}/table/{tableId}")
    public ResponseEntity<ServiceDto> deleteUserTable(
            @PathVariable(value = "userId")
            @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
            @Email String userId,
            @PathVariable(value = "tableId")
            @Size(max = TABLE_MAX_ID_SIZE) String tableId) {
        var request = ServiceDto.builder()
                .userId(userId)
                .tableId(tableId)
                .build();
        return ResponseEntity
                .ok(userService.deleteTable(request));
    }

}