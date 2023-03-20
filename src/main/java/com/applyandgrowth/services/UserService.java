package com.applyandgrowth.services;

import com.applyandgrowth.web.dto.UserDto;
import com.applyandgrowth.models.User;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}