package com.army.back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.army.back.dto.Leave;
import com.army.back.dto.LeaveTypeCount;

@Mapper
public interface LeaveTypeMapper {

    void insertLeave(@Param("leave") Leave leave, @Param("armyNumber") String armyNumber);

    void deleteLeave(@Param("leaveIds") List<Long> leaveIds);

    void modifyLeave(Leave leave);
    
    List<Leave> selectLeavesByIds(@Param("leaveIds") List<Long> leaveIds);

    List<Leave> selectLeaveHistory(String armyNumber);

    LeaveTypeCount selectLeaveTypeCount(String armyNumber);
}
