package io.github.gafelix.todo.service;

import io.github.gafelix.todo.request.ServiceDTO;

public interface UserTableService {

    ServiceDTO addTable(ServiceDTO request);
    ServiceDTO removeTable(ServiceDTO request);
    ServiceDTO getTables(ServiceDTO request);

}