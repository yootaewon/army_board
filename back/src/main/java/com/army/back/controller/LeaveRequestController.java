package com.army.back.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.army.back.dto.CustomUserDetails;
import com.army.back.dto.LeaveRequest;
import com.army.back.service.LeaveRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leave-request")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService; 

    @PostMapping("/register")
    public ResponseEntity<String> registerLeave(@RequestBody LeaveRequest leave, @AuthenticationPrincipal CustomUserDetails user) {
         try {
            leaveRequestService.registerLeave(leave, user.getArmyNumber());
            return ResponseEntity.ok("휴가가 신청되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("서버 오류: "+ e.getMessage());
        }
    }
    
    @PostMapping("/delete")
    public ResponseEntity<String> deleteLeaveType(@RequestBody List<Long> leaveIds) {
         try {
            leaveRequestService.deleteLeave(leaveIds);
            return ResponseEntity.ok("휴가가 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("서버 오류: "+ e.getMessage());
        }
    }

    @PostMapping("/modify")
    public ResponseEntity<String> modifyLeaveType(@RequestBody LeaveRequest leave) {
          try {
           leaveRequestService.modifyLeave(leave);
            return ResponseEntity.ok("휴가가 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("서버 오류: "+ e.getMessage());
        }
    }

    @PostMapping("/select/history")
    public ResponseEntity<?> selectLeaveHistoryType(@AuthenticationPrincipal CustomUserDetails user) {
         try {
            List<LeaveRequest> leaveRequestHistory = leaveRequestService.selectLeaveHistory(user.getArmyNumber());
            return ResponseEntity.ok(leaveRequestHistory);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
}
