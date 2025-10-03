package com.army.back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.army.back.dto.Leave;
import com.army.back.dto.LeaveTypeCount;

public interface LeaveMapper {

    void insertLeave(@Param("leave") Leave leave, @Param("armyNumber") String armyNumber);

    void deleteLeave(List<Long> leaveId);

    void modifyLeave(Leave leave);
    
    List<Leave> selectLeaveHistory(String armyNumber);

    LeaveTypeCount selectLeaveTypeCount(String armyNumber);
}
