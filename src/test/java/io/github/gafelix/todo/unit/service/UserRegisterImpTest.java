package io.github.gafelix.todo.unit.service;

import io.github.gafelix.todo.exceptions.EntityAlreadyExistsException;
import io.github.gafelix.todo.model.User;
import io.github.gafelix.todo.repository.UserRepository;
import io.github.gafelix.todo.service.UserRegister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(UserRegister.class)
class UserRegisterImpTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserRegister userRegister;

    private final User newUser = User.builder()
            .id("iLoveBarnacles@email.com")
            .username("PadariaDoce")
            .password("securepassword123")
            .build();
    @Test
    void givenNewUser_whenRegistering_thenCreateNewUserOnDB() {
        when(userRepository.save(newUser)).thenReturn(newUser);
        assertEquals(userRegister.register(newUser).getId(), newUser.getId());
    }

    @Test
    void givenAlreadyExistingUser_whenRegistering_thenThrowException() {
        when(userRepository.findById(newUser.getId())).thenReturn(Optional.of(newUser));
        assertThrows(EntityAlreadyExistsException.class, () -> userRegister.register(newUser));
    }

}