package io.github.gafelix.todo.service;

import io.github.gafelix.todo.request.ServiceDTO;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    ServiceDTO register(ServiceDTO request);
    ServiceDTO login(ServiceDTO request);
    ServiceDTO modifyTable(ServiceDTO request);

}