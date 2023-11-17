package io.github.gafelix.todo.service;

import io.github.gafelix.todo.request.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceMapper mapper;

    @Autowired
    private UserRegister userRegister;

    @Override
    public ServiceDTO register(ServiceDTO request) {
        var newUser = userRegister.register(mapper.map(request));
        return ServiceDTO.builder()
                .userId(newUser.getId())
                .username(newUser.getUsername())
                .build();
    }

    @Override
    public ServiceDTO login(ServiceDTO request) {
        return null;
    }

    @Override
    public ServiceDTO addTables(ServiceDTO request) {
        return null;
    }
    @Override
    public ServiceDTO removeTables(ServiceDTO request) {
        return null;
    }
    @Override
    public ServiceDTO getTables(ServiceDTO request) {
        return null;
    }
}
