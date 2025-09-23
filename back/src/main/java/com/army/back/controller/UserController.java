package com.army.back.controller;

import com.army.back.dto.User;
import com.army.back.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/signUp")
    public ResponseEntity<String> userSignUp(@RequestBody User user) {
        try {
            userService.signUpUser(user);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/api/signIn")
    public ResponseEntity<String> userSignIn(@RequestBody User user) {
        try {
            userService.signInUser(user.getArmyNumber(), user.getPassword());
            return ResponseEntity.ok("로그인 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
