package io.github.gafelix.todo.service;


import io.github.gafelix.todo.request.ServiceDto;
import io.github.gafelix.todo.request.TableReaderDto;
import io.github.gafelix.todo.request.TableWriterDto;

public interface UserService {

    ServiceDto register(ServiceDto request);
    TableWriterDto writeTable(TableWriterDto request);
    ServiceDto deleteTable(ServiceDto request);
    TableReaderDto getTables(TableReaderDto request);

}