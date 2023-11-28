package io.github.gafelix.todo.service;

import io.github.gafelix.todo.request.TableDeleterDto;
import io.github.gafelix.todo.request.TableReaderDto;
import io.github.gafelix.todo.request.TableWriterDto;
import io.github.gafelix.todo.request.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService { // TODO: Testes para os novos serviços

    @Autowired
    private ServiceDtoMapper dtoMapper;

    @Autowired
    private UserRegister userRegister;

    @Autowired
    private TableWriter tableWriter;

    @Autowired
    private TableRemover tableRemover;

    @Autowired
    private TableReader tableReader;

    @Override
    public UserRegisterDto register(UserRegisterDto request) {
        var newUser = userRegister.register(dtoMapper.toUser(request));
        return UserRegisterDto.builder()
                .userId(newUser.getId())
                .build();
    }

    @Override
    public TableWriterDto writeTable(TableWriterDto request) {
        var table = tableWriter.writeTable(request.getTable(), request.getUserId());
        return TableWriterDto.builder()
                .userId(request.getUserId())
                .table(table)
                .build();
    }

    @Override
    public TableDeleterDto deleteTable(TableDeleterDto request) {
        tableRemover.deleteTable(request.getTableId(), request.getUserId());
        return TableDeleterDto.builder().build();
    }

    @Override
    public TableReaderDto getTables(TableReaderDto request) {
        var tables = tableReader.getAllTables(request.getUserId());
        return TableReaderDto.builder()
                .userId(request.getUserId())
                .tables(tables)
                .build();
    }
}