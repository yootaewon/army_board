package com.army.back.controller;

import com.army.back.dto.JwtToken;
import com.army.back.dto.SignIn;
import com.army.back.dto.SignUp;
import com.army.back.service.ReissueService;
import com.army.back.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ReissueService reissueService;

    @PostMapping("/signUp")
    public ResponseEntity<String> userSignUp(@RequestBody SignUp user) {
        try {
            userService.signUpUser(user);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtToken> userSignIn(@RequestBody SignIn signInDTO) {
        try {
            JwtToken token = userService.signInUser(signInDTO);

            ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", token.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();

            return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
            .body(new JwtToken(token.getAccessToken(), null)); 

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<String> newAccessToken = reissueService.reissue(request, response);
        return newAccessToken;
    }
}
