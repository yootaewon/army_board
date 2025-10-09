package com.army.back.service;

import com.army.back.dto.LeaveRequest;
import com.army.back.mapper.LeaveRequestMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestMapper leaveRequestMapper;

    public void registerLeave(LeaveRequest leave, String armyNumber ){
        try {
            leaveRequestMapper.insertLeave(leave, armyNumber);
        } catch (Exception e) {
            throw new RuntimeException("휴가 신청 중 오류가 발생했습니다.", e);
        }
    }
    public void modifyLeave(LeaveRequest leave){  
        try {
            leaveRequestMapper.modifyLeave(leave);
        } catch (Exception e) {
            throw new RuntimeException("휴가 수정 중 오류가 발생했습니다.", e);
        }
    }
    public void deleteLeave(List<Long> leaveIds){
        try {
            leaveRequestMapper.deleteLeave(leaveIds);
        } catch (Exception e) {
            throw new RuntimeException("휴가 삭제 중 오류가 발생했습니다.", e);
        }
    }
    public List<LeaveRequest> selectLeaveHistory(String armyNumber){
        try {
            return leaveRequestMapper.selectLeaveHistory(armyNumber);
        } catch (Exception e) {
            throw new RuntimeException("휴가 신청 조회 중 오류가 발생했습니다.", e);
        }
    }
}