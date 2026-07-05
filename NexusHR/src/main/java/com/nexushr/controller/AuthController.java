package com.nexushr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.dto.AuthResponse;
import com.nexushr.dto.ForgotPasswordRequest;
import com.nexushr.dto.ResetPasswordRequest;
import com.nexushr.entity.User;
import com.nexushr.service.TokenBlockService;
import com.nexushr.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenBlockService tokenBlockService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {

        String token = userService.login(
                user.getEmail(),
                user.getPassword());

        User loggedInUser = userService.getUserByEmail(user.getEmail());

        return new AuthResponse(
                token,
                loggedInUser.getRole(),
                loggedInUser.getName(),
                loggedInUser.getEmail()
        );
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            tokenBlockService.blockToken(token);
        }

        return "Logged out successfully";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(
            @RequestBody ForgotPasswordRequest request) {

        return userService.forgotPassword(request.getEmail());
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestBody ResetPasswordRequest request) {

        userService.resetPassword(
                request.getToken(),
                request.getNewPassword());

        return "Password reset successful";
    }

}