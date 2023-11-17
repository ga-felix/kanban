package io.github.gafelix.todo.service;


import io.github.gafelix.todo.request.ServiceDTO;

public interface UserService {

    ServiceDTO register(ServiceDTO request);
    ServiceDTO writeTable(ServiceDTO request);
    ServiceDTO deleteTable(ServiceDTO request);
    ServiceDTO getTables(ServiceDTO request);

}