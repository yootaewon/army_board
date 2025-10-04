package com.army.back.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
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

    public void registerAnnualLeave(SignUp user, ArmyType armyType) {
        Leave leave = Leave.builder()
            .leaveType("annual")
            .leaveDays(armyType.getLeave(armyType))
            .reason("기본 연가")
            .build();
        try {
            leaveMapper.insertLeave(leave, user.getArmyNumber());
        } catch (DataAccessException e) {
            throw new RuntimeException("연가 등록 중 오류가 발생했습니다.", e);
        }
    }

    public void registerLeaveType(Leave leave, String armyNumber) {
        try {
            leaveMapper.insertLeave(leave, armyNumber);
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 등록 중 오류가 발생했습니다.", e);
        }
    }

    public void deleteLeaveType(List<Long> leaveIds) {
        try {
            leaveMapper.deleteLeave(leaveIds);
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 삭제 중 오류가 발생했습니다.", e);
        }
    }

    public void modifyLeaveType(Leave leave) {
        try {
            leaveMapper.modifyLeave(leave);
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 수정 중 오류가 발생했습니다.", e);
        }
    }

    public List<Leave> selectLeaveTypeHistory(String armyNumber) {
        List<Leave> historyList = leaveMapper.selectLeaveHistory(armyNumber);

        if (historyList == null || historyList.isEmpty()) {
            throw new RuntimeException("해당 군번의 연가 이력이 없습니다: " + armyNumber);
        }
        return historyList;
    }

    public LeaveTypeCount selectLeaveTypeCount(String armyNumber) {
        try {
            return leaveMapper.selectLeaveTypeCount(armyNumber);
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 통계 조회 중 오류가 발생했습니다.", e);
        }
    }
}
