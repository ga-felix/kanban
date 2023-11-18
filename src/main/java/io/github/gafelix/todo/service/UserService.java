package io.github.gafelix.todo.service;


import io.github.gafelix.todo.request.ServiceDto;

public interface UserService {

    ServiceDto register(ServiceDto request);
    ServiceDto writeTable(ServiceDto request);
    ServiceDto deleteTable(ServiceDto request);
    ServiceDto getTables(ServiceDto request);

}