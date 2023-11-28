package io.github.gafelix.todo.service;


import io.github.gafelix.todo.request.UserRegisterDto;
import io.github.gafelix.todo.request.TableDeleterDto;
import io.github.gafelix.todo.request.TableReaderDto;
import io.github.gafelix.todo.request.TableWriterDto;

public interface UserService {

    UserRegisterDto register(UserRegisterDto request);
    TableWriterDto writeTable(TableWriterDto request);
    TableDeleterDto deleteTable(TableDeleterDto request);
    TableReaderDto getTables(TableReaderDto request);

}