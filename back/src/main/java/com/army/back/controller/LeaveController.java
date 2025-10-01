package com.army.back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.army.back.dto.Leave;
import com.army.back.service.LeaveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LeaveController {

    private LeaveService leaveService;

    @PostMapping("/api/leave/register")
    public void registerLeave(@RequestBody Leave leave) {
        leaveService.registerLeave(leave);
    }
    
    @PostMapping("/api/leave/delete")
    public void deleteLeave(@RequestBody Leave leave) {
        leaveService.deleteLeave(leave);
    }

    @PostMapping("/api/leave/modify")
    public void modifyLeave(@RequestBody Leave leave) {
        leaveService.modifyLeave(leave);
    }

    @PostMapping("/api/leave/select/History")
    public void selectLeaveHistory(@RequestBody Leave leave) {
        leaveService.selectLeaveHistory(leave);
    }
}