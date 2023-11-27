package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.Table;
import io.github.gafelix.todo.request.ServiceDto;
import io.github.gafelix.todo.request.TableWriterDto;
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
    public ServiceDto register(ServiceDto request) {
        var newUser = userRegister.register(dtoMapper.toUser(request));
        return ServiceDto.builder()
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
    public ServiceDto deleteTable(ServiceDto request) {
        tableRemover.deleteTable(request.getTableId(), request.getUserId());
        return ServiceDto.builder()
                .build();
    }

    @Override
    public ServiceDto getTables(ServiceDto request) {
        var tables = tableReader.getAllTables(request.getUserId());
        return ServiceDto.builder()
                .userId(request.getUserId())
                .tables(tables)
                .build();
    }
}