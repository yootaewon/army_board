package com.army.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.army.back.dto.Leave;
import com.army.back.dto.LeaveTypeCount;
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
            .leaveType("comfort")
            .leaveDays(armyType.getLeave(armyType))
            .reason("기본 연가")
            .build();
        leaveMapper.insertLeave(leave,user.getArmyNumber());
    }

    public void registerLeaveType(Leave leave, String armyNumber){
        leaveMapper.insertLeave(leave,armyNumber);
    }

    public void deleteLeaveType(List<Long> leaveIds){
        leaveMapper.deleteLeave(leaveIds);
    }

    public void modifyLeaveType(Leave leave){
        leaveMapper.modifyLeave(leave);
    }

    public List<Leave> selectLeaveTypeHistory(String armyNumber){
        List<Leave> historyList = leaveMapper.selectLeaveHistory(armyNumber);
        return historyList;
    }

    public LeaveTypeCount selectLeaveTypeCount(String armyNumber) {
    return leaveMapper.selectLeaveTypeCount(armyNumber);
    }

}
