package com.vesnadev.app.ws.service;

import com.vesnadev.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);

    UserDto getUser(String email);

    UserDto getUserByUserId(String id);

    UserDto updateUser(String userId, UserDto user);

    String deleteUser(String userId);

}
