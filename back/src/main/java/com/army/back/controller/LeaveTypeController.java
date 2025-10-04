package com.army.back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.army.back.dto.CustomUserDetails;
import com.army.back.dto.Leave;
import com.army.back.dto.LeaveTypeCount;
import com.army.back.service.LeaveService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LeaveTypeController {

    private final LeaveService leaveService;

    @PostMapping("/leave-type/select")
    public ResponseEntity<?> selectLeaveCount(@AuthenticationPrincipal CustomUserDetails user) {
        try {
            LeaveTypeCount leave = leaveService.selectLeaveTypeCount(user.getArmyNumber());
            return ResponseEntity.ok(leave);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
    
    @PostMapping("/leave-type/register")
    public ResponseEntity<String> registerLeaveType(@RequestBody Leave leave, @AuthenticationPrincipal CustomUserDetails user) {
        try {
            leaveService.registerLeaveType(leave,user.getArmyNumber());
            return ResponseEntity.status(200).body("휴가 추가 완료");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
    
    @PostMapping("/leave-type/delete")
    public ResponseEntity<String> deleteLeaveType(@RequestBody List<Long> leaveIds) {
        try {
            leaveService.deleteLeaveType(leaveIds);
            return ResponseEntity.status(200).body("휴가 삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }

    @PostMapping("/leave-type/modify")
    public ResponseEntity<String> modifyLeaveType(@RequestBody Leave leave) {
         try {
            leaveService.modifyLeaveType(leave);
            return ResponseEntity.status(200).body("휴가 수정 완료");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }

    @PostMapping("/leave-type/select/history")
    public ResponseEntity<?> selectLeaveHistoryType(@AuthenticationPrincipal CustomUserDetails user) {
         try {
            List<Leave> leaveHistory= leaveService.selectLeaveTypeHistory(user.getArmyNumber());
            return ResponseEntity.ok(leaveHistory);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
}