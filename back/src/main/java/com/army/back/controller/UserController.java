package com.army.back.controller;

import com.army.back.dto.SignInDTO;
import com.army.back.dto.SignUpDTO;
import com.army.back.service.UserService;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

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
    public ResponseEntity<String> userSignIn(@RequestBody SignInDTO signInDTO) {
        try {
            String tokens = userService.signInUser(signInDTO);
            return ResponseEntity.ok(tokens);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
