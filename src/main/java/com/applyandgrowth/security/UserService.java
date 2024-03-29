package com.applyandgrowth.security;

import com.applyandgrowth.security.UserDto;
//import com.applyandgrowth.models.Email;
import com.applyandgrowth.models.User;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    User findUserByEmail(String email);

    void setPassword(String email, String password);  

    //void sendEmail(Email emailModel);

    List<UserDto> findAllUsers();
}