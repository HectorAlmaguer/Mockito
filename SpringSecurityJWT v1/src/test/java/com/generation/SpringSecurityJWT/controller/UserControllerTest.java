package com.generation.SpringSecurityJWT.controller;

import com.generation.SpringSecurityJWT.model.Post;
import com.generation.SpringSecurityJWT.model.User;
import com.generation.SpringSecurityJWT.service.PostService;
import com.generation.SpringSecurityJWT.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserControllerTest {

    @Test
    @DisplayName("Pruebas UserController save")
    void saveTest() {
        // verificar que el UserService haya sido llamado el metodo save
        UserService userService = mock(UserService.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        UserController userController = new UserController(userService, bCryptPasswordEncoder);

        User user = new User();
        user.setUsername("userJUnit@prueba.com");
        user.setPassword("123456");
        when(userService.save(any(User.class))).thenReturn(user);

        User response = userController.save(user);
        verify(userService, atLeastOnce()).save(any(User.class));
        verify(bCryptPasswordEncoder, atLeastOnce()).encode("123456");
        assertEquals(user.getUsername(), response.getUsername());
    }

}