package com.army.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.army.back.dto.Leave;
import com.army.back.dto.SignUp;
import com.army.back.enums.ArmyType;
import com.army.back.mapper.LeaveMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final LeaveMapper leaveMapper;

    public void registerAnnualLeave(SignUp user,ArmyType armyType){
        Leave leave = Leave.builder()
            .armyNumber(user.getArmyNumber())
            .leaveType("연가")
            .leaveDays(armyType.getLeave(armyType))
            .reason("기본 연가")
            .build();
        leaveMapper.insertLeave(leave);
    }

    public void registerLeave(Leave leave){
        leaveMapper.insertLeave(leave);
    }

    public void deleteLeave(Leave leave){
        leaveMapper.deleteLeave(leave);
    }

    public void modifyLeave(Leave leave){
        leaveMapper.modifyLeave(leave);
    }

    public List<Leave> selectLeaveHistory(Leave leave){
        List<Leave> historyList = leaveMapper.selectLeaveHistory(leave);
        return historyList;
    }
}
