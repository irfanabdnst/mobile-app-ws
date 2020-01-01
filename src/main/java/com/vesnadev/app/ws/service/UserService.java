package com.vesnadev.app.ws.service;

import com.vesnadev.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);

    UserDto getUser(String email);

    UserDto getUserByUserId(String id);

    UserDto updateUser(String userId, UserDto user);

    List<UserDto> getUsers();

    void deleteUser(String userId);

}
