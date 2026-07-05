package com.nexushr.service;

import java.util.List;

import com.nexushr.entity.User;

public interface UserService {

    User registerUser(User user);

    String login(String email, String password);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    String forgotPassword(String email);

    void resetPassword(String token, String newPassword);

}