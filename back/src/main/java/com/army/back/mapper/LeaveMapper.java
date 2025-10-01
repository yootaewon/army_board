package com.army.back.mapper;

import java.util.List;

import com.army.back.dto.Leave;

public interface LeaveMapper {

    void insertLeave(Leave leave);

    void deleteLeave(Leave leave);

    void modifyLeave(Leave leave);
    
    List<Leave> selectLeaveHistory(Leave leave);
}
