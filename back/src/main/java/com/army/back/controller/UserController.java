package com.army.back.controller;

import com.army.back.dto.SignUpDTO;
import com.army.back.jwt.TokenProvider;
import com.army.back.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    private final UserService userService;

    // public UserController(UserService userService) {
    //     this.userService = userService;
    // }

    @PostMapping("/api/signUp")
    public ResponseEntity<String> userSignUp(@RequestBody SignUpDTO user) {
        try {
            userService.signUpUser(user);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/api/signIn")
    public ResponseEntity<?> userSignIn(@RequestBody Map<String, String> loginDto) {
        try {
            String armyNumber = loginDto.get("armyNumber");
            String password = loginDto.get("password");

            Map<String, String> tokens = userService.signInUser(armyNumber, password);
            return ResponseEntity.ok(tokens);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
