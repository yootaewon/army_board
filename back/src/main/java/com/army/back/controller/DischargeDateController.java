package com.army.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.army.back.service.DichargeService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DischargeDateController {

    private final DichargeService dichargeService;

    @PostMapping("/api/dischargeDate")
    public ResponseEntity<?> dischargeDate(HttpServletRequest request) {
        try {
            return dichargeService.selectDichargeDate(request);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
}
