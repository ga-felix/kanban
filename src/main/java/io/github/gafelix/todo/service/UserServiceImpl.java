package io.github.gafelix.todo.service;

import io.github.gafelix.todo.request.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private ServiceDtoMapper dtoMapper;

    @Autowired
    private UserRegister userRegister;

    @Override
    public ServiceDTO register(ServiceDTO request) {
        var newUser = userRegister.register(dtoMapper.toUser(request));
        return ServiceDTO.builder()
                .userId(newUser.getId())
                .username(newUser.getUsername())
                .build();
    }

    @Override
    public ServiceDTO writeTable(ServiceDTO request) {
        return null;
    }

    @Override
    public ServiceDTO deleteTable(ServiceDTO request) {
        return null;
    }

    @Override
    public ServiceDTO getTables(ServiceDTO request) {
        return null;
    }
}
