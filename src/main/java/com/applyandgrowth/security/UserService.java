package com.applyandgrowth.security;

import com.applyandgrowth.models.User;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}