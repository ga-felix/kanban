package io.github.gafelix.todo.service;


import io.github.gafelix.todo.request.TableDeleterDto;
import io.github.gafelix.todo.request.TableReaderDto;
import io.github.gafelix.todo.request.TableWriterDto;
import io.github.gafelix.todo.request.UserRegisterDto;

public interface UserService {

    UserRegisterDto register(UserRegisterDto request);
    TableWriterDto writeTable(TableWriterDto request);
    TableDeleterDto deleteTable(TableDeleterDto request);
    TableReaderDto getTables(TableReaderDto request);

}