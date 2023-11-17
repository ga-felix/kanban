package io.github.gafelix.todo.service;

import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.TableRepository;
import io.github.gafelix.todo.repository.UserRepository;
import io.github.gafelix.todo.request.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTableServiceImpl implements UserTableService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public ServiceDTO addTable(ServiceDTO request) {
        var user = getUser(request);
        user.getKnownTablesIds().add(request.getTableId());
        var updatedUser = userRepository.save(user);
        return ServiceDTO.builder()
                .tableId(getLast(updatedUser.getKnownTablesIds()))
                .userId(request.getUserId())
                .username(request.getUsername())
                .build();
    }

    private <T> T getLast(List<T> anyList) {
        return anyList.get(anyList.size() - 1);
    }

    @Override
    public ServiceDTO removeTable(ServiceDTO request) {
        var user = getUser(request);
        user.getKnownTablesIds().remove(request.getTableId());
        var updatedUser = userRepository.save(user);
        return ServiceDTO.builder()
                .tableId(getLast(updatedUser.getKnownTablesIds()))
                .userId(request.getUserId())
                .username(request.getUsername())
                .build();
    }

    @Override
    public ServiceDTO getTables(ServiceDTO request) {
        var tables = tableRepository.findAllById(getUser(request).getKnownTablesIds());
        return ServiceDTO.builder()
                .tables(tables)
                .userId(request.getUserId())
                .username(request.getUsername())
                .build();
    }

    private User getUser(ServiceDTO request) {
        return userRepository.findById(request.getUserId()).orElseThrow();
    }
}