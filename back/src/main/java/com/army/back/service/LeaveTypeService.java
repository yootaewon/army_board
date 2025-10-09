package com.army.back.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.army.back.dto.Leave;
import com.army.back.dto.LeaveTypeCount;
import com.army.back.dto.SignUp;
import com.army.back.enums.ArmyType;
import com.army.back.mapper.LeaveTypeMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveTypeService {

    private final LeaveTypeMapper leaveTypeMapper;

    public void registerAnnualLeave(SignUp user, ArmyType armyType) {
        Leave leave = Leave.builder()
            .leaveType("연가")
            .leaveDays(armyType.getLeave(armyType))
            .reason("기본 연가")
            .build();
        try {
            leaveTypeMapper.insertLeave(leave, user.getArmyNumber());
        } catch (DataAccessException e) {
            throw new RuntimeException("연가 등록 중 오류가 발생했습니다.", e);
        }
    }

    public void registerLeaveType(Leave leave, String armyNumber) {
        if ("연가".equals(leave.getLeaveType())) {
            throw new IllegalArgumentException("연가는 등록할 수 없습니다.");
        }
        try {
            leaveTypeMapper.insertLeave(leave, armyNumber);
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 등록 중 오류가 발생했습니다.", e);
        }
    }

    public void deleteLeaveType(List<Long> leaveIds) {
        try {
            List<Leave> leavesToDelete = leaveTypeMapper.selectLeavesByIds(leaveIds);
            boolean hasAnnualLeave = leavesToDelete.stream().anyMatch(leave -> "연가".equals(leave.getLeaveType()));
        if (hasAnnualLeave) {
            throw new IllegalArgumentException("연가는 삭제할 수 없습니다.");
        }
            leaveTypeMapper.deleteLeave(leaveIds);
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 삭제 중 오류가 발생했습니다.", e);
        }
    }

    public void modifyLeaveType(Leave leave) {
        if ("연가".equals(leave.getLeaveType())) {
            throw new IllegalArgumentException("연가는 수정할 수 없습니다.");
        }
        try {
            leaveTypeMapper.modifyLeave(leave);
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 수정 중 오류가 발생했습니다.", e);
        }
    }

    public List<Leave> selectLeaveTypeHistory(String armyNumber) {
        try {
            List<Leave> historyList = leaveTypeMapper.selectLeaveHistory(armyNumber);
            return historyList;
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 기록 조회 중 오류가 발생했습니다.", e);
        }
    }

    public LeaveTypeCount selectLeaveTypeCount(String armyNumber) {
        try {
            return leaveTypeMapper.selectLeaveTypeCount(armyNumber);
        } catch (DataAccessException e) {
            throw new RuntimeException("휴가 통계 조회 중 오류가 발생했습니다.", e);
        }
    }
}
